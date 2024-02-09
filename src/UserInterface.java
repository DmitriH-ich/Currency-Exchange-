import java.util.InputMismatchException;
import java.util.Scanner;
public class UserInterface {
  private final Scanner scanner;
  public UserInterface() {
    this.scanner = new Scanner(System.in);
  }
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
  private String validateCurrency(String currency) throws InputMismatchException {
    if (!currency.matches("USD|EUR|GBP")) {
      throw new InputMismatchException("Неверный формат валюты. Выберите валюту из предложенного "
          + "списка конвертируемых валют.");
    }
    return currency;
  }
  public void displayResult(double result) {
    System.out.println("Результат обмена: " + result);
  }
  public Scanner getScanner() {
    return scanner;
  }
}
