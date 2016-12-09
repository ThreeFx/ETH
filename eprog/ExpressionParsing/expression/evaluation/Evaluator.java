package expression.evaluation;

import expression.combinators.*;
import expression.exceptions.*;

import java.util.*;

public class Evaluator {
    private Context context;
    private Expression expression;

    public Evaluator(Expression expression, Context context) {
        this.context = context;
        this.expression = expression;
    }

    public double evaluate() throws EvaluationException {
        return this.expression.evaluateWith(context);
    }

    public Set<Variable> freeVars() {
        return this.expression.getProperty(var -> (var instanceof Variable ? (Variable)var : null));
    }

    public Set<FunctionApplication> unboundFunctions() {
        return this.expression.getProperty(f -> (f instanceof FunctionApplication ? (FunctionApplication)f : null));
    }
}
