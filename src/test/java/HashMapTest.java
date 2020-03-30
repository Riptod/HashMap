import java.util.NoSuchElementException;

import hashmap.MyHashMap;
import hashmap.MyHashMapImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HashMapTest {
    private MyHashMap hashMap;
    @Before
    public void setHashMap() {
        hashMap = new MyHashMapImpl();
    }

    @Test(expected = NoSuchElementException.class)
    public void getByNonExistedKey() {
        hashMap.get(1);
    }

    @Test
    public void putAndGetOk() {
        hashMap.put(1, 313546);
        hashMap.put(2, 51234);
        hashMap.put(-2, 5500);
        hashMap.put(-24, 83478);
        hashMap.put(3, 11234567558565674L);

        Assert.assertEquals(5, hashMap.size());
        Assert.assertEquals(313546, hashMap.get(1));
        Assert.assertEquals(5500, hashMap.get(-2));
        Assert.assertEquals(83478, hashMap.get(-24));
        Assert.assertEquals(51234, hashMap.get(2));
        Assert.assertEquals(11234567558565674L, hashMap.get(3));
    }

    @Test
    public void putAndGetWithCollision() {
        hashMap.put(1, 313546);
        hashMap.put(2, 51234);
        hashMap.put(33, 56755812312L);

        Assert.assertEquals(3, hashMap.size());
        Assert.assertEquals(313546, hashMap.get(1));
        Assert.assertEquals(51234, hashMap.get(2));
        Assert.assertEquals(56755812312L, hashMap.get(33));
    }

    @Test
    public void putAndGetTheOverriddenValueByKey() {
        hashMap.put(3, 2342);
        Assert.assertEquals(1, hashMap.size());
        Assert.assertEquals(2342, hashMap.get(3));

        hashMap.put(3, 11234567558565674L);
        Assert.assertEquals(1, hashMap.size());
        Assert.assertEquals(11234567558565674L, hashMap.get(3));
    }

    @Test
    public void checkTheHashMapIncrease() {
        for (int i = 0; i < 1000; i++) {
            hashMap.put(i, i * 100);
        }
        Assert.assertEquals(1000, hashMap.size());
        Assert.assertEquals(99900, hashMap.get(999));
    }

    @Test
    public void getSizeOfEmptyHashMap() {
        Assert.assertEquals(0, hashMap.size());
    }
}
