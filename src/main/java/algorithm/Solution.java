package algorithm;

import org.hibernate.mapping.Collection;

import java.util.*;

public class Solution {

    public static void main(String[] args) {

        // içerisinde 100 farklı sayı olan bi liste oluşturulsun
        Random random = new Random();
        HashSet<Integer> uniqueNumbers = new HashSet<>();

        while (uniqueNumbers.size()<100){
            uniqueNumbers.add(random.nextInt());
        }

        //bu listenin kopyası olsun
        ArrayList<Integer> uniqueNumbersCopy = new ArrayList<>(uniqueNumbers);


        //0 <= x <100 aralığında random bi sayı üretelim

        int randomNumber = random.nextInt(100);


        //kopya listedeki bulduğumuz srastegele numaranın indeksindeki veriyi silelim
         Integer n = uniqueNumbersCopy.remove(randomNumber);


         //uniqueNumbersCopy.remove(uniqueNumbersCopy.get(randomNumber));


        //eksik olan elemanı bulalım
    }
}
