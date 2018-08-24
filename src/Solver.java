import java.util.Stack;

public interface Solver<E extends Weightable> {
	PointingList<Container<E>> solve(Stack<E> elements, double maxCapacity);
	String name();
}
