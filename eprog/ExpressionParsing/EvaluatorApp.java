import expression.combinators.*;
import expression.evaluation.*;
import expression.exceptions.*;
import expression.parser.ExpressionParser;

import java.util.Scanner;

public class EvaluatorApp {
    private static Scanner scanner;
    private static ExpressionParser parser;
    private static boolean simpleParameters;

    public static void main(String[] args) {
        simpleParameters = args.length == 0 || !(args[0].equals("-c"));

        scanner = new Scanner(System.in);
        parser = new ExpressionParser();

        while(true) {
            System.out.print(">> ");
            String function = scanner.nextLine();
            if(function.equals("exit"))
                break;

            Expression expr = null;

            try {
                expr = parser.parse(function);
            } catch (ParseException e) {
                System.out.println("Invalid expression!\n" + e.getMessage());
            }

            if (expr != null) {
                try {
                    System.out.println("expr: " + expr.toString());

                    Context ctxt = new Context();
                    Evaluator eval = new Evaluator(expr, ctxt);

                    // We have free variables
                    // Prompt the user to enter their value
                    while (!(eval.freeVars().isEmpty() && eval.unboundFunctions().isEmpty())) {
                        //System.out.println(eval.freeVars());
                        for (Variable var : eval.freeVars()) {
                            Expression ex = readExpr(var.name + " = ");
                            ctxt.addVar(var, ex);
                        }

                        //System.out.println(eval.unboundFunctions());
                        // Get definitions for the functions:
                        for (FunctionApplication fn : eval.unboundFunctions()) {
                            FunctionDefinition def = readFunction(fn);
                            ctxt.addFunc(fn, def);
                        }
                    }

                    System.out.println(eval.evaluate());
                } catch (EvaluationException e) {
                    System.out.println("Error while evaluating!\n" + e.getMessage());
                }
            }
        }
    }

    private static FunctionDefinition readFunction(FunctionApplication app) {
        FunctionDefinition def = null;
        while (true) {
            try {
                Variable var = null;

                if (!simpleParameters) {
                    System.out.print("parameter name for " + app.name + ": ");
                    Expression expr = parser.parse(scanner.nextLine());
                    if (!(expr instanceof Variable)) {
                        throw new ParseException("Not a valid parameter name: " + expr);
                    }
                    var = (Variable)expr;
                } else {
                    var = new Variable("x");
                }

                System.out.print(app.name + "("+var.name+") = ");
                Expression funcExpr = parser.parse(scanner.nextLine());
                def = new FunctionDefinition(app.name, var, funcExpr);
            } catch (ParseException e) {
                System.out.println("Error parsing definition\n" + e.getMessage() + "\n please try again");
                continue;
            }
            break;
        }
        return def;
    }

    private static Expression readExpr(String prompt) {
        Expression expr = null;
        while (true) {
            try {
                System.out.print(prompt);
                expr = parser.parse(scanner.nextLine());
            } catch (ParseException e) { continue; }
            break;
        }
        return expr;
    }
}
