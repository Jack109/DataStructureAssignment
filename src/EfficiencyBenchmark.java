import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class EfficiencyBenchmark {
    private static Random rand = new Random();
    private static final int LOOP_COUNT = 500;
    private static Scanner scanner = new Scanner( System.in );

    public static void main(String[] args) throws Exception {
        System.out.println("Enter number of parcels (example: 100) >> ");
        final int INPUT_COUNT = scanner.nextInt();
        Solver<Parcel> FF  = new FirstFitSolver<Parcel>();
        Solver<Parcel> BF  = new BestFitSolver<Parcel>();
        Solver<Parcel> FFD = new FirstFitDecreaseSolver<Parcel>();
        Solver<Parcel> BFD = new BestFitDecreaseSolver<Parcel>();
        Solver<Parcel> OP  = new OptimalSolver<Parcel>();

        EfficiencyBenchmarkResult FFResult  = new EfficiencyBenchmarkResult(FF.name());
        EfficiencyBenchmarkResult BFResult  = new EfficiencyBenchmarkResult(BF.name());
        EfficiencyBenchmarkResult FFDResult = new EfficiencyBenchmarkResult(FFD.name());
        EfficiencyBenchmarkResult BFDResult = new EfficiencyBenchmarkResult(BFD.name());
        EfficiencyBenchmarkResult OPResult  = new EfficiencyBenchmarkResult(OP.name());


        System.out.println("\nRunning efficiency benchmark . . .\n");
        for (int i = 0; i < LOOP_COUNT; i++) {
            double maximumCapacity = rand.nextDouble();
            Stack<Parcel> randomParcels = BenchmarkUtil.getRandomParcels(maximumCapacity, INPUT_COUNT);
            FFResult .addNextBinsRequired(FF.solve((Stack<Parcel>)randomParcels.clone(), maximumCapacity).size());
            BFResult .addNextBinsRequired(BF.solve((Stack<Parcel>)randomParcels.clone(), maximumCapacity).size());
            FFDResult.addNextBinsRequired(FFD.solve((Stack<Parcel>)randomParcels.clone(), maximumCapacity).size());
            BFDResult.addNextBinsRequired(BFD.solve((Stack<Parcel>)randomParcels.clone(), maximumCapacity).size());
            OPResult.addNextBinsRequired(OP.solve((Stack<Parcel>)randomParcels.clone(), maximumCapacity).size());
        }
        System.out.println(FFResult);
        System.out.println(BFResult);
        System.out.println(FFDResult);
        System.out.println(BFDResult);
        System.out.println(OPResult);
    }
}

class EfficiencyBenchmarkResult extends BenchmarkResult {
    private List<Integer> binsRequiredHistory;

    public EfficiencyBenchmarkResult(String solverName) {
        super(solverName);
        this.binsRequiredHistory = new ArrayList<Integer>();
    }

    public void addNextBinsRequired(Integer binsRequired) {
        this.binsRequiredHistory.add(binsRequired);
    }

    public String toString() {
        double averageBinRequired = (double)sum(binsRequiredHistory) / (double)binsRequiredHistory.size();
        averageBinRequired = Math.ceil(averageBinRequired);
        return this.solverName + "\t| Average number of bins required = " + (int)averageBinRequired;
    }

    private int sum(List<Integer> xs) {
        int result = 0;
        for (int i = 0; i < xs.size(); i++) {
            result += xs.get(i);
        }
        return result;
    }

}