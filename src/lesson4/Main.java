package lesson4;

public class Main {

    public static void main(String[] args) {
        System.out.println("__________task1_____________");
        doPhoneBook();
        System.out.println("__________task2_____________");
        WordsArray wordsArray = new WordsArray(createArrayWords());
        wordsArray.doArrayWords();
    }

    private static void doPhoneBook() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addElementPhoneBook("Ivanov","89999099109");
        phoneBook.addElementPhoneBook("Ivanov","89670049110");
        phoneBook.addElementPhoneBook("Ivanov","89670049111");
        phoneBook.addElementPhoneBook("Petrov","99909099009");
        phoneBook.addElementPhoneBook("Petrov","990 990 00 99 9");
        phoneBook.addElementPhoneBook("Petrov","990  9900 09 99 ");

        System.out.println("Ivanov: " + phoneBook.getPhonesByName("Ivanov"));
        System.out.println("Petrov: " + phoneBook.getPhonesByName("Petrov"));
        System.out.println("Sidorov: " + phoneBook.getPhonesByName("Alex"));
        System.out.println("Vasilev: " + phoneBook.getPhonesByName("Vasilev"));
    }

    private static String[] createArrayWords() {
        String [] strings = new String[20];
        strings[0] = "Roze";
        strings[1] = "Blossom";
        strings[2] = "Pink";
        strings[3] = "Iris";
        strings[4] = "Map";
        strings[5] = "Mark";
        strings[6] = "Cat";
        strings[7] = "Blue";
        strings[8] = "Owl";
        strings[9] = "Green";
        strings[10] = "Dog";
        strings[11] = "Summer";
        strings[12] = "Red";
        strings[13] = "Iris";
        strings[14] = "Map";
        strings[15] = "Pink";
        strings[16] = "Cat";
        strings[17] = "Dog";
        strings[18] = "Cow";
        strings[19] = "Owl";
        return strings;
    }

}
