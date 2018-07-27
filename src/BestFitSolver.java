import java.util.Stack;

public class BestFitSolver implements Solver {
	public  MyLinkedList<Bin> solve(Stack<Parcel> parcels, double maxBinWeight) {
		MyLinkedList<Bin> bins = new MyLinkedList<Bin>();
		bins.add(new Bin(maxBinWeight));
		while (parcels.size() > 0) {
			Parcel currentParcel = parcels.pop();

			// step one : set score for each bin
			while (bins.getCurrent() != null) {
				bins.getCurrent().setScore(currentParcel);
				bins.moveToNext();
			}
			bins.moveToFirst();

			// step two : find if any bin can fit the currentParcel
			Bin capableBin = null;
			while (bins.getCurrent() != null) {
				if (bins.getCurrent().score >= 0) {
					capableBin = bins.getCurrent();
					break;
				}
			}

			// step 3 : if no bin can fit, add a new empty bin
			if (capableBin == null) {
				bins.add(new Bin(maxBinWeight));
				bins.moveToLast();
				bins.getCurrent().add(currentParcel);
				continue;
			}

			// step 4 : find the bin with the best score
			Bin bestBin = bins.getCurrent();
			bins.moveToFirst();
			while (bins.getCurrent() != null) {
				if (bins.getCurrent().score < 0) {
					continue;
				} else {
					if (bins.getCurrent().score < bestBin.score) {
						bestBin = bins.getCurrent();
					}
				}
				bins.moveToNext();
			}
			capableBin.add(currentParcel);
		}
		return bins;
	}

	@Override
	public String name() {
		return "Best Fit";
	}
}
