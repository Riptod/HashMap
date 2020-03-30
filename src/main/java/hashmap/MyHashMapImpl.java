package hashmap;

import java.util.NoSuchElementException;

public class MyHashMapImpl implements MyHashMap {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private Node[] table = new Node[DEFAULT_INITIAL_CAPACITY];
    private int tableSize = DEFAULT_INITIAL_CAPACITY;
    private int size;

    private static class Node {

        private int key;
        private long value;

        Node(int key, long value) {
            this.key = key;
            this.value = value;
        }

        long getValue() {
            return value;
        }

        void setValue(long value) {
            this.value = value;
        }

        int getKey() {
            return key;
        }
    }

    public void put(int key, long value) {
        if (size >= tableSize) {
            resize();
        }
        int index = convertToHash(key);
        while (table[index] != null && table[index].getKey() != key) {
            index = (index + 1) % size;
        }
        if (table[index] == null) {
            table[index] = new Node(key, value);
            size++;
        } else if (table[index] != null && table[index].getKey() == key) {
            table[index].setValue(value);
        }
    }

    public long get(int key) {
        int index = convertToHash(key);
        if (table[index] == null) {
            throw new NoSuchElementException("No find value with key: "+ key);
        }
        while (table[index] == null || table[index].getKey() != key) {
            index = (index + 1) % tableSize;
        }
        return table[index].getValue();
    }

    public int size() {
        return size;
    }

    private int convertToHash(int key) {
        return key == 0 ? 0 : Math.abs(Integer.hashCode(key)) % tableSize;
    }

    private void resize() {
        Node[] oldMap = table;
        tableSize = size * 2;
        size = 0;
        table = new Node[tableSize];
        for (int i = 0; i < oldMap.length; i++) {
            put(oldMap[i].getKey(), oldMap[i].getValue());
        }
    }
}
