package contacts;

import java.util.Scanner;

public class Main {
    final static Scanner scanner = new Scanner(System.in); // Do not change this line
    public static void main(String[] args) {
        // main class that stores data and executes methods
        App app = new App();

        // if a CMD line arg is present, open the file and deserialize the contents
        if (args.length == 1) {
            app.deserialize(args[0]);
        }

        // Main menu that calls sub-menus for data retrieval and modification
        String option = "";
        while (!option.equals("exit")) {
            System.out.println("\n[menu] Enter action (add, list, search, count, exit): ");
            option = scanner.nextLine();
            switch (option) {
                case "add":
                    app.add_contact();
                    break;
                case "list":
                    app.list_contacts();
                    break;
                case "search":
                    app.search_helper();
                    break;
                case "count":
                    app.get_contact_count();
                    break;
            }
        }
        // if a CMD line arg is present, save the records upon exiting
        if (args.length == 1) {
            app.serialize(args[0]);
        }
    }
}
