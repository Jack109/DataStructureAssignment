import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class TimeComplexityBenchmark {
    private static Random rand = new Random();
    private static final int LOOP_COUNT = 500;
    
    public static void main(String[] args) throws Exception {
        System.out.println("NOTE: This program might take a few minutes to complete.");
        int[] inputCounts = new int[] { 100, 400, 700, 1000, 1300, 1600, 1900};
        Solver<Parcel> FF = new FirstFitSolver<Parcel>();
        Solver<Parcel> BF = new BestFitSolver<Parcel>();
        Solver<Parcel> FFD = new FirstFitDecreaseSolver<Parcel>();
        Solver<Parcel> BFD = new BestFitDecreaseSolver<Parcel>();

        TCBenchmarkResult FFResult = new TCBenchmarkResult(FF.name());
        TCBenchmarkResult BFResult = new TCBenchmarkResult(BF.name());
        TCBenchmarkResult FFDResult = new TCBenchmarkResult(FFD.name());
        TCBenchmarkResult BFDResult = new TCBenchmarkResult(BFD.name());

        int[][] resultMatrix = new int[][] {
            new int[inputCounts.length], // First Fit
            new int[inputCounts.length], // Best Fit
            new int[inputCounts.length], // First Fit Decrease
            new int[inputCounts.length], // Best Fit Decrease
        };

        for (int i = 0; i < inputCounts.length; i++) {
            for (int j = 0; j < LOOP_COUNT; j++) {
                double maximumCapacity = rand.nextDouble();
                Stack<Parcel> randomParcels = BenchmarkUtil.getRandomParcels(maximumCapacity, inputCounts[i]);

                FF.solve((Stack<Parcel>) randomParcels.clone(), maximumCapacity);
                FFResult.addNextIterationCountRequired(FF.interationCount());

                BF.solve((Stack<Parcel>) randomParcels.clone(), maximumCapacity);
                BFResult.addNextIterationCountRequired(BF.interationCount());

                FFD.solve((Stack<Parcel>) randomParcels.clone(), maximumCapacity);
                FFDResult.addNextIterationCountRequired(FFD.interationCount());

                BFD.solve((Stack<Parcel>) randomParcels.clone(), maximumCapacity);
                BFDResult.addNextIterationCountRequired(BFD.interationCount());
            }
            resultMatrix[0][i] = FFResult.averageIterationCount();
            resultMatrix[1][i] = BFResult.averageIterationCount();
            resultMatrix[2][i] = FFDResult.averageIterationCount();
            resultMatrix[3][i] = BFDResult.averageIterationCount();
        }

        System.out.println("Testing time complexity . . .");
        String[] names = new String[] {"First Fit", "Best Fit", "First Fit Decrease", "Best Fit Decrease"};
        for (int i = 0; i < 4; i++) {
            String iterationCounts = "";
            for (int j = 0; j < inputCounts.length; j++) {
                iterationCounts += resultMatrix[i][j] + ", ";
            }
            iterationCounts = "[" + iterationCounts + "]";
            System.out.println(names[i] + " = " + iterationCounts);
        }
    }
}

class TCBenchmarkResult { // TC means Time Complexity
    private String solverName;
    private List<Integer> iterationCountHistory;

    public TCBenchmarkResult(String solverName) {
        this.solverName = solverName;
        this.iterationCountHistory = new ArrayList<Integer>();
    }

    public void addNextIterationCountRequired(Integer binsRequired) {
        this.iterationCountHistory.add(binsRequired);
    }

    public int averageIterationCount() {
        double averageIterationCount = (double) sum(iterationCountHistory) / (double) iterationCountHistory.size();
        averageIterationCount = Math.ceil(averageIterationCount);

        // clear history
        this.iterationCountHistory.clear();

        return (int) averageIterationCount;
    }

    public String toString() {
        return "(Name: " + this.solverName + ", Average iteration count: " + averageIterationCount();
    }

    private double sum(List<Integer> xs) {
        int result = 0;
        for (int i = 0; i < xs.size(); i++) {
            result += xs.get(i);
        }
        return result;
    }

}
