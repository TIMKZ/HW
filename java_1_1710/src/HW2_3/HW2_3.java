package HW2_3;

import java.util.*;

public class HW2_3 {
    private static final String[] INPUT_DATA = {
            "дом", "улица", "солнце",
            "дверь", "улица","корова",
            "молоко", "квас", "корова",
            "дом", "кот", "солнце",
            "лимонад", "солнце",
            "корова"

    };

    /*1. Создать массив с набором слов (10-20 слов, среди которых должны встречаться повторяющиеся).
        Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        Посчитать, сколько раз встречается каждое слово.

        2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и
        телефонных номеров. В этот телефонный справочник с помощью метода add() можно
        добавлять записи. С помощью метода get() искать номер телефона по фамилии.
        Следует учесть, что под одной фамилией может быть несколько
        телефонов (в случае однофамильцев), тогда при запросе такой фамилии должны
        выводиться все телефоны.
    */
    public static void main(String[] args) {

        String [] arr = {
                "дом", "улица", "солнце",
                "дверь", "улица","корова",
                "молоко", "квас", "корова",
                "дом", "кот", "солнце",
                "лимонад", "солнце",
                 "корова"

        };

//1.
        Set<String> printArr1 = new HashSet<>();
        for (String a : arr) {
            if (printArr1.add(a))
                System.out.print(a + " ");
        }

        System.out.println("");
        Arrays countArr = null;

        long n = countArr.stream(arr).distinct().count();
        System.out.println("Уникальных значений: " + n);
//2.
        System.out.println(new TreeSet(Arrays.asList(arr)));
//        Arrays countArr = null;
        long m = countArr.stream(arr).distinct().count();
        System.out.println("Уникальных значений: " + n);


//        Set<String> printArr2 = new HashSet<>();
//        for (String b : arr) {
//            if (printArr2.add(b))
//                System.out.print(b + ": " + Collections.frequency(Arrays.asList(arr), b) + " шт. ");
//        }

//3.
        Map<String, Integer > frequencyByWord = new HashMap<>();
        for (String word : INPUT_DATA) {
         Integer frequency = frequencyByWord.getOrDefault(word, 0);
         frequencyByWord.put(word, ++frequency);

        }
        for (String key : frequencyByWord.keySet()) {
            Integer frequency = frequencyByWord.get(key);
            System.out.printf(" %s: %d,", key, frequency);


        }














    }







}
