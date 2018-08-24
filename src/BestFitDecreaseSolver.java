import java.util.List;
import java.util.Stack;

public class BestFitDecreaseSolver<T extends Weightable> implements Solver<T> {

	@Override
	public List<Container<T>> solve(Stack<T> elements, double maxCapacity) {
		elements = StackSorter.sortstack(elements);
		return new BestFitSolver().solve(elements, maxCapacity);	
	}

	@Override
	public String name() {
		return "Best Fit Decrease";
	}

}
