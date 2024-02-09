
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ExchangeHistory {

  private List<ExchangeRecord> history;
  private static final String HISTORY_FILE_NAME = "exchange_history.txt";

  public ExchangeHistory() {
    this.history = loadHistory();
  }

  public void addRecord(ExchangeRecord record) {
    history.add(record);
    saveHistory(history);
  }

  public void displayHistory() {
    history.forEach(System.out::println);
  }

  private List<ExchangeRecord> loadHistory() {
    List<ExchangeRecord> history = new ArrayList<>();

    File file = new File(HISTORY_FILE_NAME);

    if (!file.exists()) {
      System.out.println("Файл истории не существует.");
      return history;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = reader.readLine()) != null) {
        ExchangeRecord record = parseRecord(line);
        if (record != null) {
          history.add(record);
        }
      }
    } catch (IOException e) {
      System.out.println("Ошибка при загрузке истории.");
      e.printStackTrace();
    }

    return history;
  }

  private void saveHistory(List<ExchangeRecord> history) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(HISTORY_FILE_NAME))) {
      history.forEach(record -> {
        try {
          writer.write(formatRecord(record));
          writer.newLine();
        } catch (IOException e) {
          System.out.println("Ошибка при записи в файл истории.");
          e.printStackTrace();
        }
      });
    } catch (IOException e) {
      System.out.println("Ошибка при сохранении истории.");
      e.printStackTrace();
    }
  }

  private ExchangeRecord parseRecord(String line) {
    String[] parts = line.split(";");
    if (parts.length == 4) {
      try {
        LocalDateTime dateTime = LocalDateTime.parse(parts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        double amount = Double.parseDouble(parts[1]);
        String fromCurrency = parts[2];
        String toCurrency = parts[3];
        return new ExchangeRecord(dateTime, amount, fromCurrency, toCurrency);
      } catch (Exception e) {
        System.out.println("Ошибка при парсинге строки: " + line + ". Проигнорирована.");
        e.printStackTrace();
      }
    } else {
      System.out.println("Некорректная запись: " + line + ". Проигнорирована.");
    }
    return null;
  }

  private String formatRecord(ExchangeRecord record) {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      String formattedDateTime = record.getDateTime().format(formatter);
      return String.format("%s;%s;%s;%s", formattedDateTime, record.getAmount(), record.getFromCurrency(), record.getToCurrency());
    } catch (Exception e) {
      System.out.println("Ошибка при форматировании записи: " + record);
      e.printStackTrace();
      return null;
    }
  }
}


