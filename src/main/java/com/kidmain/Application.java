package com.kidmain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * 1. getWordsList() – load the file {words_aplha.txt} and add word to List.
 * 2. getRandomWord() – get a random word from the {wordsList}.
 * 3. printGameState() - print a current game state (guessed and unguessed letters).
 * 4. isAllLettersGuessed() – return TRUE if all letters of {guessingWord} are guessed.
 * 5. isWordGuessed() – return TRUE if player guessed the {guessingWord}.
 * 6. getPlayerLetterGuess() – ask player write a letter.
 * 7. getPlayerWordGuess() – ask player write a word.
 * 8. start() – public method to start the game.
 */

public class Application {
    private final String pathOfWords = "words_alpha.txt";
    private final String guessingWord = getRandomWord();
    private final List<Character> playerLetterGuesses = new ArrayList<>();
    private String playerWordGuess = "";
    private final String victoryText = "==========\nCONGRATULATIONS!!! YOU HAVE GUESSED THE WORD :)\n==========";

    private List<String> getWordsList() {
        Scanner scanner;
        try {
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


    private boolean isWordGuessed() {
        return playerWordGuess.equals(guessingWord);
    }

    private void getPlayerLetterGuess() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter a letter: ");
        String letterGuess = scanner.nextLine();

        playerLetterGuesses.add(letterGuess.charAt(0));
    }

    private void getPlayerWordGuess() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your guess for the word: ");
        playerWordGuess = scanner.nextLine();
    }

    public void start() {
        while (true) {
            getPlayerLetterGuess();
            if (isAllLettersGuessed()) {
                System.out.println(victoryText);
                break;
            }

            printGameState();

            getPlayerWordGuess();
            if (isWordGuessed()) {
                System.out.println(victoryText);
                break;
            } else {
                System.out.println("Nope :C Nice try.");
            }

            printGameState();
        }
    }

}
