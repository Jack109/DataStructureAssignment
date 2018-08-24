import java.util.Stack;

public class StackSorter {
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
