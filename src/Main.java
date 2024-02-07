public class Main {


    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        CurrencyExchange currencyExchange = new CurrencyExchange();
        ExchangeHistory exchangeHistory = new ExchangeHistory();

        while (true) {
            ExchangeRequest request = userInterface.getUserInput();
            double result = currencyExchange.performExchange(request);
            userInterface.displayResult(result);

            // Сохранение обмена в истории
            ExchangeRecord exchangeRecord = new ExchangeRecord(request.getAmount(),
                    request.getFromCurrency(), request.getToCurrency());
            exchangeHistory.addRecord(exchangeRecord);

            System.out.println("Хотите продолжить (y/n)?");
            String continueOption = userInterface.getScanner().next();

            if (!"y".equalsIgnoreCase(continueOption)) {
                break;
            }
        }

        // Вывод истории обменов
        System.out.println("\nИстория обменов:");
        exchangeHistory.displayHistory();
    }
}

