package phonebook;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String pathDir = "/home/me/Downloads/directory.txt";
        String pathFind = "/home/me/Downloads/find.txt";
        File fileFind = new File(pathFind);
        File fileDir = new File(pathDir);
        ArrayList<String> findArr;
        ArrayList<String> dirArr;
        ArrayList<String> sortedDirArr;
        ArrayList<String> qSortedDirArr;

        findArr = readFile(fileFind);
        dirArr = readFile(fileDir);

        int count = 0;
        long startTime = System.currentTimeMillis();

        String output = "Start searching (linear search)...\n";

        for (String sFind : findArr) {
            for (String sDir : dirArr) {
                if (sDir.contains(sFind)) {
                    count++;
                    break;
                }
            }
        }

        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;

        output = output.concat(String.format("Found %d / %d entries. ", count, findArr.size()));
        Time timeLinear = new Time(timeTaken);
        output = output.concat("Time taken: " + timeLinear.stringTime);
        output = output.concat("\n\n");

        long maxTime = timeTaken * 11;
        output = output.concat("Start searching (bubble sort + jump search)...\n");
        long sortStartTime = System.currentTimeMillis();
        sortedDirArr = sortAL(dirArr, maxTime);
        long sortEndTime = System.currentTimeMillis();
        Time timeBubble = new Time(sortEndTime - sortStartTime);

        String strBubble = ("Sorting time: " + timeBubble.stringTime);
        if (sortEndTime - sortStartTime > maxTime) {
            strBubble = strBubble.concat(" - STOPPED, moved to linear search\n");
            long timeStartLinear = System.currentTimeMillis();
            count = 0;
            for (String sFind : findArr) {
                for (String sDir : dirArr) {
                    if (sDir.contains(sFind)) {
                        count++;
                        break;
                    }
                }
            }

            long timeEndLinear = System.currentTimeMillis();
            Time timeLinear2 = new Time(timeEndLinear - timeStartLinear);
            strBubble = strBubble.concat("Searching time: ");
            strBubble = strBubble.concat(timeLinear2.stringTime);

            output = output.concat(String.format("Found %d / %d entries. ", count, findArr.size()));

            Time timeTotal = new Time(timeEndLinear - timeStartLinear + sortEndTime - sortStartTime);
            output = output.concat("Time taken: " + timeTotal.stringTime + "\n");
            output = output.concat(strBubble + "\n");
        } else {
            long jumpStartTime = System.currentTimeMillis();
            for (String sFind : findArr) {
                if (jumpSearch(sortedDirArr, sFind) >= 0) {
                    count++;
                }
            }
            long jumpEndTime = System.currentTimeMillis();
            Time timeJump = new Time(jumpEndTime - jumpStartTime);
            Time timeTotal = new Time(jumpEndTime - jumpStartTime + sortEndTime - sortStartTime);
            output = output.concat(String.format("Found %d / %d entries. ", count, findArr.size()));
            output = output.concat("Time taken: " + timeTotal.stringTime + "\n");
            output = output.concat(strBubble + "\n");
            output = output.concat("Searching time: " + timeJump.stringTime + "\n");
        }

        System.out.print(output + "\n");

        qSortedDirArr = dirArr;
        long quickStartTime = System.currentTimeMillis();
        quickSort(qSortedDirArr, 0, dirArr.size() - 1);
        long quickEndTime = System.currentTimeMillis();
        Time timeQuick = new Time(quickEndTime - quickStartTime);
        count = 0; // reset count
        long binStartTime = System.currentTimeMillis();
        for (String sFind : findArr) {
            if (binSearch(qSortedDirArr, sFind, 0, qSortedDirArr.size() - 1) >= 0) {
                count++;
            }
        }
        long binEndTime = System.currentTimeMillis();
        Time timeBinary = new Time(binEndTime - binStartTime);
        Time timeQBTotal = new Time(binEndTime - binStartTime + quickEndTime - quickStartTime);

        output = "Start searching (quick sort + binary search)...\n";
        output = output.concat(String.format("Found %d / %d entries. ", count, findArr.size()));
        output = output.concat("Time taken: " + timeQBTotal.stringTime + "\n");
        output = output.concat("Sorting time: " + timeQuick.stringTime + "\n");
        output = output.concat("Searching time: " + timeBinary.stringTime + "\n");
        System.out.print(output + "\n");


        HashTable hashTable = new HashTable(dirArr.size() * 2);
        long hashCreateStartTime = System.currentTimeMillis();
        for (String sDir : dirArr) {
            String[] dirEntry = sDir.split(" ", 2);
            hashTable.put(Math.abs(dirEntry[1].hashCode()), dirEntry[0]);
        }
        long hashCreateEndTime = System.currentTimeMillis();
        Time timeHashCreate = new Time(hashCreateEndTime - hashCreateStartTime);
        count = 0;
        long hashSearchStartTime = System.currentTimeMillis();
        for (String sFind : findArr) {
            Object result = hashTable.get(Math.abs(sFind.hashCode()));
            if (result != null) {
                count++;
            }
        }
        long hashSearchEndTime = System.currentTimeMillis();
        Time timeHashSearch = new Time(hashSearchEndTime - hashSearchStartTime);
        Time timeHashTotal = new Time(hashSearchEndTime - hashSearchStartTime + hashCreateEndTime - hashCreateStartTime);

        output = "Start searching (hash table)...\n";
        output = output.concat(String.format("Found %d / %d entries. ", count, findArr.size()));
        output = output.concat("Time taken: " + timeHashTotal.stringTime + "\n");
        output = output.concat("Creating time: " + timeHashCreate.stringTime + "\n");
        output = output.concat("Searching time: " + timeHashSearch.stringTime + "\n");
        System.out.print(output);

    }

    public static int binSearch(ArrayList<String> arrayList, String target, int left, int right) {
        if (left > right) {
            return -1; // search interval is empty, the element is not found
        }

        int mid = left + (right - left) / 2; // the index of the middle element
        String[] splitName = arrayList.get(mid).split(" ", 2);

        if (splitName[1].contains(target)) {
            return mid; // the element is found, return its index
        } else if (splitName[1].compareTo(target) > 0) {
            return binSearch(arrayList, target, left, mid - 1); // go to the left subarray
        } else {
            return binSearch(arrayList, target, mid + 1, right); // go the the right subarray
        }
    }

    public static void quickSort(ArrayList<String> inputAl, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(inputAl, left, right);
            quickSort(inputAl, left, pivotIndex - 1);  // sort the left subarray
            quickSort(inputAl, pivotIndex + 1, right); // sort the right subarray
        }
    }

    public static int partition(ArrayList<String> inputAL, int left, int right) {
        String[] listing = inputAL.get(right).split(" ", 2);
        String pivot = listing[1];
        int partitionIndex = left;

        for (int i = left; i < right; i++) {
            String[] splitName = inputAL.get(i).split(" ", 2);
            if (splitName[1].compareTo(pivot) <= 0) {
                swap(inputAL, i, partitionIndex);
                partitionIndex++;
            }
        }
        swap(inputAL, partitionIndex, right);
        return partitionIndex;
    }

    private static void swap(ArrayList<String> arrayList, int i, int j) {
        String temp = arrayList.get(i);
        arrayList.set(i, arrayList.get(j));
        arrayList.set(j, temp);
    }

    public static ArrayList<String> readFile(File path) {
        ArrayList<String> stringAL = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(path);
            while (scanner.hasNextLine()) {
                stringAL.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stringAL;
    }

    public static ArrayList<String> sortAL (ArrayList<String> inputAL, long time) {
        long startTime = System.currentTimeMillis();
        long currentTime = 0;
        for (int i = 0; i < inputAL.size() - 1; i++) {
            for (int j = 0; j < inputAL.size() - i - 1; j++) {
                if (inputAL.get(j).compareTo(inputAL.get(j + 1)) > 0) {
                    String temp = inputAL.get(j);
                    inputAL.set(j, inputAL.get(j + 1));
                    inputAL.set(j + 1, temp);
                }
            }
            currentTime = System.currentTimeMillis();
            if (currentTime - startTime > time) {
                break;
            }
        }
        return inputAL;
    }

    public static int jumpSearch(ArrayList<String> arrayList, String target) {
        int currentRight = 0; // right border of the current block
        int prevRight = 0; // right border of the previous block

        /* If array is empty, the element is not found */
        if (arrayList.size() == 0) {
            return -1;
        }

        /* Check the first element */
        if (arrayList.get(currentRight).contains(target)) {
            return 0;
        }

        /* Calculating the jump length over array elements */
        int jumpLength = (int) Math.sqrt(arrayList.size());

        /* Finding a block where the element may be present */
        while (currentRight < arrayList.size() - 1) {

            /* Calculating the right border of the following block */
            currentRight = Math.min(arrayList.size() - 1, currentRight + jumpLength);

            if (arrayList.get(currentRight).compareTo(target) <= 0) {
                break; // Found a block that may contain the target element
            }

            prevRight = currentRight; // update the previous right block border
        }

        /* If the last block is reached and it cannot contain the target value => not found */
        if ((currentRight == arrayList.size() - 1) && arrayList.get(currentRight).compareTo(target) < 0) {
            return -1;
        }

        /* Doing linear search in the found block */
        return backwardSearch(arrayList, target, prevRight, currentRight);
    }

    public static int backwardSearch(ArrayList<String> arrayList, String target, int leftExcl, int rightIncl) {
        for (int i = rightIncl; i > leftExcl; i--) {
            if (arrayList.get(i).contains(target)) {
                return i;
            }
        }
        return -1;
    }
}

class Time {
    long timeTaken;
    long minTaken = 0;
    long secTaken = 0;
    long msTaken = 0;
    String stringTime;

    public Time (long timeTaken) {
        this.timeTaken = timeTaken;
        stringTime = formatTime(timeTaken);
    }

    public String formatTime (long timeTaken) {
        long minTaken = timeTaken / 60000;
        long secTaken = (timeTaken - (minTaken * 60000)) / 1000;
        long msTaken = timeTaken - (minTaken * 60000) - (secTaken * 1000);

        String output = String.format("%d min. %d sec. %d ms.", minTaken, secTaken, msTaken);
        return output;
    }
}

class TableEntry<T> {
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

class HashTable<T> {
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