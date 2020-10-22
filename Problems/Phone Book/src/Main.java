import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;
        private boolean removed;

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

        public void remove() {
             removed = true;   
        }

        public boolean isRemoved() {
             return removed;   
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
            } else if (table[index].isRemoved()) {
                return null;
            } else {
                return (T) table[index].getValue();
            }
        }

        public void remove(int key) {
            // put your code here
            int index = findKey(key);

            if (table[index] != null) {
                table[index].remove();
            }
        }

        private int findKey(int key) {
            // put your code here
            int hash = key % size;

            if (!(table[hash] == null || table[hash].getKey() == key)) {
                hash = (hash + 1) % size;

                if (hash == key % size) {
                    return -1;
                }
            }

            return hash;
        }
    }

    public static void main(String[] args) {
        // put your code here
        Scanner keyboard = new Scanner(System.in);
        int size = Integer.parseInt(keyboard.nextLine());
        int count = 0;

        HashTable<String> hashTable = new HashTable<>(size);

        while (keyboard.hasNextLine() && count < size) {
            String[] inputArr = keyboard.nextLine().split(" ");
            String cmd = inputArr[0];
            int id = Integer.parseInt(inputArr[1]);
            if (inputArr.length == 3) {
                String number = inputArr[2];

                if ("put".equals(cmd)) {
                    hashTable.put(id, number);
                }
            } else {
                if ("get".equals(cmd)) {
                    if (hashTable.get(id) != null) {
                        System.out.println(hashTable.get(id));
                    } else {
                        System.out.println("-1");
                    }
                } else if ("remove".equals(cmd)) {
                    hashTable.remove(id);
                }

            }
            count++;
        }
    }
}