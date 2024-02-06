public class ExchangeRequest {
  private double amount;
  private String fromCurrency;
  private String toCurrency;
  public ExchangeRequest(double amount, String fromCurrency, String toCurrency) {
    this.amount = amount;
    this.fromCurrency = fromCurrency;
    this.toCurrency = toCurrency;
  }
  public double getAmount() {
    return amount;
  }
  public String getFromCurrency() {
    return fromCurrency;
  }
  public String getToCurrency() {
    return toCurrency;
  }
}