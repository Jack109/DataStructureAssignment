import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class TruckLoading {
	public static void main(String[] args) {
		int[] weights1 = new int[]{9,2,10,5,2,8,1,10,2,5};
		testEverySolver(weights1);
	}
	
	public static void testEverySolver(int[] weights) {
		int maxBinCapacity = 15;
		testSolver(weights, maxBinCapacity, new FirstFitSolver());
		testSolver(weights, maxBinCapacity, new BestFitSolver());
	}
	
	public static Stack<Parcel> initializeParcels(int[] weights) {
		Stack<Parcel> parcels = new Stack<Parcel>();
		for(int i = 0 ; i < weights.length; i++) {
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
		MyLinkedList<Bin> result = solver.solve(parcels, maxBinCapacity);
		System.out.println(result.toString());
	}
}
