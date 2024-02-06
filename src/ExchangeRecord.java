
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
  public class ExchangeRecord {
    private LocalDateTime dateTime;
    private double amount;
    private String fromCurrency;
    private String toCurrency;
    public ExchangeRecord(double amount, String fromCurrency, String toCurrency) {
      this.dateTime = LocalDateTime.now();
      this.amount = amount;
      this.fromCurrency = fromCurrency;
      this.toCurrency = toCurrency;
    }
    @Override
    public String toString() {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      String formattedDateTime = dateTime.format(formatter);
      return String.format("[%s] Обмен: %.2f %s -> %s", formattedDateTime, amount, fromCurrency, toCurrency);
    }
  }

