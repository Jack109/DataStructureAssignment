import java.util.Stack;

public class FirstFitSolver implements Solver {
	public PointingList<Bin> solve(Stack<Parcel> parcels, double maxBinCapacity) {
		PointingList<Bin> bins = new PointingList<Bin>();
		bins.add(new Bin(maxBinCapacity));
		while (parcels.size() > 0) {
			Parcel currentParcel = parcels.pop();
			bins.moveToFirst();
			while (!bins.getCurrent().canFit(currentParcel)) {
				bins.moveToNext();
				if (bins.getCurrent() == null) {
					bins.add(new Bin(maxBinCapacity));
					break;
				} 
			}
			bins.getCurrent().add(currentParcel);
		}
		return bins;
	}
	
	public String name() {
		return "First Fit";
	}
}
