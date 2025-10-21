package tight_loosely_coupling;

public class LooselyCoupledService {

    private SortAlgorithm sortAlgorithm;

    // Constructor mặc định
    public LooselyCoupledService() {}

    // Constructor có tham số -> cho phép truyền bất kỳ thuật toán nào
    public LooselyCoupledService(SortAlgorithm sortAlgorithm) {
        this.sortAlgorithm = sortAlgorithm;
    }

    // Phương thức nghiệp vụ
    public void complexBusiness(int[] array) {
        sortAlgorithm.sort(array);
    }

    public static void main(String[] args) {
        // Truyền thuật toán muốn dùng vào service
        LooselyCoupledService service =
                new LooselyCoupledService(new LooselyBubbleSortAlgorithm());
        service.complexBusiness(new int[]{11, 21, 13, 42, 15});
    }
}

