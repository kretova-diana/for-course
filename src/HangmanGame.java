import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    private static final ArrayList<String> wordList = new ArrayList<>();

    static {
        wordList.add("яблоко");
        wordList.add("лимон");
        wordList.add("мандарин");
        wordList.add("крот");
        wordList.add("кость");
        wordList.add("дерево");
        wordList.add("трава");
        wordList.add("автомобиль");
        wordList.add("зерно");
        wordList.add("медведь");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        Random random = new Random();

        String randomWord = wordList.get(random.nextInt(wordList.size()));
        char[] lettersArray = randomWord.toCharArray();
        char[] starsArray = new char[randomWord.length()];

        for (int i = 0; i < starsArray.length; i++) {
            starsArray[i] = '*';
        }

        int attempts = 0;
        int maxAttempts = 7;

        System.out.println("ИГРА 'ВИСЕЛИЦА' !!!");
        System.out.println("Угадайте слово! У вас " + maxAttempts + " попыток.");


        while (attempts < maxAttempts) {
            System.out.println("\nСлово: " + new String(starsArray));
            System.out.print("Введите букву: ");
            String input = scanner.nextLine().toLowerCase();


            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Ошибка! Пожалуйста, введите одну букву!");
                continue;
            }

            char userLetter = input.charAt(0);
            boolean letterFound = false;


            for (int i = 0; i < lettersArray.length; i++) {
                if (lettersArray[i] == userLetter && starsArray[i] == '*') {
                    starsArray[i] = userLetter;
                    letterFound = true;
                }
            }

            if (letterFound) {
                System.out.println("Супер! Буква '" + userLetter + "' есть в слове!");

                if (isWordGuessed(starsArray)) {
                    System.out.println("\n ПОБЕДА! Вы угадали слово: " + randomWord);
                    break;
                }
            } else {
                attempts++;
                System.out.println("Буквы '" + userLetter + "' нет в слове!");
                System.out.println("Осталось попыток: " + (maxAttempts - attempts));
            }
        }

        if (attempts >= maxAttempts) {
            System.out.println("\nПопытки закончились! Загаданное слово: " + randomWord);
        }

        scanner.close();
    }

    // Проверка, угадано ли все слово
    public static boolean isWordGuessed(char[] starsArray) {
        for (char c : starsArray) {
            if (c == '*') {
                return false;
            }
        }
        return true;
    }
}
