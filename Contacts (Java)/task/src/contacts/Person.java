package contacts;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class Person extends Contact {
    @Serial
    private static final long serialVersionUID = 5999L;

    public Person(){super();}
    public Person(String name, String surname, String birth_date, String gender, String phoneNumber) {
        super(name, surname, birth_date, gender, phoneNumber);
    }

    public Contact edit_person() {
        System.out.println("Select a field (name, surname, birth, gender, number): ");
        String option = "";
        option = scanner.nextLine();
        System.out.println("Enter " + option + ": ");
        String update = scanner.nextLine();
        switch (option) {
            case "name":
                this.setName(update);
                break;
            case "surname":
                this.setSurname(update);
                break;
            case "birth":
                this.setBirthDate(update);
                break;
            case "gender":
                this.setGender(update);
                break;
            case "number":
                this.setPhoneNumber(update);
                break;
        }
        this.edited = LocalDateTime.now();
        System.out.println("Saved");
        return this;
    }

    // used for serialization/deserialization process - do not modify
    @Override
    public String toString() {
        return "Person{" +
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

    // replacement toString method to print Person objects
    public String printPerson() {
        return "Name: " + this.name + "\n" +
                "Surname: " + this.surname + "\n" +
                "Birth date: " + this.birth_date + "\n" +
                "Gender: " + this.gender + "\n" +
                "Number: " + this.phoneNumber + "\n" +
                "Time created:: " + this.created.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")) + "\n" +
                "Time last edit: " + this.edited.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
    }

    public String toName() {
        return this.name + " " + this.surname;
    }
}
