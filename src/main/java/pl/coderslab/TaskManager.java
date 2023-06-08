package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    static final String FILE_NAME = "tasks.csv";
    static final String[] OPTIONS = {"add", "remove", "list", "exit"};
    static String[][] tasks;
    public static void main(String[] args) {
        tasks = getData(FILE_NAME);
        printOptions(OPTIONS);
        optionSelector();

    }

    public static void printOptions(String[] tab) {
        System.out.println(ConsoleColors.BLUE + "Please select an option: ");
        System.out.println(ConsoleColors.RESET);
        for (String option : tab) {
            System.out.println(option);
        }
    }

    public static void optionSelector() {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String input = scan.nextLine();
            switch (input)  {
                case "list" :
                    list(tasks);
                    break;
                case "add" :
                    add();
                    break;
                default:
                    System.out.println("Please select a correct option. ");
            }
            printOptions(OPTIONS);
        }
    }

    public static String[][] getData(String fileName) {
        Path dir = Paths.get(fileName);
        if (!Files.exists(dir)) {
            System.out.println("File doesn't exists.");
            System.exit(0);
        }
        String[][] lines = null;
        try {
            List<String> listOfStrings = Files.readAllLines(dir);
            lines = new String[listOfStrings.size()][listOfStrings.get(0).split(",").length];

            for (int i = 0; i < listOfStrings.size(); i++) {
                String[] split = listOfStrings.get(i).split(",");
                for (int j = 0; j < split.length; j++) {
                    lines[i][j] = split[j];
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return lines;
        }

    public static void list(String[][] tab) {
       for (int i = 0; i < tab.length; i++) {
           System.out.println(i + " : ");
           for(int j = 0; j < tab[i].length; j++) {
               System.out.println(tab[i][j] + " ");
           }
           System.out.println();
       }
    }

    public static void add() {
    Scanner scan = new Scanner(System.in);
    String[] listArr = new String[3];
        System.out.println("Please add task description: ");
        listArr[0] = scan.nextLine();
        System.out.println("Please add task due date: ");
        listArr[1] = scan.nextLine();
        System.out.println("Is your task is important: true/false: ");
        listArr[2] = scan.nextLine();

        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        tasks[tasks.length -1] = new String[3];
        tasks[tasks.length -1][0] = listArr[0];
        tasks[tasks.length -1][1] = listArr[1];
        tasks[tasks.length -1][2] = listArr[2];
        }
}