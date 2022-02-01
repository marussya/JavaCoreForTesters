package lesson5;

import java.util.Arrays;

public class HW5 {

    public static void main(String[] args) {
        AppData data = new AppData();
//task1
        data.readFromFile("file.csv");
        System.out.println(Arrays.toString(data.getHeader()));
        System.out.println(Arrays.deepToString(data.getData()));
//task2
        data.writeToCSV("new_file.csv");
    }

}
