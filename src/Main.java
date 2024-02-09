import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void ChooseOption() {
    System.out.println("Выберите опцию меню: ");
    System.out.println("1. Обменять валюту");
    System.out.println("2. Просмотреть историю обмена валюты");
    System.out.println("3. Выход из меню");
  }

  public static void main(String[] args) throws IOException {
    UserInterface userInterface = new UserInterface();
    CurrencyExchange currencyExchange = new CurrencyExchange("exchange_rates.txt");

    while (true) {
      Runnable method = Main::ChooseOption;
      method.run();
      Scanner scanner = new Scanner(System.in);
      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          ExchangeRequest request = userInterface.getUserInput();
          double result = currencyExchange.performExchange(request);
          userInterface.displayResult(result);

          break;

        case 2:
          System.out.println("История обменов:");
          currencyExchange.getExchangeHistory().displayHistory();
          break;

        case 3:
          System.out.println("Всего доброго!");
          System.exit(0);
          break;

        default:
          System.out.println("Некорректный выбор. Пожалуйста, выберите предлагаемую опцию.");
      }
    }
  }
}
