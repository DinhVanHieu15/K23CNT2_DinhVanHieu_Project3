package tight_loosely_coupling;

import java.util.Arrays;

// Triển khai thuật toán BubbleSort
public class LooselyBubbleSortAlgorithm implements SortAlgorithm {

    @Override
    public void sort(int[] array) {
        System.out.println("Sorted using bubble sort algorithm");
        Arrays.stream(array).sorted().forEach(System.out::println);
    }
}