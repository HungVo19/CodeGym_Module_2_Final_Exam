package MenuPrinter;

import de.vandermeer.asciitable.AT_Row;
import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.asciitable.CWC_LongestWordMin;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import java.io.Serializable;

public class MenuPrinter implements Serializable {

    public static void showHomePage() {
        AsciiTable atHeader1 = new AsciiTable();
        atHeader1.addRule();
        AT_Row row1 = atHeader1.addRow("STUDENTS MANAGER SYSTEM");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader1.addRule();
        System.out.println(atHeader1.render(40));

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("1", "Display students list");
        at.addRule();
        at.addRow("2", "Add new student");
        at.addRule();
        at.addRow("3", "Update student information");
        at.addRule();
        at.addRow("4", "Delete student");
        at.addRule();
        at.addRow("5", "Sort list");
        at.addRule();
        at.addRow("6", "Read from file");
        at.addRule();
        at.addRow("7", "Write to file");
        at.addRule();
        at.addRow("8", "Exit");
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestWordMin(new int[]{-1, 36}));
        String rend = at.render();
        System.out.println(rend);
        System.out.print("☛ Enter your choice: ");
    }

    public static void showGenders() {
        AsciiTable atHeader1 = new AsciiTable();
        atHeader1.addRule();
        AT_Row row1 = atHeader1.addRow("GENDER OPTION");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader1.addRule();
        System.out.println(atHeader1.render(40));

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("1", "Male");
        at.addRule();
        at.addRow("2", "Female");
        at.addRule();
        at.addRow("3", "Other");
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestWordMin(new int[]{-1, 36}));
        String rend = at.render();
        System.out.println(rend);
        System.out.print("☛ Enter your choice: ");
    }

    public static void wrongInput() {
        System.out.println("⛔ Wrong input. Try again");
    }

    public static void showSortPage() {
        AsciiTable atHeader1 = new AsciiTable();
        atHeader1.addRule();
        AT_Row row1 = atHeader1.addRow("SORT FUNCTION");
        row1.getCells().get(0).getContext().setTextAlignment(TextAlignment.CENTER);
        atHeader1.addRule();
        System.out.println(atHeader1.render(40));

        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("1", "Increase Average Mark");
        at.addRule();
        at.addRow("2", "Decrease Average Mark");
        at.addRule();
        at.addRow("3", "Exit");
        at.addRule();
        at.getRenderer().setCWC(new CWC_LongestWordMin(new int[]{-1, 36}));
        String rend = at.render();
        System.out.println(rend);
        System.out.print("☛ Enter your choice: ");
    }
}

