import java.util.List;
import java.util.Stack;

public interface Solver<T extends Weightable> {
	List<Container<T>> solve(Stack<T> elements, double maxCapacity) throws Exception;
	String name();
}
