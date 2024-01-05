package contacts;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Organization
 */
public class Organization extends Contact {
    @Serial
    private static final long serialVersionUID = 600L;
    public Organization(){super();}
    public Organization(String name, String address, String phoneNumber) {
        super(name, address, phoneNumber);
    }

    // method that allows editing of the Organization
    public Contact edit_organization() {
        System.out.println("Select a field (address, number: ");
        String option = "";
        option = scanner.nextLine();
        System.out.println("Enter " + option + ": ");
        String update = scanner.nextLine();
        switch (option) {
            case "address":
                this.setAddress(update);
                break;
            case "number":
                this.setPhoneNumber(update);
                break;
        }
        this.edited = LocalDateTime.now();
        System.out.println("The record updated!\n");
        return this;
    }

    // used for serialization/deserialization process - do not modify
    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", gender='" + gender + '\'' +
                ", created=" + created +
                ", edited=" + edited +
                '}';
    }

    // replacement toString method to print Organization objects
    public String printOrganization() {
        return "Organization name: " + this.name + "\n" +
                "Address: " + this.address + "\n" +
                "Number: " + this.phoneNumber + "\n" +
                "Time created: " + this.created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) + "\n" +
                "Time last edit: " + this.edited.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
    }

}
