import java.util.Stack;

public class FirstFitDecreaseSolver<T extends Weightable> implements Solver<T> {
	@Override
	public String name() {
		return "First Fit Decrease";
	}
	
	@Override
	public PointingList<Container<T>> solve(Stack<T> elements, double maxCapacity) {
		elements = sortstack(elements);
		return new FirstFitSolver().solve(elements, maxCapacity);
	}

	// Modified from https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/
	public static <T extends Comparable> Stack<T> sortstack(Stack<T> input) {
		Stack<T> tempStack = new Stack<T>();
		while (!input.isEmpty()) {
			// pop out the first element
			T temp = input.pop();

			// while temporary stack is not empty and
			// top of stack is greater than temp
			while (!tempStack.isEmpty() && tempStack.peek().compareTo(temp) > 0) {
				// pop from temporary stack and
				// push it to the input stack
				input.push(tempStack.pop());
			}

			// push temp in tempory of stack
			tempStack.push(temp);
		}
		return tempStack;
	}

	
}
