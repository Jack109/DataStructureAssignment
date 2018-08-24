import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		// Note that the last element is actually the first element,
		// Because Stack is used

		// best fit will perform better than first fit
		int[] weights1 = new int[] { 8, 1, 10, 2, 5 };

		// first fit will perform better than best fit
		int[] weights2 = new int[] { 5, 6, 2, 9, 7 };

		// best fit and first fit will perform equally
		int[] weights3 = new int[] { 8, 1, 7, 2, 7 };

		// first fit decrease performs best
		int[] weights4 = new int[] { 3, 13, 2, 12 };

		testEverySolver(weights1, "Best fit perform better");
		testEverySolver(weights2, "First fit perform better");
		testEverySolver(weights3, "Best fit and first fit will perform equally");
		testEverySolver(weights4, "First fit decrease perform best");

	}

	public static void testEverySolver(int[] weights, String message) {
		System.out.println(message + "\n");
		int maxBinCapacity = 15;
		testSolver(weights, maxBinCapacity, new FirstFitSolver<Parcel>());
		testSolver(weights, maxBinCapacity, new BestFitSolver<Parcel>());
		testSolver(weights, maxBinCapacity, new FirstFitDecreaseSolver<Parcel>());
		testSolver(weights, maxBinCapacity, new BestFitDecreaseSolver<Parcel>());
		System.out.println("==========================================================");
	}

	public static Stack<Parcel> initializeParcels(int[] weights) {
		Stack<Parcel> parcels = new Stack<Parcel>();
		for (int i = 0; i < weights.length; i++) {
			parcels.push(new Parcel(weights[i]));
		}
		return parcels;
	}

	public static void testSolver(int[] weights, int maxBinCapacity, Solver<Parcel> solver) {
		System.out.println("Testing " + solver.name() + "\n");
		Stack<Parcel> parcels = initializeParcels(weights);
		System.out.println("Initial parcels : " + new ArrayList<Parcel>(parcels) + "\n");
		System.out.println("Bin maximun capacity is " + maxBinCapacity + "\n");
		
		List<Container<Parcel>> result = solver.solve(parcels, maxBinCapacity);
		
		System.out.println("Result: " + result.size() + " bins is required.\n");
		for (Container<Parcel> c : result) {
			if (c != null)
				System.out.println(c);
		}
		System.out.println("\n------------------------------------\n");
	}
}
