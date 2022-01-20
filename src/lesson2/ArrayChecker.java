package lesson2;

public class ArrayChecker {
    private String[][] strings;

    public void setStrings(String[][] strings) {
        try {
            checkSizeArray(strings);

            this.strings = strings;
        } catch (MyArraySizeException e) {
            e.printStackTrace();
        } catch (NullPointerException ne) {
            System.out.println("В параметр массива передан null!");
            ne.printStackTrace();
        }
    }

    private void checkSizeArray(String[][] strings) throws MyArraySizeException {
        if (strings.length != 4 || checkLengthStrings(strings)){
            throw new MyArraySizeException("Размер массива не соответствует заданному значению");
        }
    }

    private boolean checkLengthStrings(String[][] strings) {
        for (String [] str: strings){
            if (str.length!=4){return true;}
        }
        return false;
    }

    public void summ() {
        if (strings == null) {
            System.out.println("У класса не инициализирован массив строк, суммировать нечего");
            return;
        }

        try {
            summArray();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    private void summArray() throws MyArrayDataException {
        int result = 0;
        for (int i = 0; i < strings.length; i++) {
            String[] str = strings[i];
            for (int j = 0; j < str.length; j++) {
                try {
                    result+=Integer.parseInt(str[j]);
                }
                catch (NumberFormatException e) {
                    throw new MyArrayDataException("Массив содержит не только числа, суммирование не возможно. Ошибка в ячейке: " + i + ":" + j);
                }
            }
        }
        System.out.println("Сумма элементов массива: " + result);
    }
}
