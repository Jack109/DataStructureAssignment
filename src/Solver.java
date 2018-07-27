import java.util.Stack;

public interface Solver {
	MyLinkedList<Bin> solve(Stack<Parcel> parcels, double maxBinCapacity);
	String name();
}
