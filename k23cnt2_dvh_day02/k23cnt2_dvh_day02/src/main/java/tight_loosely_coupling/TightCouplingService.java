package tight_loosely_coupling;

import java.util.Arrays;

public class TightCouplingService {

    // 1️⃣ Class này phụ thuộc trực tiếp vào BubbleSortAlgorithm
    private BubbleSortAlgorithm bubbleSortAlgorithm = new BubbleSortAlgorithm();

    // Constructor mặc định
    public TightCouplingService() {}

    // Constructor khác – có thể truyền thuật toán khác (nếu có)
    public TightCouplingService(BubbleSortAlgorithm bubbleSortAlgorithm) {
        this.bubbleSortAlgorithm = bubbleSortAlgorithm;
    }

    // 2️⃣ Logic chính: dùng thuật toán sắp xếp trong nghiệp vụ
    public void complexBusinessSort(int[] arr) {
        bubbleSortAlgorithm.sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }

    // 3️⃣ Hàm main chạy thử
    public static void main(String[] args) {
        TightCouplingService tCouplingService = new TightCouplingService();
        tCouplingService.complexBusinessSort(new int[]{11, 21, 13, 42, 15});
    }
}

