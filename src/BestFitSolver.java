import java.util.List;
import java.util.Stack;

public class BestFitSolver<T extends Weightable> implements Solver<T> {
	private int interationCount;
	public String name() {
		return "Best Fit";
	}

	@Override
	public List<Container<T>> solve(Stack<T> elements, double maxCapacity) throws Exception {
		this.interationCount = 0;
		PointingList<Container<T>> containers = new PointingList<Container<T>>();
		containers.add(new Container<T>(maxCapacity));
		while (elements.size() > 0) {
			T currentElement = elements.pop();		
			
			double bestScore = Double.MAX_VALUE;
			Container<T> bestContainer = null;
			// find the best bin
			containers.moveToFirst();
			while (containers.current() != null) {
				this.interationCount ++;
				double currentScore = containers.current().getScore(currentElement);
				if(currentScore >= 0 && currentScore < bestScore) {
					bestScore = currentScore;
					bestContainer = containers.current();
				}
				containers.moveToNext();
			}

			if (bestContainer == null) {
				// if no bin can fit, add a new empty bin
				Container<T> newContainer = new Container<T>(maxCapacity);
				newContainer.add(currentElement);
				containers.add(newContainer);
			} else {
				bestContainer.add(currentElement);
			}
		}
		return containers.toList();
	}

	@Override
	public int interationCount() {
		return this.interationCount;
	}
}
