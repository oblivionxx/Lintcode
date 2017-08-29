import java.util.*;

/*
 * Given an expression string array, return the Polish notation of this expression. (remove the parentheses)

Example
For the expression [(5 − 6) * 7] (which represented by ["(", "5", "−", "6", ")", "*", "7"]), the corresponding polish notation is [* - 5 6 7] (which the return value should be ["*", "−", "5", "6", "7"]).
 */
public class Lint369_Convert_Expression_to_Polish_Notation {
    public ArrayList<String> convertToPN(String[] expression) {
	ArrayList<String> list = new ArrayList<String>();
	Stack<String> stack = new Stack<String>();
	for (int cur = expression.length - 1; cur >= 0; cur--) {
	    String str = expression[cur];
	    if (isOp(str)) {
		if (str.equals(")")) {
		    stack.push(str);
		} else if (str.equals("(")) {
		    while (!stack.isEmpty()) {
			String inPar = stack.pop();
			if (inPar.equals(")")) {
			    break;
			}
			list.add(inPar);
		    }
		} else {
		    while (!stack.isEmpty() && order(str) < order(stack.peek())) {
			list.add(stack.pop());
		    }
		    stack.push(str);
		}
	    } else {
		list.add(str);
	    }
	}
	while (!stack.isEmpty()) {
	    list.add(stack.pop());
	}
	ArrayList<String> result = new ArrayList<String>();
	for (int i = list.size() - 1; i >= 0; i--) {
	    result.add(list.get(i));
	}
	return result;
    }

    boolean isOp(String str) {
	if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("(")
		|| str.equals(")")) {
	    return true;
	} else {
	    return false;
	}
    }

    int order(String a) {
	if (a.equals("*") || a.equals("/")) {
	    return 2;
	} else if (a.equals("+") || a.equals("-")) {
	    return 1;
	} else {
	    return 0;
	}
    }
 
}
