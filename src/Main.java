
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
    ExchangeHistory exchangeHistory = new ExchangeHistory();
    exchangeHistory.createHistoryFile("exchange_history.txt");

    while (true) {
      Runnable method = Main::ChooseOption;
      method.run();
      Scanner scanner = new Scanner(System.in);
      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          //Код для обмена валюты
          ExchangeRequest request = userInterface.getUserInput();
          double result = currencyExchange.performExchange(request);
          userInterface.displayResult(result);

          // Сохранение обмена в истории
          ExchangeRecord exchangeRecord = new ExchangeRecord(request.getAmount(),
              request.getFromCurrency(), request.getToCurrency());
          exchangeHistory.addRecord(exchangeRecord);
          System.out.println("Хотите продолжить (да/нет)?");

          String continueOption = userInterface.getScanner().next();

          if (!"да".equalsIgnoreCase(continueOption)) {
            System.out.println("Выход из меню.");
            System.exit(0);
          }
          break;

        case 2:
          // Вывод истории обменов
          System.out.println("История обменов:");
          exchangeHistory.displayHistory();
          break;

        case 3:
          //Код для выхода из меню
          exchangeHistory.saveHistoryOnExit("exchange_history.txt");
          System.out.println("Всего доброго!");
          System.exit(0);
          break;
        default:
          System.out.println("Некорректный выбор. Пожалуйста, выберите предлагаемую опцию.");
      }
    }
  }
}
