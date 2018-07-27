import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class TruckLoading {
	public static void main(String[] args) {
		int[] weights = new int[]{9,2,10,2,2,8,1,7,2,7};
		int maxBinCapacity = 15;
		testFirstFit(weights, maxBinCapacity);
	}
	
	public static Stack<Parcel> initializeParcels(int[] weights) {
		Stack<Parcel> parcels = new Stack<Parcel>();
		for(int i = 0 ; i < weights.length; i++) {
			parcels.push(new Parcel(weights[i]));
		}
		return parcels;
	}
	
	public static void testFirstFit(int[] weights, int maxBinCapacity) {
		System.out.println("Test first fit");
		System.out.println("Initial parcels");
		
		Stack<Parcel> parcels = initializeParcels(weights);
		System.out.println(parcels);
		System.out.println("Bin maximun capacity is " + maxBinCapacity);
		System.out.println("Resulting bins");
		MyLinkedList<Bin> result = Solution.firstFit(parcels, maxBinCapacity);
		System.out.println(result.toString());
	}
	
}
