import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс CurrencyExchange представляет систему обмена валюты. Он загружает курсы обмена из файла при
 * создании экземпляра, выполняет обмен валюты и сохраняет историю обменов. Класс также
 * предоставляет доступ к истории обменов.
 */
public class CurrencyExchange {

  private Map<String, Double> exchangeRates;// Хранит курсы обмена
  private ExchangeHistory exchangeHistory;// Хранит историю обменов

  /**
   * Конструктор класса CurrencyExchange. Загружает курсы обмена из файла и инициализирует объект
   * истории обменов.
   *
   * @param filename Имя файла с курсами обмена.
   * @throws IOException Если произошла ошибка при загрузке файла с курсами обмена.
   */
  public CurrencyExchange(String filename) throws IOException {
    exchangeRates = new HashMap<>();
    loadExchangeRates(filename);
    exchangeHistory = new ExchangeHistory();
  }

  /**
   * Приватный метод для получения файла с курсами обмена. Создает новый файл, если он не
   * существует, и записывает в него курсы обмена по умолчанию.
   *
   * @return Объект типа File, представляющий файл с курсами обмена.
   * @throws IOException Если произошла ошибка при создании или записи в файл.
   */
  private File getFile() throws IOException {
    File file = new File("exchange_rates.txt");
    boolean newFile = file.createNewFile();

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
      String[] exchangeRates = {
          "USD to EUR: 0.93",
          "USD to GBP: 0.79",
          "EUR to USD: 1.08",
          "EUR to GBP: 0.86",
          "GBP to USD: 1.26",
          "GBP to EUR: 1.17"
      };
      for (String rate : exchangeRates) {
        bw.write(rate);
        bw.newLine();
      }
      return file;
    }
  }

  /**
   * Приватный метод для загрузки курсов обмена из файла. Извлекает курсы обмена из файла и
   * сохраняет их в объект exchangeRates.
   *
   * @param filename Имя файла с курсами обмена.
   * @throws IOException Если произошла ошибка при чтении файла.
   */
  private void loadExchangeRates(String filename) throws IOException {
    File file = getFile();

    try {
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] parts = line.split(": ");
        String currencyPair = parts[0].trim();
        double rate = Double.parseDouble(parts[1].trim());
        exchangeRates.put(currencyPair, rate);
      }
    } catch (FileNotFoundException ex) {
      System.out.println("Ошибка: Файл с курсами обмена валюты не найден.");
    }
  }

  /**
   * Метод для выполнения обмена валюты. Принимает запрос на обмен и выполняет его на основе
   * загруженных курсов обмена. Сохраняет запись об обмене в истории обменов.
   *
   * @param request Запрос на обмен валюты.
   * @return Результат обмена - сумма после обмена.
   */
  public double performExchange(ExchangeRequest request) {
    double amount = request.getAmount();
    String fromCurrency = request.getFromCurrency();
    String toCurrency = request.getToCurrency();
    double result = 0;

    try {
      String currencyPair = fromCurrency + " to " + toCurrency;
      if (exchangeRates.containsKey(currencyPair)) {
        double rate = exchangeRates.get(currencyPair);
        result = amount * rate;

        // Создаем запись об обмене
        ExchangeRecord exchangeRecord = new ExchangeRecord(
            LocalDateTime.now(), // добавляем текущее время
            amount,
            fromCurrency,
            toCurrency);
        exchangeHistory.addRecord(exchangeRecord);

        System.out.println("Возьмите ваши деньги в сумме: " + result + " " + toCurrency);
      } else {
        throw new UnsupportedOperationException(
            "Невозможно обменять " + fromCurrency + " на " + toCurrency);
      }
    } catch (UnsupportedOperationException ex) {
      System.out.println(ex.getMessage());
    }
    return result;
  }

  /**
   * Метод для доступа к истории обменов.
   *
   * @return Объект истории обменов.
   */
  public ExchangeHistory getExchangeHistory() {
    return exchangeHistory;

  }
}
