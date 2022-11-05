package com.kidmain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Application {
    private final String pathOfWords = "words_alpha.txt";
    private final String correctWord = getRandomWord();
    private final List<Character> playerGuesses = new ArrayList<>();

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

    private void printWordState() {
        for (int i = 0; i < correctWord.length(); i++) {
            if (playerGuesses.contains(correctWord.charAt(i))) {
                System.out.print(correctWord.charAt(i));
            } else {
                System.out.print("-");
            }
        }
        System.out.println();
    }

    public void getPlayerGuess() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a letter: ");

        String letterGuess = scanner.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        printWordState();
    }

}
