import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExchangeHistory {

  private List<ExchangeRecord> history;

  public ExchangeHistory() {
    this.history = new ArrayList<>();
  }

  public void addRecord(ExchangeRecord record) {
    history.add(record);
  }

  public void displayHistory() {
    for (ExchangeRecord record : history) {
      System.out.println(record);
    }
  }
  // Метод для создания текстового файла для сохранения истории обмена
  public void createHistoryFile(String filename) {
    try {
      // Создаем новый файл
      FileWriter fileWriter = new FileWriter("exchange_history.txt");
      fileWriter.close();
      System.out.println("Файл " + filename + " успешно создан.");
    } catch (IOException ex) {
      System.err.println("Ошибка при создании файла: " + ex.getMessage());
    }
  }
  public void saveHistoryToFile(String filename) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
      for (ExchangeRecord record : history) {
        bw.write(record.toString());
        bw.newLine();
      }
      System.out.println("История обмена сохранена в файл: " + filename);
    } catch (IOException ex) {
      System.err.println("Ошибка при сохранении истории обмена в файл: " + ex.getMessage());
    }
  }
  // Добавленный метод для сохранения истории обмена в файл при выходе из программы
  public void saveHistoryOnExit(String filename) {
    saveHistoryToFile(filename);
  }
}