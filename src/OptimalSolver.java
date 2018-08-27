import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This solver is impossible, its purpose is just for benchmarking other solver.
 * It will compute the Lower Bound for the given input
 */
public class OptimalSolver<T extends Weightable> implements Solver<T> {

	@Override
	public List<Container<T>> solve(Stack<T> elements, double maxCapacity) throws Exception {
		double totalWeights = 0;
		for (T e : elements) {
			totalWeights += e.getWeight();
		}

		int numberOfBinsNeeded = (int)Math.ceil(totalWeights / maxCapacity);

		List<Container<T>> result = new ArrayList<Container<T>>();
		for (int i = 0; i < numberOfBinsNeeded; i++) {
			result.add(new Container<T>(0.0));
		}
		return result;
	}

	@Override
	public String name() {
		return "Optimal solver";
	}

}
