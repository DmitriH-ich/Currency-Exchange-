
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
}