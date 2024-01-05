package contacts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class that stores program data and main menu methods for accessing, printing, and modifying data
 */
public class App {
    final static Scanner scanner = new Scanner(System.in); // Do not change this line

    // main storage for the App that stores both Person and Organization objects
    ArrayList<Contact> contacts;

    public App() {
        this.contacts = new ArrayList<>();
    }

    public void serialize(String filename) {
        try {
            SerializationUtils.serialize(this.contacts, filename);
        } catch (IOException e) {
            System.err.println("Error occurred during serialization process.");
        }
    }

    public void deserialize(String filename) {
        try {
            this.contacts = (ArrayList<Contact>) SerializationUtils.deserialize(filename);
            System.out.println("open " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error occurred during deserialization process.");
        }
    }

    public void add_contact () {
        System.out.println("Enter the type (person, organization): ");
        String option = scanner.nextLine();
        if (Objects.equals(option, "person")) {
            Person person = new Person();
            System.out.println("Enter the name: ");
            person.setName(scanner.nextLine());
            System.out.println("Enter the surname: ");
            person.setSurname(scanner.nextLine());
            System.out.println("Enter the birth date: ");
            person.setBirthDate(scanner.nextLine());
            System.out.println("Enter the gender: ");
            person.setGender(scanner.nextLine());
            System.out.println("Enter the number: ");
            person.setPhoneNumber(scanner.nextLine());
            contacts.add(person);
        }
        if (Objects.equals(option, "organization")) {
            Organization organization = new Organization();
            System.out.println("Enter the name:");
            organization.setName(scanner.nextLine());
            System.out.println("Enter the address:");
            organization.setAddress(scanner.nextLine());
            System.out.println("Enter the number:");
            organization.setPhoneNumber(scanner.nextLine());
            contacts.add(organization);
        }
        System.out.println("The record added!");
    }

    public int record(Integer index) {
        if (contacts.isEmpty() || index > contacts.size()) {
            System.out.println("Empty Contacts");
            return 3;
        }
        printObject(index);
        System.out.println("\n[record] Enter action (edit, delete, menu): ");
        String choice = "";
        while (!choice.equals("menu")) {
            choice = scanner.nextLine();
            switch (choice) {
                case "edit": {
                    edit_contact(index);
                    break;
                }
                case "delete": {
                    contacts.remove(index);
                }
            }
        }
        return 3;
    }

    public void edit_contact (Integer index) {
        Contact contact = contacts.get(index);
        Contact updated;
            if (contact instanceof Person person) {
                updated = person.edit_person();
            } else {
                Organization organization = (Organization) contact;
                updated = organization.edit_organization();
            }
        contacts.set(index, updated);
            printObject(index);
    }

    public void search_helper() {

        int res = 1;
        while (res == 1) {
            List<Integer> found = search_contacts();
            res = search_action(found);
        }
    }
    public List<Integer>  search_contacts() {
        System.out.println("Enter search query: ");
        List<Integer> found = new ArrayList<>();
        String find = scanner.nextLine();
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).searchString().toLowerCase().contains(find.toLowerCase())) {
                found.add(i);
            }
        }
        System.out.println("Found " + found.size() + " results: ");
        for (int j = 1; j < found.size() + 1; j++) {
            System.out.println(j + ". " + contacts.get(found.get(j - 1)).getName());
        }
        return found;
    }

    public int search_action(List<Integer> found) {
        String choice = "";
        while (!choice.equals("back")) {
            System.out.println("\n[search] Enter action ([number], back, again): ");
            choice = scanner.nextLine();
            switch (choice) {
                case "back": {
                    break;
                }
                case "again": {
                    return 1;
                }
                default: {
                    int intChoice = Integer.parseInt(choice);
                    if (intChoice < 1 || intChoice > found.size()) {
                        System.out.println("Search error");
                        return 0;
                    }
                    Integer index = found.get(intChoice - 1);
                    int res = record(index);
                    if (res == 3) return 0;
                }
        }
        }
        return 0;
    }

    public void get_contact_count() {
        System.out.println("The Phone Book has " + contacts.size() + " records.\n");
    }

    public void list_contacts() {
        int i = 1;
        for (Contact contact: contacts) {
            if (contact instanceof Person person) {
                System.out.println(i++ + ". " + person.toName());
            } else {
                System.out.println(i++ + ". " + contact.name);
            }
        }
        String choice = "";
        System.out.println("\n[list] Enter action ([number], back): ");
        while (choice != "back") {
            choice = scanner.nextLine();
            switch(choice) {
                case "back": return;
                default: {
                    int index = Integer.parseInt(choice);
                    if (index < 1 || index > contacts.size()) {
                        System.out.println("List error");
                        return;
                    }
                    int res = record(index - 1);
                    if (res == 3) return;
                }
            }
        }
    }

    public void printObject(Integer index) {
        Contact contact = contacts.get(index);
        if (contact instanceof Person person) {
            System.out.println(person.printPerson());
        } else {
            Organization organization = (Organization) contact;
            System.out.println(organization.printOrganization());
        }
    }

}
