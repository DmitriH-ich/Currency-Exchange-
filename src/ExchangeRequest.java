/**
 * Класс ExchangeRequest представляет запрос на обмен валюты. Он содержит информацию о сумме,
 * исходной валюте и целевой валюте для обмена.
 */
public class ExchangeRequest {

  private double amount;// Сумма для обмена
  private String fromCurrency;// Исходная валюта
  private String toCurrency;// Целевая валюта

  /**
   * Конструктор класса ExchangeRequest. Создает новый объект запроса на обмен с заданной суммой и
   * валютами.
   *
   * @param amount       Сумма для обмена.
   * @param fromCurrency Исходная валюта.
   * @param toCurrency   Целевая валюта.
   */
  public ExchangeRequest(double amount, String fromCurrency, String toCurrency) {
    this.amount = amount;
    this.fromCurrency = fromCurrency;
    this.toCurrency = toCurrency;
  }

  /**
   * Метод для получения суммы для обмена.
   *
   * @return Сумма для обмена.
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Метод для получения исходной валюты.
   *
   * @return Исходная валюта.
   */
  public String getFromCurrency() {
    return fromCurrency;
  }

  /**
   * Метод для получения целевой валюты.
   *
   * @return Целевая валюта.
   */
  public String getToCurrency() {
    return toCurrency;
  }
}
