package MyList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class MyArrayListTest {


    MyArrayList<Integer> list;
    Random random = new Random();
    int size = 0;

    @Before
    public void list(){
        list = new MyArrayList<Integer>();


        for (int i = 0; i < 10; i++) {
            int temp = random.nextInt(20);
            list.add(temp);
        }
        size = list.getSize();
    }

    @Test
    public void add() {
        list.add(101);
        Assert.assertEquals(list.getSize(), size);
        Assert.assertNotNull(list.get(size));
    }

    @Test
    public void testAdd() {
    }

    @Test
    public void get() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void clear() {
    }

    @Test
    public void sort() {
    }

    @Test
    public void getSize() {
    }
}