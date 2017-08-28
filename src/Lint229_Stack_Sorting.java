import java.util.Stack;
/*
 * Sort a stack in ascending order (with biggest terms on top).
You may use at most one additional stack to hold items, but you may not copy the elements into any other data structure (e.g. array).
 */
public class Lint229_Stack_Sorting {
    public void stackSorting(Stack<Integer> stack) {
	Stack<Integer> helpStack = new Stack<Integer>();
	while (!stack.isEmpty()) {
	    int element = stack.pop();
	    while (!helpStack.isEmpty() && helpStack.peek() < element) {
		stack.push(helpStack.pop());
	    }
	    helpStack.push(element);
	}
	while (!helpStack.isEmpty()) {
	    stack.push(helpStack.pop());
	}
    }
}
