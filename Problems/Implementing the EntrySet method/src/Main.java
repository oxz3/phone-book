import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }
    }

    private static class HashTable<T> {
        private int size;
        private TableEntry[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            // put your code here
            int index = findKey(key);

            if (index == -1) {
                return false;
            }

            table[index] = new TableEntry(key, value);
            return true;
        }

        public T get(int key) {
            // put your code here
            int index = findKey(key);

            if (index == -1 || table[index] == null) {
                return null;
            }

            return (T) table[index].getValue();
        }

        public <T> void entrySet() {
            // put your code here
            StringBuilder tableStringBuilder = new StringBuilder();

            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) {
                    //tableStringBuilder.append(i + ": null");
                } else {
                    tableStringBuilder.append(table[i].getKey() + ": ");
                    for (String s : (String[]) table[i].getValue()) {
                        tableStringBuilder.append(s + " ");
                    }
                    tableStringBuilder.append("\n");
                }

            }

            System.out.print(tableStringBuilder.toString());
        }

        private int findKey(int key) {
            // put your code here
            int hash = key % size;

            while (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }

        private void rehash() {
            // put your code here
            this.size = this.size + 1;
            TableEntry[] newTable = new TableEntry[size];
            for (int i = 0; i < table.length; i++) {
                newTable[i] = table[i];
            }
            table = newTable;
        }
    }

    public static void main(String[] args) {
        // put your code here
        Scanner keyboard = new Scanner(System.in);
        int size = keyboard.nextInt();
        HashTable <String[]> hashTable = new HashTable<>(size);
        for (int i = 0; i < size; i++) {
            int key = keyboard.nextInt();
            if (hashTable.get(key) == null) {
                hashTable.put(key, new String[]{ keyboard.next() });
            } else {
                String[] newHashTable = Arrays.copyOf(hashTable.get(key),hashTable.get(key).length + 1);
                newHashTable[newHashTable.length - 1] = keyboard.next();
                hashTable.put(key, newHashTable);
            }
        }
        hashTable.entrySet();
    }
}