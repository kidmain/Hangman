package com.kidmain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * 1. getWordsList() – load the file {alpha.txt} and add word to List.
 * 2. getRandomWord() – get a random word from the {wordsList}.
 * 3. printGameState() - print a current game state (guessed and unguessed letters).
 * 4. isAllLettersGuessed() – return TRUE if all letters of {guessingWord} are guessed.
 * 5. isWordGuessed() – return TRUE if player guessed the {guessingWord}.
 * 6. isPlayerGuessedLetter() – ask player write a letter and count wrong guessed letters.
 * 7. isPlayerGuessedWord() – ask player write a word.
 * 8. start() – public method to start the game with victory/defeat texts.
 */

public class Application {
    private final String guessingWord = getRandomWord();
    private final List<Character> playerLetterGuesses = new ArrayList<>();
    private int wrongLetterGuesses = 0;

    private List<String> getWordsList() {
        Scanner scanner;
        try {
            String pathOfWords = "words_alpha.txt";
            scanner = new Scanner(new File(pathOfWords));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        List<String> wordsList = new ArrayList<>();
        while (scanner.hasNext()) {
            wordsList.add(scanner.nextLine());
        }
        return wordsList;
    }

    private String getRandomWord() {
        List<String> wordsList = getWordsList();
        Random random = new Random();
        return wordsList.get(random.nextInt(wordsList.size()));
    }

    private void printGameState() {
        char[] guessingWordCharArray = guessingWord.toCharArray();
        for (char ch : guessingWordCharArray) {
            if (playerLetterGuesses.contains(ch)) {
                System.out.print(ch);
            } else {
                System.out.print("-");
            }
        }

        System.out.println("\n _____");
        System.out.println(" |   |");

        if (wrongLetterGuesses >= 1) {
            System.out.println(" O   ");
        }
        if (wrongLetterGuesses >= 2) {
            System.out.print("\\");
        }
        if (wrongLetterGuesses >= 3) {
            System.out.println(" /");
        }
        if (wrongLetterGuesses >= 4) {
            System.out.println(" |   ");
        }
        if (wrongLetterGuesses >= 5) {
            System.out.print("/");
        }
        if (wrongLetterGuesses >= 6) {
            System.out.println(" \\");
        }

        System.out.println();
    }

    private boolean isAllLettersGuessed() {
        int guessingWordLength = guessingWord.length();
        int guessedLettersCount = (int) IntStream
                .range(0, guessingWordLength)
                .filter(i -> playerLetterGuesses.contains(guessingWord.charAt(i)))
                .count();

        return guessedLettersCount == guessingWordLength;
    }

    private boolean isPlayerGuessedLetter() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a letter: ");
        String letterGuess = scanner.nextLine();

        playerLetterGuesses.add(letterGuess.charAt(0));

        return guessingWord.contains(letterGuess);
    }

    private boolean isPlayerGuessedWord() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your guess for the word: ");
        String playerWordGuess = scanner.nextLine();

        return playerWordGuess.equals(guessingWord);
    }

    public void start() {
        String victoryText = "==========\nCONGRATULATIONS!!! YOU HAVE GUESSED THE WORD :)\n==========";
        String defeatText = "==========\nYOU HAVE LOST :C TRY AGAIN\n==========";
        while (true) {
            if (!isPlayerGuessedLetter()) {
                wrongLetterGuesses++;
                if (wrongLetterGuesses == 6) {
                    printGameState();
                    System.out.println(defeatText);
                    break;
                }
            }

            if (isAllLettersGuessed()) {
                printGameState();
                System.out.println(victoryText);
                break;
            }

            printGameState();

            if (isPlayerGuessedWord()) {
                printGameState();
                System.out.println(victoryText);
                break;
            } else {
                printGameState();
                System.out.println("Nope :C Nice try.\n");
            }
        }
    }

}
