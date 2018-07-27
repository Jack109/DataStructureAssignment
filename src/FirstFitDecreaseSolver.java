import java.util.Stack;

public class FirstFitDecreaseSolver implements Solver {
	public MyLinkedList<Bin> solve(Stack<Parcel> parcels, double maxBinWeight) {
		parcels.sort(null); // need to change
		parcels = sortstack(parcels);
		return new FirstFitSolver().solve(parcels, maxBinWeight);
	}

	@Override
	public String name() {
		return "First Fit Decrease";
	}

	// Modified from https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/
	public static <T extends Comparable> Stack<T> sortstack(Stack<T> input) {
		Stack<T> tmpStack = new Stack<T>();
		while (!input.isEmpty()) {
			// pop out the first element
			T tmp = input.pop();

			// while temporary stack is not empty and
			// top of stack is greater than temp
			while (!tmpStack.isEmpty() && tmpStack.peek().compareTo(tmp) > 0) {
				// pop from temporary stack and
				// push it to the input stack
				input.push(tmpStack.pop());
			}

			// push temp in tempory of stack
			tmpStack.push(tmp);
		}
		return tmpStack;
	}
}
