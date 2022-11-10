package Manager;

import IOFile.IOFile;
import MenuPrinter.MenuPrinter;
import Modal.Gender;
import Modal.Student;
import Validattion.Validation;
import com.jakewharton.fliptables.FlipTable;
import com.jakewharton.fliptables.FlipTableConverters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentsManager implements Serializable {
    private final IOFile<Student> file;
    private ArrayList<Student> students;
    private final String filePath = "src/Data/students.csv";

    public StudentsManager() {
        file = new IOFile<Student>();
        students = (ArrayList<Student>) file.readFromFile(filePath);
        resetStaticIndex();
    }

    public IOFile<Student> getFile() {
        return file;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void add(ArrayList<Gender> genders) {
        Scanner scanner = new Scanner(System.in);
        String studentID = "";
        do {
            System.out.println("Enter student id: ");
            studentID = scanner.nextLine();
            if (studentID.isEmpty() || !checkStudentIdExist(studentID)) {
                System.out.println("Id is already existed. Try again!");
            }
        } while (studentID.isEmpty() || !checkStudentIdExist(studentID));

        String name = "";
        do {
            System.out.println("Enter name");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty!");
            }
        } while (name.isEmpty());

        Integer age = null;
        String tempAge = "";
        do {
            System.out.println("⏩ Enter age:");
            tempAge = scanner.nextLine();
            if (tempAge.isEmpty()) {
                age = 0;
                break;
            } else if (Validation.checkInteger(tempAge)) {
                age = Integer.parseInt(tempAge);
                break;
            } else {
                MenuPrinter.wrongInput();
            }
        } while (!Validation.checkInteger(tempAge));

        String choice = "";
        Gender gender = null;
        System.out.println("Choose gender");
        do {
            MenuPrinter.showGenders();
            choice = scanner.nextLine();
            if (!Validation.checkGenderChoice(choice)) {
                MenuPrinter.wrongInput();
            }
        } while (!Validation.checkGenderChoice(choice));
        switch (Integer.parseInt(choice)) {
            case 1:
                gender = genders.get(0);
                break;
            case 2:
                gender = genders.get(1);
                break;
            case 3:
                gender = genders.get(2);
                break;
        }

        System.out.println("Enter address");
        String address = scanner.nextLine();

        Double avgMark = null;
        String tempAvgMark = "";
        do {
            System.out.println("⏩ Enter average mark of student");
            tempAvgMark = scanner.nextLine();
            if (tempAvgMark.isEmpty()) {
                avgMark = 0.0;
                break;
            } else if (Validation.parseDouble(tempAvgMark) != null) {
                avgMark = Double.parseDouble(tempAvgMark);
                break;
            } else {
                MenuPrinter.wrongInput();
            }
        } while (Validation.parseDouble(tempAvgMark) == null);

        System.out.println("New student is added successfully!");
        students.add(new Student(studentID, name, age, gender, address, avgMark));
        file.writeToFile(students, "src/Data/students.csv");
    }

    public void update(ArrayList<Gender> genders) {
        Scanner scanner = new Scanner(System.in);
        display();
        String input = "";
        do {
            System.out.println("Enter index of product to update: ");
            input = scanner.nextLine();
            if (!Validation.checkInteger(input)) {
                MenuPrinter.wrongInput();
            } else if (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= students.size()) {
                System.out.println("That index is not found");
            } else if (input.isEmpty()) {
                return;
            }
        } while (!Validation.checkInteger(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= students.size());
        int index = Integer.parseInt(input);

        String studentId = "";
        do {
            System.out.println("Enter student id: ");
            studentId = scanner.nextLine();
            if (studentId.isEmpty()) {
                studentId = students.get(index).getStudentId();
                break;
            } else if (!checkStudentIdExist(studentId)) {
                System.out.println("This student ID is already existed. Try again!");
            }
        } while (!checkStudentIdExist(studentId));
        students.get(index).setStudentId(studentId);

        String name = "";
        do {
            System.out.println("Enter name");
            name = scanner.nextLine();
            if (name.isEmpty()) {
                name = students.get(index).getName();
            }
        } while (name.isEmpty());
        students.get(index).setName(name);

        Integer age = null;
        String tempAge = "";
        do {
            System.out.println("⏩ Enter age:");
            tempAge = scanner.nextLine();
            if (tempAge.isEmpty()) {
                age = students.get(index).getAge();
                break;
            } else if (Validation.checkInteger(tempAge)) {
                age = Integer.parseInt(tempAge);
                break;
            } else {
                MenuPrinter.wrongInput();
            }
        } while (!Validation.checkInteger(tempAge));
        students.get(index).setAge(age);

        String choice = "";
        Gender gender = null;
        System.out.println("Choose gender");
        do {
            MenuPrinter.showGenders();
            choice = scanner.nextLine();
            if (!Validation.checkGenderChoice(choice)) {
                MenuPrinter.wrongInput();
            }
        } while (!Validation.checkGenderChoice(choice));
        switch (Integer.parseInt(choice)) {
            case 1:
                students.get(index).setGender(genders.get(0));
                break;
            case 2:
                students.get(index).setGender(genders.get(1));
                break;
            case 3:
                students.get(index).setGender(genders.get(2));
                break;
        }

        System.out.println("Enter address");
        String address = scanner.nextLine();
        if (address.isEmpty()) {
            address = students.get(index).getAddress();
        }
        students.get(index).setAddress(address);

        Double avgMark = null;
        String tempAvgMark = "";
        do {
            System.out.println("⏩ Enter average mark of student");
            tempAvgMark = scanner.nextLine();
            if (tempAvgMark.isEmpty()) {
                avgMark = students.get(index).getAverageMark();
                break;
            } else if (Validation.parseDouble(tempAvgMark) != null) {
                avgMark = Double.parseDouble(tempAvgMark);
                break;
            } else {
                MenuPrinter.wrongInput();
            }
        } while (Validation.parseDouble(tempAvgMark) == null);
        students.get(index).setAverageMark(avgMark);
        file.writeToFile(students, "src/Data/students.csv");
    }

    public void delete() {
        Scanner scanner = new Scanner(System.in);
        display();
        String input = "";
        do {
            System.out.println("⏩ Enter index of student to delete: ");
            input = scanner.nextLine();
            if (!Validation.checkInteger(input)) {
                MenuPrinter.wrongInput();
            } else if (Integer.parseInt(input) < 0 || Integer.parseInt(input) >= students.size()) {
                System.out.println("That index is not found!");
            }
        } while (!Validation.checkInteger(input) || Integer.parseInt(input) < 0 || Integer.parseInt(input) >= students.size());
        int index = Integer.parseInt(input);
        if (!Validation.checkYN("Are you sure to delete this student (Y/N)")) {
            return;
        } else {
            System.out.println("Student is removed from list successfully!");
            students.remove(index);
        }
        file.writeToFile(students, "src/Data/students.csv");
    }

    public void sortByIncreaseAvg() {
//        file.readFromFile("src/Data/students.csv");
        Collections.sort(students, Comparator.comparingDouble(Student::getAverageMark));
        display(students);
    }

    public void sortByDecreaseAvg() {
//        file.readFromFile("src/Data/students.csv");
        ArrayList<Student> list = new ArrayList<>();
        list = (ArrayList<Student>) students.clone();
        list.sort(Comparator.comparing(Student::getAverageMark));
//        String[] headers = {"Student ID", "Name", "Age", "Gender", "Address", "Average Mark"};
//        Object[][] data = new Object[list.size()][7];
//        for (int i = list.size() - 1; i >= 0; i--) {
//            data[i] = new Object[]{String.valueOf(list.get(i).getStudentId()), list.get(i).getName(), String.valueOf(list.get(i).getAge()), list.get(i).getGender().getStatus(), list.get(i).getAddress(), String.valueOf(list.get(i).getAverageMark())};
//        }
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.println(list.get(i));
        }
    }

    private boolean checkStudentIdExist(String id) {
        boolean flag = true;
        for (Student p : students) {
            if (p.getStudentId().equals(id)) {
                flag = false;
                break;
            }
        }
        return flag;
    }


    public void display() {
        file.readFromFile("src/Data/students.csv");
        String[] headers = {"Index", "Student ID", "Name", "Age", "Gender", "Address", "Average Mark"};
        if (students.isEmpty()) {
            String[][] data = new String[][]{
                    {"NA", "NA", "NA", "NA", "NA", "NA", "NA"}
            };
            System.out.println(FlipTable.of(headers, data));
        } else {
            Object[][] data = new Object[students.size()][7];
            for (int i = 0; i < students.size(); i++) {
                data[i] = new Object[]{i, String.valueOf(students.get(i).getStudentId()), students.get(i).getName(), String.valueOf(students.get(i).getAge()), students.get(i).getGender().getStatus(), students.get(i).getAddress(), String.valueOf(students.get(i).getAverageMark())};
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        }
    }

    public void display(ArrayList<Student> students) {
        this.students = (ArrayList<Student>) file.readFromFile(filePath);
        String[] headers = {"Index", "Student ID", "Name", "Age", "Gender", "Address", "Average Mark"};
        if (students.isEmpty()) {
            String[][] data = new String[][]{
                    {"NA", "NA", "NA", "NA", "NA", "NA", "NA"}
            };
            System.out.println(FlipTable.of(headers, data));
        } else {
            Object[][] data = new Object[students.size()][7];
            for (int i = 0; i < students.size(); i++) {
                data[i] = new Object[]{i, String.valueOf(students.get(i).getStudentId()), students.get(i).getName(), String.valueOf(students.get(i).getAge()), students.get(i).getGender().getStatus(), students.get(i).getAddress(), String.valueOf(students.get(i).getAverageMark())};
            }
            System.out.println(FlipTableConverters.fromObjects(headers, data));
        }
    }

    private void resetStaticIndex() {
        if (!students.isEmpty()) {
            Student.INDEX = students.get(students.size() - 1).getId();
        }
    }
}
