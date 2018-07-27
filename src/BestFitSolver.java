import java.util.Stack;

public class BestFitSolver implements Solver {
	public  MyLinkedList<Bin> solve(Stack<Parcel> parcels, double maxBinWeight) {
		MyLinkedList<Bin> bins = new MyLinkedList<Bin>();
		bins.add(new Bin(maxBinWeight));
		while (parcels.size() > 0) {
			Parcel currentParcel = parcels.pop();		
			
			double bestScore = Double.MAX_VALUE;
			Bin bestBin = null;
			// find the best bin
			bins.moveToFirst();
			while (bins.getCurrent() != null) {
				double currentScore = bins.getCurrent().getScore(currentParcel);
				if(currentScore >= 0 && currentScore < bestScore) {
					bestScore = currentScore;
					bestBin = bins.getCurrent();
				}
				bins.moveToNext();
			}

			if (bestBin == null) {
				// if no bin can fit, add a new empty bin
				Bin newBin = new Bin(maxBinWeight);
				newBin.add(currentParcel);
				bins.add(newBin);
			} else {
				bestBin.add(currentParcel);
			}
		}
		return bins;
	}

	public String name() {
		return "Best Fit";
	}
}
