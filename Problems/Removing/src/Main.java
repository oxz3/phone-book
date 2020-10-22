import java.util.Scanner;

public class Main {
    private static class TableEntry<T> {
        private final int key;
        private final T value;
        private boolean removed;

        public TableEntry(int key, T value) {
            this.key = key;
            this.value = value;
            removed = false;
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
        private final int size;
        private final TableEntry[] table;

        public HashTable(int size) {
            this.size = size;
            table = new TableEntry[size];
        }

        public boolean put(int key, T value) {
            // put your code here
            int index = findKey(key);

            if (index == -1) {
                return false;
            } else if (table[index] == null) {
                table[index] = new TableEntry(key, value);
                return true;
            }
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

            if (index == -1) {
            } else if (table[index] != null) {
                table[index].remove();
            }
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
        }

        @Override
        public String toString() {
            StringBuilder tableStringBuilder = new StringBuilder();

            for (int i = 0; i < table.length; i++) {
                if (table[i] == null) {
                    tableStringBuilder.append(i + ": null");
                } else {
                    tableStringBuilder.append(i + ": key=" + table[i].getKey()
                                                + ", value=" + table[i].getValue()
                                                + ", removed=" + table[i].isRemoved());
                }

                if (i < table.length - 1) {
                    tableStringBuilder.append("\n");
                }
            }

            return tableStringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        // put your code here
        Scanner keyboard = new Scanner(System.in);

        String[] inputNums = keyboard.nextLine().split(" ");
        int entry = Integer.parseInt(inputNums[0]);
        int remove = Integer.parseInt(inputNums[1]);
        int starting = 5;
        while (entry > starting) {
            starting *= 2;
        }
        HashTable hashTable = new HashTable(starting);

        for (int i = 0; i < entry; i++) {
            String[] inputArr = keyboard.nextLine().split(" ");
            hashTable.put(Integer.parseInt(inputArr[0]), inputArr[1]);
        }

        for (int i = 0; i < remove; i++) {
            hashTable.remove(Integer.parseInt(keyboard.nextLine()));
        }

        System.out.print(hashTable.toString());
    }
}