import java.util.ArrayList;
import java.util.Stack;

public class FirstFit {
	public void firstFit(Stack<Parcel> parcels, double maxWeight) {
		ArrayList<Bin> bins = new ArrayList<Bin>();
		bins.add(new Bin(maxWeight));
		for (int i = 0; i < bins.size(); i++) {
			Parcel current = parcels.pop();
			Bin currentBin = bins.get(i);
			if (currentBin.canFit(current)) {
				currentBin.add(current);
			} else {

			}
		}
	}
}
