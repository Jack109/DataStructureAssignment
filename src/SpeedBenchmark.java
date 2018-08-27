import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class SpeedBenchmark {
    private static Random rand = new Random();
    private static final int LOOP_COUNT = 500;
    private static final int INPUT_COUNT = 2000; // change this number

    public static void main(String[] args) throws Exception {
        Solver<Parcel> FF = new FirstFitSolver<Parcel>();
        Solver<Parcel> BF = new BestFitSolver<Parcel>();
        Solver<Parcel> FFD = new FirstFitDecreaseSolver<Parcel>();
        Solver<Parcel> BFD = new BestFitDecreaseSolver<Parcel>();

        SpeedBenchmarkResult FFResult = new SpeedBenchmarkResult(FF.name());
        SpeedBenchmarkResult BFResult = new SpeedBenchmarkResult(BF.name());
        SpeedBenchmarkResult FFDResult = new SpeedBenchmarkResult(FFD.name());
        SpeedBenchmarkResult BFDResult = new SpeedBenchmarkResult(BFD.name());
        for (int i = 0; i < LOOP_COUNT; i++) {
            double maxCapacity = rand.nextDouble();
            Stack<Parcel> randomParcels = getRandomParcels(maxCapacity);
            FFResult.benchmark(FF,   (Stack<Parcel>)randomParcels.clone(), maxCapacity);
            BFResult.benchmark(BF,   (Stack<Parcel>)randomParcels.clone(), maxCapacity);
            FFDResult.benchmark(FFD, (Stack<Parcel>)randomParcels.clone(), maxCapacity);
            BFDResult.benchmark(BFD, (Stack<Parcel>)randomParcels.clone(), maxCapacity);
        }
        System.out.println(FFResult);
        System.out.println(BFResult);
        System.out.println(FFDResult);
        System.out.println(BFDResult);
    }

    private static Stack<Parcel> getRandomParcels(double maximumCapacity) {
        Stack<Parcel> parcels = new Stack<Parcel>();
        for (int j = 0; j < INPUT_COUNT; j++) {
            parcels.push(new Parcel(getRandomWeight(maximumCapacity)));
        }
        return parcels;
    }

    private static double getRandomWeight(double maximumCapacity) {
        while (true) {
            double weight = rand.nextDouble();
            if (weight <= maximumCapacity) {
                return weight;
            }
        }
    }
}

class SpeedBenchmarkResult {
    private String solverName;
    private List<Long> timeRequiredHistory;

    public SpeedBenchmarkResult(String solverName) {
        this.solverName = solverName;
        this.timeRequiredHistory = new ArrayList<Long>();
    }

    public void benchmark(Solver<Parcel> solver, Stack<Parcel> inputParcels, double maxCapacity) throws Exception {
        long before = System.currentTimeMillis();
        solver.solve(inputParcels, maxCapacity);
        long now = System.currentTimeMillis();
        this.timeRequiredHistory.add(now - before);
    }

    public String toString() {
        double averageBinRequired = (double) sum(timeRequiredHistory) / (double) timeRequiredHistory.size();
        return "(Name: " + this.solverName + ", Average time required: " + averageBinRequired + " ms";
    }

    private double sum(List<Long> xs) {
        long result = 0;
        for (int i = 0; i < xs.size(); i++) {
            result += xs.get(i);
        }
        return result;
    }

}