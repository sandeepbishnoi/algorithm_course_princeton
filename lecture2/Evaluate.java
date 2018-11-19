package lecture2;

import java.io.InvalidObjectException;
import java.security.InvalidParameterException;

/** Implementation of Arithmetic function evaluation using Dijkstra's two stack algorithm */
public class Evaluate {

  public static void main(String[] args) {
    LimitedEvaluator eval = new LimitedEvaluator();
    System.out.println(eval.evaluate("(1+(2+3))"));
  }
}

/**
 * Limited Evaluator to evaluate single digit numeric values with operators + and *
 */
class LimitedEvaluator
{
  public int evaluate(String e)
  {
    GenericStack<Character> operator = new GenericStack<Character>();
    GenericStack<Integer> value = new GenericStack<Integer>();

    char[] expr = e.toCharArray();
    for(char c: expr)
    {
      if(c == '(')
      {}
      else if(c ==')')
      {
        Integer operand1 = value.pop();
        Integer operand2 = value.pop();
        char op = operator.pop();
        switch (op)
        {
          case '*':
            value.push(operand1*operand2);
            break;
          case '+':
            value.push(operand1+operand2);
            break;
          default:
        }
      }
      else if(c >= '0' && c <= '9')
      {
        value.push(Character.getNumericValue(c));
      }
      else
      {
        operator.push(c);
      }
    }
    return value.pop();
  }
}
