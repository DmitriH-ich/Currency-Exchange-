import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Класс UserInterface представляет собой пользовательский интерфейс для взаимодействия с программой
 * обмена валюты. Он включает методы для получения ввода от пользователя, валидации введенных данных
 * и отображения результатов обмена.
 */
public class UserInterface {

  /**
   * Scanner для считывания ввода пользователя.
   */
  private final Scanner scanner;

  /**
   * Конструктор класса, инициализирующий Scanner для ввода данных.
   */
  public UserInterface() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Метод получения ввода от пользователя для создания объекта ExchangeRequest. Пользователю
   * предлагается ввести сумму, валюту для обмена и валюту для приобретения. В случае некорректного
   * ввода, метод обрабатывает исключение InputMismatchException и предоставляет пользователю
   * возможность ввести корректные данные.
   *
   * @return ExchangeRequest - объект, представляющий запрос на обмен валюты.
   */
  public ExchangeRequest getUserInput() {
    try {
      System.out.println("Введите сумму для обмена:");
      double amount = scanner.nextDouble();
      System.out.println("Введите валюту, которую вы хотите обменять (USD, EUR, GBP):");
      String fromCurrency = validateCurrency(scanner.next().toUpperCase());
      System.out.println("Введите валюту, которую вы хотите приобрести (EUR, GBP, USD):");
      String toCurrency = validateCurrency(scanner.next().toUpperCase());
      return new ExchangeRequest(amount, fromCurrency, toCurrency);
    } catch (InputMismatchException ex) {
      System.out.println("Ошибка ввода. Пожалуйста, введите корректные данные.");
      scanner.nextLine();
      return getUserInput();
    }
  }

  /**
   * Приватный метод для валидации введенной пользователем валюты. В случае неверного формата валюты
   * выбрасывается исключение InputMismatchException.
   *
   * @param currency - строка, представляющая введенную валюту.
   * @return String - введенная и валидированная валюта.
   * @throws InputMismatchException - в случае неверного формата валюты.
   */
  private String validateCurrency(String currency) throws InputMismatchException {
    currency = currency.toUpperCase();
    if (!currency.matches("USD|EUR|GBP")) {
      throw new InputMismatchException("Неверный формат валюты. Выберите валюту из предложенного "
          + "списка конвертируемых валют.");
    }
    return currency;
  }

  /**
   * Метод для отображения результатов обмена валюты.
   *
   * @param result - результат обмена, который необходимо отобразить.
   */
  public void displayResult(double result) {
    System.out.println("Результат обмена: " + result);
  }

  /**
   * Getter метод для получения объекта Scanner, используемого для ввода данных.
   *
   * @return Scanner - объект Scanner.
   */
  public Scanner getScanner() {
    return scanner;
  }
}

