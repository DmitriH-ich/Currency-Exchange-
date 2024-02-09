
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Класс ExchangeRecord представляет запись об обмене валюты. Он содержит информацию о дате и
 * времени обмена, сумме, исходной и целевой валюте. Класс предоставляет методы для получения
 * каждого из этих параметров и переопределяет метод toString для удобного вывода записи об обмене в
 * виде строки.
 */
public class ExchangeRecord {

  private LocalDateTime dateTime;// Дата и время обмена
  private double amount;// Сумма обмена
  private String fromCurrency;// Исходная валюта
  private String toCurrency;// Целевая валюта

  /**
   * Конструктор класса ExchangeRecord. Создает новую запись об обмене с заданной датой и временем,
   * суммой, исходной и целевой валютой.
   *
   * @param dateTime     Дата и время обмена.
   * @param amount       Сумма обмена.
   * @param fromCurrency Исходная валюта.
   * @param toCurrency   Целевая валюта.
   */
  public ExchangeRecord(LocalDateTime dateTime, double amount, String fromCurrency,
      String toCurrency) {
    this.dateTime = LocalDateTime.now();// Устанавливаем текущую дату и время, если не указаны явно
    this.amount = amount;
    this.fromCurrency = fromCurrency;
    this.toCurrency = toCurrency;
  }

  /**
   * Метод для получения даты и времени обмена.
   *
   * @return Дата и время обмена.
   */
  public LocalDateTime getDateTime() {
    return dateTime;
  }

  /**
   * Метод для получения суммы обмена.
   *
   * @return Сумма обмена.
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

  /**
   * Переопределенный метод toString для удобного вывода записи об обмене в виде строки. Строка
   * содержит дату и время обмена, сумму, исходную и целевую валюту.
   */
  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = dateTime.format(formatter);
    return String.format("[%s] Обмен: %.2f %s -> %s", formattedDateTime, amount, fromCurrency,
        toCurrency);
  }
}

