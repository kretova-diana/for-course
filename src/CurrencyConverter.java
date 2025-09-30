import java.util.Scanner;

public class CurrencyConverter {

    private static final double USD_TO_EUR = 0.85;
    private static final double USD_TO_GBP = 0.73;
    private static final double USD_TO_JPY = 110.25;
    private static final double USD_TO_RUB = 74.50;
    private static final double USD_TO_CNY = 6.45;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("КОНВЕРТЕР ВАЛЮТ");
        System.out.println("Доступные валюты:");
        System.out.println("1. USD - Доллар США");
        System.out.println("2. EUR - Евро");
        System.out.println("3. GBP - Фунт стерлингов");
        System.out.println("4. JPY - Японская йена");
        System.out.println("5. RUB - Российский рубль");
        System.out.println("6. CNY - Китайский юань");

        while (true) {
            System.out.print("Введите исходную валюту (1-6) или 0 для выхода: ");

            int sourceCurrency = readIntSafe(scanner);

            if (sourceCurrency == 0) {
                System.out.println("Программа закончена!");
                break;
            }

            if (sourceCurrency < 1 || sourceCurrency > 6) {
                System.out.println("Ошибка: выберите валюту из списка от 1 до 6!");
                continue;
            }

            System.out.print("Введите сумму для конвертации: ");

            double amount = readDoubleSafe(scanner);

            if (amount <= 0) {
                System.out.println("Ошибка: сумма должна быть положительной!");
                continue;
            }

            System.out.println("\nРезультаты конвертации:");
            System.out.printf("Исходная сумма: %.2f %s%n", amount, getCurrencyName(sourceCurrency));

            for (int targetCurrency = 1; targetCurrency <= 6; targetCurrency++) {
                if (targetCurrency != sourceCurrency) {
                    double convertedAmount = convertCurrency(amount, sourceCurrency, targetCurrency);
                    System.out.printf("%s: %.2f%n", getCurrencyName(targetCurrency), convertedAmount);
                }
            }
        }

        scanner.close();
    }

    public static int readIntSafe(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите целое число.");
                System.out.print("Попробуйте снова: ");
            }
        }
    }

    public static double readDoubleSafe(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите число.");
                System.out.print("Попробуйте снова: ");
            }
        }
    }

    public static double convertCurrency(double amount, int fromCurrency, int toCurrency) {

        double amountInUSD = convertToUSD(amount, fromCurrency);
        return convertFromUSD(amountInUSD, toCurrency);
    }

    public static double convertToUSD(double amount, int currency) {
        switch (currency) {
            case 1: return amount;
            case 2: return amount / USD_TO_EUR;
            case 3: return amount / USD_TO_GBP;
            case 4: return amount / USD_TO_JPY;
            case 5: return amount / USD_TO_RUB;
            case 6: return amount / USD_TO_CNY;
            default: return 0;
        }
    }


    public static double convertFromUSD(double amountInUSD, int currency) {
        switch (currency) {
            case 1: return amountInUSD;
            case 2: return amountInUSD * USD_TO_EUR;
            case 3: return amountInUSD * USD_TO_GBP;
            case 4: return amountInUSD * USD_TO_JPY;
            case 5: return amountInUSD * USD_TO_RUB;
            case 6: return amountInUSD * USD_TO_CNY;
            default: return 0;
        }
    }

    public static String getCurrencyName(int currency) {
        switch (currency) {
            case 1: return "USD";
            case 2: return "EUR";
            case 3: return "GBP";
            case 4: return "JPY";
            case 5: return "RUB";
            case 6: return "CNY";
            default: return "UNKNOWN";
        }
    }
}