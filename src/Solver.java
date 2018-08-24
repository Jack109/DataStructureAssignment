import java.util.Stack;

public interface Solver {
	PointingList<Bin> solve(Stack<Parcel> parcels, double maxBinCapacity);
	String name();
}
