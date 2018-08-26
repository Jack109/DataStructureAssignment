import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class EfficiencyBenchmark {
    private static Random rand = new Random();
    private static final int LOOP_COUNT = 500;
    private static final int INPUT_COUNT = 10; // You can change this to any positive number
    public static void main(String[] args) throws Exception {
        Solver<Parcel> FF  = new FirstFitSolver<Parcel>();
        Solver<Parcel> BF  = new BestFitSolver<Parcel>();
        Solver<Parcel> FFD = new FirstFitDecreaseSolver<Parcel>();
        Solver<Parcel> BFD = new BestFitDecreaseSolver<Parcel>();

        EfficiencyBenchmarkResult FFResult  = new EfficiencyBenchmarkResult(FF.name());
        EfficiencyBenchmarkResult BFResult  = new EfficiencyBenchmarkResult(BF.name());
        EfficiencyBenchmarkResult FFDResult = new EfficiencyBenchmarkResult(FFD.name());
        EfficiencyBenchmarkResult BFDResult = new EfficiencyBenchmarkResult(BFD.name());
        for (int i = 0; i < LOOP_COUNT; i++) {
            double maximumCapacity = rand.nextDouble();
            Stack<Parcel> randomParcels = getRandomParcels(maximumCapacity);
            FFResult .addNextBinsRequired(FF.solve((Stack<Parcel>)randomParcels.clone(), maximumCapacity).size());
            BFResult .addNextBinsRequired(BF.solve((Stack<Parcel>)randomParcels.clone(), maximumCapacity).size());
            FFDResult.addNextBinsRequired(FFD.solve((Stack<Parcel>)randomParcels.clone(), maximumCapacity).size());
            BFDResult.addNextBinsRequired(BFD.solve((Stack<Parcel>)randomParcels.clone(), maximumCapacity).size());
        }
        System.out.println(FFResult);
        System.out.println(BFResult);
        System.out.println(FFDResult);
        System.out.println(BFDResult);
    }

    private static Stack<Parcel> getRandomParcels(double maximumCapacity) {
        Stack<Parcel> parcels = new Stack<Parcel>();
        for (int j = 0; j < INPUT_COUNT; j++) {
            parcels.push(new Parcel(getRandomWeight(maximumCapacity))) ;
        }
        return parcels;
    }

    private static double getRandomWeight(double maximumCapacity) {
        while(true) {
            double weight = rand.nextDouble();
            if(weight <= maximumCapacity) {
                return weight;
            }
        }
    }
}

class EfficiencyBenchmarkResult {
    private String solverName;
    private List<Integer> binsRequiredHistory;

    public EfficiencyBenchmarkResult(String solverName) {
        this.solverName = solverName;
        this.binsRequiredHistory = new ArrayList<Integer>();
    }

    public void addNextBinsRequired(Integer binsRequired) {
        this.binsRequiredHistory.add(binsRequired);
    }

    public String toString() {
        double averageBinRequired = (double)sum(binsRequiredHistory) / (double)binsRequiredHistory.size();
        return "(Name: " + this.solverName + ", Average bin required: " + averageBinRequired;
    }

    private double sum(List<Integer> xs) {
        int result = 0;
        for (int i = 0; i < xs.size(); i++) {
            result += xs.get(i);
        }
        return result;
    }

}