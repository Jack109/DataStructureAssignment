import java.util.Stack;

public class FirstFitDecreaseSolver implements Solver {
	public MyLinkedList<Bin> solve(Stack<Parcel> parcels, double maxBinWeight) {
		parcels.sort(null); // need to change
		return new FirstFitSolver().solve(parcels, maxBinWeight);
	}

	@Override
	public String name() {
		return "First Fit Decrease";
	}
}
