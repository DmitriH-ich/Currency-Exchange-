
public class CurrencyExchange {

  private static final double USD_TO_EUR_RATE = 0.93;
  private static final double USD_TO_GBP_RATE = 0.79;
  private static final double EUR_TO_USD_RATE = 1.08;
  private static final double EUR_TO_GBP_RATE = 0.86;
  private static final double GBP_TO_USD_RATE = 1.26;
  private static final double GBP_TO_EUR_RATE = 1.17;

  //метод для преобразования обмена валюты
  public double performExchange(ExchangeRequest request) {
    double amount = request.getAmount();
    String fromCurrency = request.getFromCurrency();
    String toCurrency = request.getToCurrency();
    double result = 0;
    try {
      switch (fromCurrency + toCurrency) {
        case "USDEUR":
          result = amount * USD_TO_EUR_RATE;
          break;
        case "USDGBP":
          result = amount * USD_TO_GBP_RATE;
          break;
        case "EURUSD":
          result = amount * EUR_TO_USD_RATE;
          break;
        case "EURGBP":
          result = amount * EUR_TO_GBP_RATE;
          break;
        case "GBPUSD":
          result = amount * GBP_TO_USD_RATE;
          break;
        case "GBPEUR":
          result = amount * GBP_TO_EUR_RATE;
          break;
        default:
          throw new UnsupportedOperationException("Невозможно обменять " + fromCurrency
              + " на " + toCurrency);
      }
      System.out.println("Возьмите ваши деньги в сумме: " + result + " " + toCurrency);
    } catch (UnsupportedOperationException ex) {
      System.out.println(ex.getMessage());
    }
    return result;
  }
}