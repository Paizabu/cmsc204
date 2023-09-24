//author: Paizabu Min
import java.util.ArrayList;
public class Notation {
	
	public Notation() {};
	
	/**
	 * Evaluates a postfix expression from a string to a double
	 * @param postfixExpr the postfix expression in String format
	 * @return the evaluation of the postfix expression as a double
	 * @throws InvalidNotationFormatException  if the postfix expression format is invalid
	 */
	public static double evaluatePostfixExpression​(String postfixExpr) 
			throws InvalidNotationFormatException{
		
		MyStack<Double> operandStack = new MyStack<>();
		
		ArrayList<Character> operands = new ArrayList<>();
		operands.add('*');
		operands.add('/');
		operands.add('%');
		operands.add('+');
		operands.add('-');
		
		double result = 0.0;
		
		try {
			while(!postfixExpr.isEmpty()) {
				Character look = postfixExpr.charAt(0);
				String ch = Character.toString(postfixExpr.charAt(0));
				if(Character.isDigit(look)) {
					operandStack.push(Double.valueOf(ch));
				}
				else if (operands.contains(look)) {
					double n1 = Double.valueOf(operandStack.pop());
					if(operandStack.isEmpty()) throw new InvalidNotationFormatException();
					double n2 = Double.valueOf(operandStack.pop());
					
					switch(look) {
						case '+' : 
							result = n2+n1;
							break;
						case '-':
							result = n2-n1;
							break;
						case '*':
							result = n2*n1;
							break;
						case '/':
							result = n2/n1;
							break;
						case '%':
							result = n2%n1;
							break;
					}
					operandStack.push(result);
	
				} else throw new InvalidNotationFormatException();
				if(postfixExpr.length() != 1)
					postfixExpr = postfixExpr.substring(1);
				else postfixExpr = "";
			}
		}  catch (StackOverflowException e) {
			System.out.println(e.getMessage());
		} catch (StackUnderflowException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	/**
	 * Convert the Postfix expression to the Infix expression
	 * @param postfix the postfix expression in string format
	 * @return the infix expression in string format
	 * @throws InvalidNotationFormatException if the postfix expression format is invalid
	 */
	public static String convertPostfixToInfix​(String postfix) 
			throws InvalidNotationFormatException{
		
		MyStack<String> operandStack = new MyStack<>();
		
		ArrayList<Character> operands = new ArrayList<>();
		operands.add('*');
		operands.add('/');
		operands.add('%');
		operands.add('+');
		operands.add('-');
		
		try {
			while(!postfix.isEmpty()) {
				Character look = postfix.charAt(0);
				String ch = Character.toString(postfix.charAt(0));
				if(Character.isDigit(look))
					operandStack.push(ch);
				else if(operands.contains(look)) {
					String n1 = operandStack.top();
					operandStack.pop();
					if(operandStack.isEmpty()) 
						throw new InvalidNotationFormatException();
					String n2 = operandStack.top();
					operandStack.pop();
					operandStack.push("(" + n2 + ch + n1 + ")");
				} else throw new InvalidNotationFormatException();
				if(postfix.length() != 1)
					postfix = postfix.substring(1);
				else postfix = "";
			}
		} catch (StackOverflowException e) {
			System.out.println(e.getMessage());
		} catch (StackUnderflowException e) {
			System.out.println(e.getMessage());
		}
		return operandStack.toString();
		
	}
	
	/**
	 * Convert an infix expression into a postfix expression
	 * @param infix the infix expression in string format
	 * @return the postfix expression in string format
	 * @throws InvalidNotationFormatException if the infix expression format is invalid
	 */
	public static String convertInfixToPostfix​(String infix) 
			throws InvalidNotationFormatException{
		
		MyQueue<Character> postfix = new MyQueue<>();
		MyStack<Character> operatorStack = new MyStack<>();
		
		ArrayList<Character> highPrecedence = new ArrayList<>();
		highPrecedence.add('*');
		highPrecedence.add('/');
		highPrecedence.add('%');
	
		ArrayList<Character> lowPrecedence = new ArrayList<>();
		lowPrecedence.add('+');
		lowPrecedence.add('-');
		
		ArrayList<Character> normalPrecedence = new ArrayList<>();
		normalPrecedence.add('(');
		normalPrecedence.add(')');
		normalPrecedence.add('^');
		
		char topOperator;
		try {
		while(!infix.isEmpty()) {
			char ch = infix.charAt(0);
				if(Character.isDigit(ch))
				postfix.enqueue(ch);
				else if (highPrecedence.contains(ch) || lowPrecedence.contains(ch) || normalPrecedence.contains(ch)){
					switch(ch) {
						case '^' :
							operatorStack.push(ch);
							break;
						case '(' :
							operatorStack.push(ch);
							break;
						case ')' :
							
							topOperator = operatorStack.pop();
							if(operatorStack.isEmpty()) throw new InvalidNotationFormatException();
							while(topOperator != '(') {
								postfix.enqueue(topOperator);
								
								topOperator = operatorStack.pop();
							}
							break;
						case '+' : case '-' : case '*' : case '/' : case '%' :
							if(!operatorStack.isEmpty()) {
								while(!operatorStack.isEmpty() && (
										(lowPrecedence.contains(ch) && highPrecedence.contains(operatorStack.top())))
										|| (lowPrecedence.contains(ch) && lowPrecedence.contains(operatorStack.top()))
										|| (highPrecedence.contains(ch) && highPrecedence.contains(operatorStack.top()))
										) {
									topOperator = operatorStack.pop();
									postfix.enqueue(topOperator);
								}
							}
							operatorStack.push(ch);
							break;
					}
				} else throw new InvalidNotationFormatException();
				if(infix.length() != 1)
					infix = infix.substring(1);
				else infix = "";	
		}
		while(!operatorStack.isEmpty()) {
			topOperator = operatorStack.pop();
			postfix.enqueue(topOperator);
		}
		} catch (QueueOverflowException e) {
			System.out.println(e.getMessage());
		} catch (StackOverflowException e) {
			System.out.println(e.getMessage());
		} catch (StackUnderflowException e) {
			System.out.println(e.getMessage());
		} 	
		
		return postfix.toString();
	}
}
