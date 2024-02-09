import java.io.IOException;
import java.util.Scanner;

/**
 * Класс Main представляет точку входа в программу для управления обменом валюты. Он содержит метод
 * main, который запускает основной цикл программы, где пользователь может выбирать различные опции
 * меню.
 */
public class Main {

  /**
   * Метод ChooseOption выводит на экран опции меню.
   */
  public static void ChooseOption() {
    System.out.println("Выберите опцию меню: ");
    System.out.println("1. Обменять валюту");
    System.out.println("2. Просмотреть историю обмена валюты");
    System.out.println("3. Выход из меню");
  }

  /**
   * Метод main - точка входа в программу. Он запускает основной цикл программы, в котором
   * пользователь может выбирать опции меню.
   *
   * @param args Параметры командной строки.
   * @throws IOException Исключение, возникающее при ошибке ввода-вывода.
   */
  public static void main(String[] args) throws IOException {
    // Создание объекта userInterface для взаимодействия с пользователем.
    UserInterface userInterface = new UserInterface();
    // Создание объекта currencyExchange для выполнения операций обмена валюты.
    // В качестве аргумента конструктору передается имя файла, содержащего курсы обмена валюты.
    CurrencyExchange currencyExchange = new CurrencyExchange("exchange_rates.txt");
    // Бесконечный цикл, который обеспечивает постоянное отображение опций меню и
    // ожидание ввода пользователя.
    while (true) {
      // Создание объекта Runnable для выполнения метода ChooseOption класса Main.
      // Этот метод отображает опции меню.
      Runnable method = Main::ChooseOption;
      // Выполнение метода ChooseOption для отображения опций меню.
      method.run();
      // Создание объекта Scanner для чтения ввода пользователя с консоли.
      Scanner scanner = new Scanner(System.in);
      // Чтение выбора пользователя.
      int choice = scanner.nextInt();

      switch (choice) {
        case 1:
          // Обмен валюты
          // Получение ввода от пользователя через объект userInterface.
          // Метод getUserInput() запрашивает у пользователя информацию о валюте для обмена.
          ExchangeRequest request = userInterface.getUserInput();
          // Выполнение обмена валюты с использованием объекта currencyExchange.
          // Метод performExchange() принимает объект ExchangeRequest и возвращает результат обмена.
          double result = currencyExchange.performExchange(request);
          // Отображение результата обмена пользователю с помощью объекта userInterface.
          // Метод displayResult() выводит результат обмена на экран.
          userInterface.displayResult(result);
          // Прерывание текущего case блока, чтобы перейти к следующей итерации цикла.
          break;

        case 2:
          // Просмотр истории обмена валюты
          // Вывод заголовка "История обменов:" на консоль для указания начала вывода истории обмена.
          System.out.println("История обменов:");
          // Получение объекта истории обмена из объекта currencyExchange с помощью метода
          // getExchangeHistory().
          // Затем вызывается метод displayHistory(), который отображает всю историю обменов на консоль.
          currencyExchange.getExchangeHistory().displayHistory();
          // Прерывание текущего case блока, чтобы перейти к следующей итерации цикла.
          break;

        case 3:
          // Выход из программы
          // Вывод сообщения "Всего доброго!" на консоль перед завершением программы.
          System.out.println("Всего доброго!");
          // Выход из программы с кодом 0.
          System.exit(0);
          // Прерывание текущего case блока для завершения работы switch-конструкции.
          break;
        // Если введенное пользователем значение не соответствует ни одному из вариантов в меню,
        // выводится сообщение об ошибке с просьбой выбрать корректную опцию.
        default:
          System.out.println("Некорректный выбор. Пожалуйста, выберите предлагаемую опцию.");
      }
    }
  }
}
