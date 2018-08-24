import java.util.List;
import java.util.Stack;

public class BestFitDecreaseSolver<T extends Weightable> extends BestFitSolver<T> {

	@Override
	public List<Container<T>> solve(Stack<T> elements, double maxCapacity) {
		elements = StackSorter.sortstack(elements);
		return super.solve(elements, maxCapacity);	
	}

	@Override
	public String name() {
		return "Best Fit Decrease";
	}

}
