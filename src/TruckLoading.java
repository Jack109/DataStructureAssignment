import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class TruckLoading {
	public static void main(String[] args) {
		// best fit will perform better than first fit
		int[] weights1 = new int[] {8, 1, 10, 2, 5 };

		// first fit will perform better than best fit
		int[] weights2 = new int[] {5, 6, 2, 9, 7 };

		// best fit and first fit will perform equally
		int[] weights3 = new int[] {8, 1, 7, 2, 7 };

		testEverySolver(weights1, "best fit perform better");
		testEverySolver(weights2, "first fit perform better");
		testEverySolver(weights3, "best fit and first fit will perform equally");
	}

	public static void testEverySolver(int[] weights, String message) {
		System.out.println(message + "\n");
		int maxBinCapacity = 15;
		testSolver(weights, maxBinCapacity, new FirstFitSolver());
		testSolver(weights, maxBinCapacity, new BestFitSolver());
		testSolver(weights, maxBinCapacity, new FirstFitDecreaseSolver());
		System.out.println("==========================================================");
	}

	public static Stack<Parcel> initializeParcels(int[] weights) {
		Stack<Parcel> parcels = new Stack<Parcel>();
		for (int i = 0; i < weights.length; i++) {
			parcels.push(new Parcel(weights[i]));
		}
		return parcels;
	}

	public static void testSolver(int[] weights, int maxBinCapacity, Solver solver) {
		System.out.println("Testing " + solver.name());
		System.out.println("Initial parcels : ");

		Stack<Parcel> parcels = initializeParcels(weights);
		System.out.println(new ArrayList<Parcel>(parcels));
		System.out.println("Bin maximun capacity is " + maxBinCapacity);
		System.out.println("Resulting bins : ");
		PointingList<Bin> result = solver.solve(parcels, maxBinCapacity);
		System.out.println(result.toString());
	}
}
