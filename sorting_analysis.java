import java.util.*;
import java.io.*;

public class SortingAnalysis {

    // ------------------ CASE GENERATORS ------------------
    static int[] generateBestCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        return arr;
    }

    static int[] generateWorstCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = n - i;
        return arr;
    }

    static int[] generateAverageCase(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) list.add(i);
        Collections.shuffle(list);

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = list.get(i);
        return arr;
    }

    // ------------------ BUBBLE SORT ------------------
    static int bubbleSort(int[] arr, boolean ascending) {
        int steps = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                steps++;

                if ((ascending && arr[j] > arr[j + 1]) ||
                    (!ascending && arr[j] < arr[j + 1])) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    steps += 3;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        return steps;
    }

    // ------------------ SELECTION SORT ------------------
    static int selectionSort(int[] arr, boolean ascending) {
        int steps = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int idx = i;

            for (int j = i + 1; j < n; j++) {
                steps++;

                if ((ascending && arr[j] < arr[idx]) ||
                    (!ascending && arr[j] > arr[idx])) {
                    idx = j;
                }
            }

            if (idx != i) {
                int temp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = temp;
                steps += 3;
            }
        }

        return steps;
    }

    // ------------------ INSERTION SORT ------------------
    static int insertionSort(int[] arr, boolean ascending) {
        int steps = 0;
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            steps++;
            int j = i - 1;

            while (j >= 0) {
                steps++;

                if ((ascending && arr[j] > key) ||
                    (!ascending && arr[j] < key)) {
                    arr[j + 1] = arr[j];
                    steps++;
                    j--;
                } else break;
            }

            arr[j + 1] = key;
            steps++;
        }

        return steps;
    }

    // ------------------ ANALYSIS ------------------
    static void analyzeSorting() {
        int[] sizes = {10, 20, 30, 40};

        String[] cases = {"Best", "Average", "Worst"};
        String[] orders = {"Ascending", "Descending"};

        for (String order : orders) {
            boolean ascending = order.equals("Ascending");

            for (String caseType : cases) {

                System.out.println("\n--- " + order + " | " + caseType + " ---");

                for (int n : sizes) {

                    int[] original;

                    if (caseType.equals("Best"))
                        original = generateBestCase(n);
                    else if (caseType.equals("Worst"))
                        original = generateWorstCase(n);
                    else
                        original = generateAverageCase(n);

                    System.out.println("\nInput Size: " + n);

                    int[] arr1 = original.clone();
                    int[] arr2 = original.clone();
                    int[] arr3 = original.clone();

                    int stepsBubble = bubbleSort(arr1, ascending);
                    int stepsSelection = selectionSort(arr2, ascending);
                    int stepsInsertion = insertionSort(arr3, ascending);

                    System.out.println("Bubble Steps: " + stepsBubble);
                    System.out.println("Selection Steps: " + stepsSelection);
                    System.out.println("Insertion Steps: " + stepsInsertion);
                }
            }
        }
    }

    // ------------------ MAIN ------------------
    public static void main(String[] args) {
        analyzeSorting();
    }
}
