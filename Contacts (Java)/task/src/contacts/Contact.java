package contacts;


import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

/**
 * Abstract class that stores information and provides methods to both Person and Organization objects
 */
public abstract class Contact implements Serializable {

    private static final long serialVersionUID = 2765113488337953265L;
    final static Scanner scanner = new Scanner(System.in); // Do not change this line
    String name;
    String surname;
    String phoneNumber;
    String address;
    String birth_date;
    String gender;
    LocalDateTime created;
    LocalDateTime edited;

    public Contact() {
        this.created = LocalDateTime.now();
    }

    public String getName() {
        if (surname != null) {
            return name + " " + surname;
        }
        return name;
    }

    public Contact(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        setPhoneNumber(phoneNumber);
        this.created = LocalDateTime.now();
    }

    public Contact(String name, String surname, String birth_date, String gender, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        setBirthDate(birth_date);
        setGender(gender);
        setPhoneNumber(phoneNumber);
        this.created = LocalDateTime.now();
    }

    public void setName(String name) {
        this.name = name;
        this.edited = LocalDateTime.now();
    }

    public void setSurname(String surname) {
        this.surname = surname;
        this.edited = LocalDateTime.now();
    }

    public void setAddress(String address) {
        this.address = address;
        this.edited = LocalDateTime.now();
    }

    public void setBirthDate(String string_date) {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date;
        try {
            date = df.parse(string_date);
            this.birth_date = string_date;
        } catch (ParseException e) {
            this.birth_date = "[no data]";
            System.out.println("Bad birth date!");
        }
    }

    public void setGender(String gender) {
        if (Objects.equals(gender, "M") | Objects.equals(gender, "F")) {
            this.gender = gender;
        } else {
            this.gender = "[no data]";
            System.out.println("Bad gender!");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (checkPhoneNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "[no number]";
            System.out.println("Wrong number format!");

        }
        this.edited = LocalDateTime.now();
    }

    // used to provide searchable string data for all fields
    public String searchString() {
        return name + " " + surname + " " + phoneNumber + " " + address + " " + birth_date + " " + gender;
    }

    @Override
    public String toString() {
        return "Contact{" +
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

    private boolean checkPhoneNumber(String phoneNumber) {
        String regex = "\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*";
        return phoneNumber.matches(regex);
    }
}
