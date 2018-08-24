import java.util.List;
import java.util.Stack;

public class FirstFitSolver<T extends Weightable> implements Solver<T> {	
	public String name() {
		return "First Fit";
	}

	@Override
	public List<Container<T>> solve(Stack<T> elements, double maxCapacity) {
		PointingList<Container<T>> containers = new PointingList<Container<T>>();
		containers.add(new Container<T>(maxCapacity));
		while (elements.size() > 0) {
			T currentElement = elements.pop();
			containers.moveToFirst();
			while (!containers.getCurrent().canFit(currentElement)) {
				containers.moveToNext();
				if (containers.getCurrent() == null) {
					containers.add(new Container<T>(maxCapacity));
					break;
				} 
			}
			containers.getCurrent().add(currentElement);
		}
		return containers.toList();
	}
}
