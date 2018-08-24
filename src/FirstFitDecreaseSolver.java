import java.util.Stack;

public class FirstFitDecreaseSolver<T extends Weightable> implements Solver<T> {
	@Override
	public String name() {
		return "First Fit Decrease";
	}
	
	@Override
	public PointingList<Container<T>> solve(Stack<T> elements, double maxCapacity) {
		elements = StackSorter.sortstack(elements);
		return new FirstFitSolver().solve(elements, maxCapacity);
	}

	

	
}
