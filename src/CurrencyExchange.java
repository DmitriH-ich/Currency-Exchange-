

  public class CurrencyExchange {
    private static final double USD_TO_EUR_RATE = 0.85;
    private static final double USD_TO_GBP_RATE = 0.73;

    public double performExchange(ExchangeRequest request) {
      double amount = request.getAmount();
      String fromCurrency = request.getFromCurrency();
      String toCurrency = request.getToCurrency();
      double result = 0;
      if ("USD".equals(fromCurrency) && "EUR".equals(toCurrency)) {
        result = amount * USD_TO_EUR_RATE;
      } else if ("USD".equals(fromCurrency) && "GBP".equals(toCurrency)) {
        result = amount * USD_TO_GBP_RATE;
      }
    }