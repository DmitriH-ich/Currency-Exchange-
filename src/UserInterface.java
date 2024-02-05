import java.util.Scanner;
public class UserInterface {
  private Scanner scanner;
  public UserInterface() {
    this.scanner = new Scanner(System.in);
  }
  public ExchangeRequest getUserInput() {
    System.out.println("Введите сумму для обмена:");
    double amount = scanner.nextDouble();
    System.out.println("Введите валюту, которую вы хотите обменять (например, USD):");
    String fromCurrency = scanner.next();
    System.out.println("Введите валюту, которую вы хотите приобрести (например, EUR):");
    String toCurrency = scanner.next();
    return new ExchangeRequest(amount, fromCurrency, toCurrency);
  }
  public void displayResult(double result) {
    System.out.println("Результат обмена: " + result);
  }
  public Scanner getScanner() {
    return scanner;
  }
}