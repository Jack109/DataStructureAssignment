import java.util.List;
import java.util.Stack;

public class FirstFitDecreaseSolver<T extends Weightable> extends FirstFitSolver<T> {
	@Override
	public String name() {
		return "First Fit Decrease";
	}
	
	@Override
	public List<Container<T>> solve(Stack<T> elements, double maxCapacity) throws Exception {
		elements = StackSorter.sortstack(elements);
		return super.solve(elements, maxCapacity);
	}	
}
