import Manager.StudentsManager;
import MenuPrinter.MenuPrinter;
import Modal.Gender;
import Validattion.Validation;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Gender> genders = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentsManager studentsManager = new StudentsManager();


    static {
        genders.add(new Gender("Male"));
        genders.add(new Gender("Female"));
        genders.add(new Gender("Other"));
    }

    public static void main(String[] args) {
        do {
            MenuPrinter.showHomePage();
            String choice = "";
            do {
                choice = scanner.nextLine();
                if (!Validation.checkHomePageOption(choice)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkHomePageOption(choice));
            int choiceInput = Integer.parseInt(choice);
            switch (choiceInput) {
                case 1:
                    studentsManager.display();
                    break;
                case 2:
                    studentsManager.add(genders);
                    break;
                case 3:
                    studentsManager.update(genders);
                    break;
                case 4:
                    studentsManager.delete();
                    break;
                case 5:
                    activeSortActivities(scanner);
                    break;
                case 6:
                    studentsManager.getFile().readFromFile("src/Data/students.csv");
                    System.out.println("Read file successfully!");
                    break;
                case 7:
                    studentsManager.getFile().writeToFile(studentsManager.getStudents(), "src/Data/students.csv");
                    System.out.println("Write file successfully!");
                    break;
                case 8:
                    System.exit(0);
                    break;
            }
        } while (true);
    }

    private static void activeSortActivities(Scanner scanner) {
        do {
            MenuPrinter.showSortPage();
            String choice = "";
            do {
                choice = scanner.nextLine();
                if (!Validation.checkHomePageOption(choice)) {
                    MenuPrinter.wrongInput();
                }
            } while (!Validation.checkHomePageOption(choice));
            int choiceInput = Integer.parseInt(choice);
            switch (choiceInput) {
                case 1:
                    studentsManager.sortByIncreaseAvg();
                    break;
                case 2:
                    studentsManager.sortByDecreaseAvg();
                    break;
                case 3:
                    return;
            }
        } while (true);
    }

}

