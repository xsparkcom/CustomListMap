package MyList;

import MyMap.MyHashMap;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> testlist = new ArrayList<>();

        MyArrayList<Integer> list = new MyArrayList<Integer>();
        Random random = new Random();

        String s = "[";
        for (int i = 0; i < 10; i++) {
            int temp = random.nextInt(20);
            list.add(temp);
            if (i != 10 && i!=0 ) {
                s += ", ";
            }
            s += temp + "";
        }
        s += "]";

        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        s = "[";
        for (int i = 0; i < list.getSize(); i++){

            if (i != 10 && i!=0 ) {
                s += ", ";
            }
            s += list.get(i) + "";
        }
        s += "]";

        MyHashMap<String, String> myHashMap= new MyHashMap<String, String>();
        myHashMap.put("I am", "Kotov");
        System.out.println(myHashMap.get("I am"));
        myHashMap.put("I am", "Test");
        System.out.println(myHashMap.get("I am"));
        myHashMap.put("Something", "bla bla bla");
        System.out.println(myHashMap.get("I am"));
        System.out.println(myHashMap.get("Something"));
        myHashMap.remove("I am");
        System.out.println(myHashMap.get("I am"));
        System.out.println(myHashMap.size());
        System.out.println(myHashMap.isEmpty());
        System.out.println(myHashMap.remove("what"));
        System.out.println(myHashMap.size());
        System.out.println(myHashMap.remove("Something"));
        System.out.println(myHashMap.size());
        myHashMap.clear();


        MyHashMap<Integer, String> myHashMap2= new MyHashMap<Integer, String>();

        for(int i = 0; i < 1000; i++){
            myHashMap2.put(random.nextInt(1000), "string");
        }






    }


}
