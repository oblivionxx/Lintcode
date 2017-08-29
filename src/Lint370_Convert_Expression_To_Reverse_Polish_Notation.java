import java.util.*;

/*
 * Given an expression string array, return the Reverse Polish notation of this expression. (remove the parentheses)
Example
For the expression [3 - 4 + 5] (which denote by ["3", "-", "4", "+", "5"]), return [3 4 - 5 +] (which denote by ["3", "4", "-", "5", "+"])

Tags 
LintCode Copyright Stack
 */
public class Lint370_Convert_Expression_To_Reverse_Polish_Notation {
    public List<String> convertToRPN(String[] expression) {
	// write your code here
	// https://www.youtube.com/watch?v=LQ-iW8jm6Mk
	// precendence: 'exponential' > '* /' > '+ -' > '='
	// write down all letters
	// meet higher priority than peek, put on top of stack. meet lower/equal priority then peek. pop and write it down. put ( in stack anyway! but when pop just discard. and break
	// eg. a/b-c ==> ab/c- when meet -, should write down / first
	// when meet ) empty the stack.
	List<String> list = new ArrayList<>();
	Stack<String> stack = new Stack<>();

	for (int i = 0; i < expression.length; i++) {
	    String str = expression[i];
	    if (isOp(str)) {
		if (str.equals("(")) {
		    stack.push(str);
		} else if (str.equals(")")) {
		    while (!stack.isEmpty()) {
			String p = stack.pop();
			if (p.equals("(")) {
			    break;
			}
			list.add(p);
		    }
		} else {
		    while (!stack.isEmpty() && order(str) <= order(stack.peek())) {
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
	return list;

	// infix. 3+5, + in the middle
	// precendence: Bracket>Exponential>Divide=Mulitply>Add=Substract
	// RPN no bracket
	// this question is infix to postfix conversion
	// convert-expression-to-polish-notation is infix to prefix
	// http://blog.csdn.net/nicaishibiantai/article/details/45743385
    }

    public boolean isOp(String str) {
	if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("(")
		|| str.equals(")")) {
	    return true;
	} else {
	    return false;
	}
    }

    public int order(String a) {
	if (a.equals("*") || a.equals("/")) {
	    return 2;
	} else if (a.equals("+") || a.equals("-")) {
	    return 1;
	} else {
	    return 0;
	}
    }
}
