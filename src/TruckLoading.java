import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

public class TruckLoading {
	public static void main(String[] args) {
		Stack<Parcel> parcels = new Stack<Parcel>();
		int [] weights = {9,2,10,2,2,8,1,7,2,7};
		for(int i = 0 ; i < weights.length; i++) {
			parcels.push(new Parcel(weights[i]));
		}
		// Random rand = new Random();
		//for (int i = 0; i < 10; i++) {
		//	parcels.push(new Parcel(rand.nextInt(10) + 1));
		//}
		System.out.println(parcels);
		MyLinkedList<Bin> result = Solution.firstFit(parcels, 15);
		System.out.println(result.toString());
	}

}
