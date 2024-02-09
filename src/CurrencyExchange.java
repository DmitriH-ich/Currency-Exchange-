import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyExchange {


    private Map<String, Double> exchangeRates;
    private ExchangeHistory exchangeHistory;


    public CurrencyExchange(String filename) throws IOException {
        exchangeRates = new HashMap<>();
        loadExchangeRates(filename);
        exchangeHistory = new ExchangeHistory();
    }

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

    public ExchangeHistory getExchangeHistory() {
        return exchangeHistory;

    }
}
