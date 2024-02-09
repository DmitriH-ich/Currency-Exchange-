
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс ExchangeHistory представляет собой историю обмена валюты. Он отвечает за загрузку и
 * сохранение истории обмена из/в текстовый файл, добавление новых записей обмена, а также
 * отображение всей истории.
 */
public class ExchangeHistory {

  private List<ExchangeRecord> history;// Список записей обмена
  private static final String HISTORY_FILE_NAME = "exchange_history.txt";// Имя файла истории

  /**
   * Конструктор класса. Инициализирует историю обмена загруженными из файла данными.
   */
  public ExchangeHistory() {
    this.history = loadHistory();
  }// Загрузка истории обмена из файла

  /**
   * Метод для добавления новой записи обмена в историю и сохранения её в файл.
   *
   * @param record Новая запись обмена.
   */
  public void addRecord(ExchangeRecord record) {
    history.add(record);// Добавление записи в историю
    saveHistory(history);// Сохранение истории в файл
  }

  /**
   * Метод для отображения всей истории обмена.
   */
  public void displayHistory() {
    history.forEach(System.out::println);// Вывод всех записей обмена в консоль
  }

  /**
   * Приватный метод для загрузки истории обмена из файла.
   *
   * @return Список загруженных записей обмена.
   */
  private List<ExchangeRecord> loadHistory() {
    List<ExchangeRecord> history = new ArrayList<>();// Создание нового списка истории

    File file = new File(HISTORY_FILE_NAME);// Создание объекта файла
    // Проверка существования файла и вывод сообщения, если файл не существует
    if (!file.exists()) {
      System.out.println("Файл истории не существует.");
      return history;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      // Построчное чтение файла и парсинг каждой строки в объект ExchangeRecord
      while ((line = reader.readLine()) != null) {
        ExchangeRecord record = parseRecord(line);// Парсинг строки в объект ExchangeRecord
        if (record != null) {
          history.add(record);// Добавление записи в список истории
        }
      }
    } catch (IOException e) {
      // Обработка ошибки при чтении файла и вывод сообщения об ошибке
      System.out.println("Ошибка при загрузке истории.");
      e.printStackTrace();
    }
    return history;// Возвращение списка загруженной истории обмена
  }

  /**
   * Приватный метод для сохранения истории обмена в файл.
   *
   * @param history Список записей обмена для сохранения.
   */
  private void saveHistory(List<ExchangeRecord> history) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE_NAME))) {
      // Построчная запись истории обмена в файл
      history.forEach(record -> {
        try {
          writer.write(formatRecord(record));// Форматирование записи обмена
          writer.newLine();// Добавление новой строки после каждой записи
        } catch (IOException e) {
          // Обработка ошибки при записи в файл и вывод сообщения об ошибке
          System.out.println("Ошибка при записи в файл истории.");
          e.printStackTrace();
        }
      });
    } catch (IOException e) {
      // Обработка ошибки при открытии/закрытии файла и вывод сообщения об ошибке
      System.out.println("Ошибка при сохранении истории.");
      e.printStackTrace();
    }
  }

  /**
   * Приватный метод для парсинга строки из файла в объект ExchangeRecord.
   *
   * @param line Строка из файла, содержащая данные об одном обмене.
   * @return Объект ExchangeRecord, представляющий данные об одном обмене, или null в случае ошибки
   * парсинга.
   */
  private ExchangeRecord parseRecord(String line) {
    String[] parts = line.split(";");// Разделение строки на части по разделителю ";"
    if (parts.length == 4) {
      try {
        // Парсинг каждой части строки и создание объекта ExchangeRecord
        LocalDateTime dateTime = LocalDateTime.parse(parts[0],
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        double amount = Double.parseDouble(parts[1]);
        String fromCurrency = parts[2];
        String toCurrency = parts[3];
        // Создание объекта ExchangeRecord
        return new ExchangeRecord(dateTime, amount, fromCurrency, toCurrency);
      } catch (Exception e) {
        // Обработка ошибки при парсинге строки и вывод сообщения об ошибке
        System.out.println("Ошибка при парсинге строки: " + line + ". Проигнорирована.");
        e.printStackTrace();
      }
    } else {
      // Вывод сообщения об ошибке, если строка имеет некорректный формат
      System.out.println("Некорректная запись: " + line + ". Проигнорирована.");
    }
    return null;
  }

  /**
   * Приватный метод для форматирования записи обмена в строку. Этот метод принимает объект типа
   * ExchangeRecord и возвращает отформатированную строку, представляющую данные об обмене. Если
   * форматирование происходит успешно, метод возвращает строку, содержащую дату и время обмена,
   * сумму, начальную и конечную валюту. Если возникает исключение при форматировании, метод выводит
   * сообщение об ошибке, выводит информацию об объекте записи обмена и стектрейс исключения, а
   * затем возвращает null.
   *
   * @param record Объект типа ExchangeRecord, представляющий запись обмена.
   * @return Отформатированная строка с данными об обмене или null в случае ошибки.
   */
  private String formatRecord(ExchangeRecord record) {
    try {
      // Создаем форматтер для даты и времени
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      // Форматируем дату и время обмена
      String formattedDateTime = record.getDateTime().format(formatter);
      // Форматируем строку для записи обмена с указанием даты и времени, суммы, начальной и конечной валюты
      return String.format("[%s] Обмен: %.2f %s -> %s", formattedDateTime, record.getAmount(),
          record.getFromCurrency(), record.getToCurrency());
    } catch (Exception e) {
      // В случае ошибки при форматировании выводим сообщение об ошибке, информацию о записи обмена
      // и стектрейс исключения
      System.out.println("Ошибка при форматировании записи: " + record);
      e.printStackTrace();
      // Возвращаем null, чтобы обработать ошибку в вызывающем коде
      return null;
    }
  }
}


