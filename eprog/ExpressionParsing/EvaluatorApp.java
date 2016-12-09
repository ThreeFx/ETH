import expression.combinators.*;
import expression.evaluation.*;
import expression.exceptions.*;
import expression.parser.ExpressionParser;

import java.util.Scanner;

public class EvaluatorApp {
    private static Scanner scanner;
    private static ExpressionParser parser;

    public static void main(String[] args) {
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
                    for (Variable var : eval.freeVars()) {
                        Expression ex = readExpr(var.name + " = ");
                        ctxt.addVar(var, ex);
                    }

                    // Get definitions for the functions:
                    for (FunctionApplication fn : eval.unboundFunctions()) {
                        FunctionDefinition def = readFunction(fn);
                        ctxt.addFunc(fn, def);
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
                System.out.print(app.name + "(x) = ");
                Expression funcExpr = parser.parse(scanner.nextLine());
                def = new FunctionDefinition(app.name, "x", funcExpr);
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
