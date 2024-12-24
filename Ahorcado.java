import java.util.Random;
import java.util.Scanner;

public class Ahorcado {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";

    public static String getWord() {
        String[] words = {"hola", "puerto", "playa", "esternocleidomastoideo"};
        Random random = new Random();
        return words[random.nextInt(words.length)];
    }

    public static String updateProgress(String word, String progress, char guessedLetter) {
        StringBuilder updatedProgress = new StringBuilder(progress);
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guessedLetter) {
                updatedProgress.setCharAt(i, guessedLetter);
            }
        }
        return updatedProgress.toString();
    }

    public static void printHangedMan(int attempts) {
        String[] stages = {
            """
               -----
               |   |
               O   |
              /|\\  |
              / \\  |
                   |
            =========
            """,
            """
               -----
               |   |
               O   |
              /|\\  |
              /    |
                   |
            =========
            """,
            """
               -----
               |   |
               O   |
              /|\\  |
                   |
                   |
            =========
            """,
            """
               -----
               |   |
               O   |
              /|   |
                   |
                   |
            =========
            """,
            """
               -----
               |   |
               O   |
               |   |
                   |
                   |
            =========
            """,
            """
               -----
               |   |
               O   |
                   |
                   |
                   |
            =========
            """,
            """
               -----
               |   |
                   |
                   |
                   |
                   |
            =========
            """
        };
        System.out.println(stages[6 - attempts]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            String word = getWord();
            String progress = "_".repeat(word.length());
            int attempts = 6;
            String guessedLetters = "";

            System.out.println("=====================================");
            System.out.println("     ¡Bienvenido al juego Ahorcado!  ");
            System.out.println("=====================================");
            System.out.println("Adivina la palabra: " + progress);

            while (attempts > 0) {
                if (progress.equals(word)) {
                    System.out.println(GREEN + "¡Felicidades! Has adivinado la palabra: " + word + RESET);
                    break;
                }

                printHangedMan(attempts);
                System.out.println("Progreso actual: " + progress); // Mostrar el progreso actual.
                System.out.print("Introduce una letra: ");
                char guessedLetter = sc.next().charAt(0);

                if (guessedLetters.indexOf(guessedLetter) >= 0) {
                    System.out.println(YELLOW + "Ya has intentado la letra '" + guessedLetter + "'." + RESET);
                    continue;
                }

                guessedLetters += guessedLetter;

                if (word.indexOf(guessedLetter) >= 0) {
                    progress = updateProgress(word, progress, guessedLetter);
                    System.out.println(GREEN + "¡Bien hecho! Palabra: " + progress + RESET);
                } else {
                    attempts--;
                    System.out.println(RED + "Letra incorrecta. Intentos restantes: " + attempts + RESET);
                }

                // Mostrar el progreso actualizado después de un fallo.
                System.out.println("Progreso actual: " + progress); 
            }

            if (!progress.equals(word)) {
                printHangedMan(0);
                System.out.println(RED + "Lo siento, has perdido. La palabra era: " + word + RESET);
            }

            System.out.println("¿Quieres jugar de nuevo? (s/n): ");
        } while (sc.next().charAt(0) == 's');

        System.out.println("¡Gracias por jugar! Hasta la próxima.");
        sc.close();
    }
}
