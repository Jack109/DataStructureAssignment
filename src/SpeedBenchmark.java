import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class SpeedBenchmark {
    private static Random rand = new Random();
    private static final int LOOP_COUNT = 500;
    
    public static void main(String[] args) throws Exception {
        System.out.println("Enter number of parcels (example: 100) >> ");
        final int INPUT_COUNT = BenchmarkUtil.readPositiveInteger();
        Solver<Parcel> FF = new FirstFitSolver<Parcel>();
        Solver<Parcel> BF = new BestFitSolver<Parcel>();
        Solver<Parcel> FFD = new FirstFitDecreaseSolver<Parcel>();
        Solver<Parcel> BFD = new BestFitDecreaseSolver<Parcel>();

        SpeedBenchmarkResult FFResult = new SpeedBenchmarkResult(FF.name());
        SpeedBenchmarkResult BFResult = new SpeedBenchmarkResult(BF.name());
        SpeedBenchmarkResult FFDResult = new SpeedBenchmarkResult(FFD.name());
        SpeedBenchmarkResult BFDResult = new SpeedBenchmarkResult(BFD.name());

        System.out.println("\nRunning speed benchmark . . .\n");
        for (int i = 0; i < LOOP_COUNT; i++) {
            double maxCapacity = rand.nextDouble();
            Stack<Parcel> randomParcels = BenchmarkUtil.getRandomParcels(maxCapacity, INPUT_COUNT);
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

}

class SpeedBenchmarkResult extends BenchmarkResult {
    private List<Long> timeRequiredHistory;

    public SpeedBenchmarkResult(String solverName) {
        super(solverName);
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
        return this.solverName + "| Average time required = " + averageBinRequired + " ms";
    }

    private double sum(List<Long> xs) {
        long result = 0;
        for (int i = 0; i < xs.size(); i++) {
            result += xs.get(i);
        }
        return result;
    }

}