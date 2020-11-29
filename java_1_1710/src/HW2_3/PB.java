package HW2_3;


import java.util.Set;

public class PB {
    public static void main(String[] args) {
        IPhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Иванов", "123-45-67");
        phoneBook.add("Иванов", "123-45-68");
        phoneBook.add("Петров", "123-45-69");
        phoneBook.add("Сидоров", "123-45-10");
        phoneBook.add("Кузнецов", "123-45-11");

        Set<String> allSurnames = phoneBook.getAllSurnames();
        for (String surname : allSurnames) {
            Set<String> phones = phoneBook.get(surname);
            System.out.printf("%s: %s%n", surname, phones);

        }


    }
}
