import java.util.Random;
import java.util.Scanner;

public class EnhancedPasswordGenerator {

    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIALS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ГЕНЕРАТОР ПАРОЛЕЙ");

        while (true) {

            int length = getPasswordLength(scanner);
            if (length == 0) break;

            if (length < 8) {
                System.out.println("Ошибка! Мы генерируем пароли длиной от 8 до 16");
                continue;
            }

            generateAndDisplayPassword(length);
        }

        System.out.println("\nСпасибо за использование!");
        scanner.close();
    }


    public static void generateAndDisplayPassword(int length) {
        System.out.println("\nСгенерированный пароль (длина: " + length + "):");

        String password = generateEnhancedPassword(length);
        System.out.println("Пароль: " + password);
    }


    public static String generateEnhancedPassword(int length) {

        int guaranteedChars = 8;

        if (length < guaranteedChars) {
            throw new IllegalArgumentException("Длина пароля должна быть не менее 8 символов");
        }

        char[] password = new char[length];
        int index = 0;

        for (int i = 0; i < 2; i++) {
            password[index++] = getRandomChar(LOWERCASE);
        }

        for (int i = 0; i < 2; i++) {
            password[index++] = getRandomChar(UPPERCASE);
        }

        for (int i = 0; i < 2; i++) {
            password[index++] = getRandomChar(DIGITS);
        }

        for (int i = 0; i < 2; i++) {
            password[index++] = getRandomChar(SPECIALS);
        }

        String allCharacters = LOWERCASE + UPPERCASE + DIGITS + SPECIALS;
        for (int i = index; i < length; i++) {
            password[i] = getRandomChar(allCharacters);
        }

        enhancedShuffle(password);

        return new String(password);
    }


    private static void enhancedShuffle(char[] array) {

        for (int pass = 0; pass < 3; pass++) {
            for (int i = array.length - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                char temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
    }


    private static char getRandomChar(String characterSet) {
        return characterSet.charAt(random.nextInt(characterSet.length()));
    }


    public static int getPasswordLength(Scanner scanner) {
        while (true) {
            System.out.print("Выберите длину пароля (8-16) или 0 для выхода: ");
            String input = scanner.nextLine().trim();

            try {
                int length = Integer.parseInt(input);

                if (length == 0) return 0;
                if (length >= 8 && length <= 16) return length;

                System.out.println("Длина должна быть от 8 до 16 символов!");
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное число!");
            }
        }
    }
}