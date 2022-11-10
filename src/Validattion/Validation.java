package Validattion;

import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation implements Serializable {
    private final static String homePageOptionRegex = "^[1-8]$";

    private final static String integerRegex = "^[0-9]+$";
    private final static String gendersChoiceRegex = "^[1-3]$";
    private final static String sortChoiceRegex = "^[1-3]$";

    public static boolean checkHomePageOption (String input) {
        Pattern pattern = Pattern.compile(homePageOptionRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
    public static boolean checkInteger(String input) {
        Pattern pattern = Pattern.compile(integerRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static Double parseDouble(String myString) {
        final String Digits = "(\\p{Digit}+)";
        final String HexDigits = "(\\p{XDigit}+)";

        final String Exp = "[eE][+-]?" + Digits;
        final String fpRegex = ("[\\x00-\\x20]*" +
                "[+-]?(" +
                "NaN|" +
                "Infinity|" +

                "(((" + Digits + "(\\.)?(" + Digits + "?)(" + Exp + ")?)|" +
                "(\\.(" + Digits + ")(" + Exp + ")?)|" +
                "((" +
                "(0[xX]" + HexDigits + "(\\.)?)|" +

                "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +
                ")[pP][+-]?" + Digits + "))" + "[fFdD]?))" + "[\\x00-\\x20]*");
        if (Pattern.matches(fpRegex, myString))
            return Double.valueOf(myString);
        else {
            return null;
        }
    }

    public static boolean checkYN(String question) {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.print(question);
            input = scanner.next().trim().toUpperCase();
        } while (!input.matches("[YN]"));
        return input.equalsIgnoreCase("Y");
    }

    public static boolean checkGenderChoice(String input) {
        Pattern pattern = Pattern.compile(gendersChoiceRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public static boolean checkSortChoice(String input) {
        Pattern pattern = Pattern.compile(sortChoiceRegex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
