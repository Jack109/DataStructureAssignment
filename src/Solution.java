import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

public class Solution {
	

	public MyLinkedList<Bin> firstFitDecrease(Stack<Parcel> parcels, double maxBinWeight) {
		parcels.sort(null);
		return firstFit(parcels, maxBinWeight);
	}


}
