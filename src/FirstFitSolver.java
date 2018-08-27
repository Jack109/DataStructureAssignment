import java.util.List;
import java.util.Stack;

public class FirstFitSolver<T extends Weightable> implements Solver<T> {	
	private int interationCount;
	public String name() {
		return "First Fit";
	}

	@Override
	public List<Container<T>> solve(Stack<T> elements, double maxCapacity) throws Exception {
		this.interationCount = 0;
		PointingList<Container<T>> containers = new PointingList<Container<T>>();
		containers.add(new Container<T>(maxCapacity));
		while (elements.size() > 0) {
			T currentElement = elements.pop();
			containers.moveToFirst();
			while (!containers.current().canFit(currentElement)) {
				this.interationCount++;
				containers.moveToNext();
				if (containers.current() == null) {
					containers.add(new Container<T>(maxCapacity));
					break;
				} 
			}
			containers.current().add(currentElement);
		}
		return containers.toList();
	}

	@Override
	public int interationCount() {
		return interationCount;
	}
}
