package expression.parser;

import expression.combinators.*;
import expression.exceptions.*;

public class ParserDefaults {
    static Expression createUnaryExpression(String funcName, Expression arg) throws ParseException {
        if (funcName.equals("sin")) {
            return new Sin(arg);
        } else if (funcName.equals("cos")) {
            return new Cos(arg);
        } else if (funcName.equals("abs")) {
            return new Abs(arg);
        } else if (funcName.equals("log")) {
            return new Log(arg);
        } else {
            // Maybe the user defined a function with this name -
            // we will see this at evaluation time.
            return new FunctionApplication(funcName, arg);
//            throw new NoSuchOperationException("Unary operation`" + funcName + "` is not supported!");
        }
    }

    static Expression createBinaryExpression(String exprName, Expression left, Expression right) throws ParseException {
        if (exprName.equals("+")) {
            return new Addition(left, right);
        } else if (exprName.equals("-")) {
            return new Subtraction(left, right);
        } else if (exprName.equals("*")) {
            return new Multiplication(left, right);
        } else if (exprName.equals("/")) {
            return new Division(left, right);
        } else if (exprName.equals("^")) {
            return new Exponentation(left, right);
        } else {
            throw new NoSuchOperationException("Binary operation `" + exprName + "` is not supported!");
        }
    }
}
