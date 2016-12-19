package expression.evaluation;

import expression.combinators.*;
import expression.exceptions.*;

import java.util.*;
import java.util.stream.*;

public class Evaluator {
    public final Context context;
    public final Expression expression;

    public Evaluator(Expression expression, Context context) {
        this.context = context;
        this.expression = expression;
    }

    public double evaluate() throws EvaluationException {
        return this.expression.evaluateWith(context);
    }

    public Set<Variable> freeVars() {
        Set<Variable> res = freeVarsOf(this.expression);
        res.addAll(context.getDefinitions().stream()
                    .flatMap((FunctionDefinition func) -> freeVarsOf(func.body).stream()).collect(Collectors.toCollection(HashSet::new)));
        res.addAll(context.getVarDefs().stream()
                    .flatMap((Expression expr) -> freeVarsOf(expr).stream()).collect(Collectors.toCollection(HashSet::new)));
        res.removeAll(context.getVars());
        res.removeAll(context.getDefinitions().stream().map(func -> func.parameter).collect(Collectors.toCollection(HashSet::new)));
        return res;
    }

    public Set<FunctionApplication> unboundFunctions() {
        Set<FunctionApplication> res = unboundFunctionsOf(this.expression);
        res.addAll(context.getDefinitions().stream()
                    .flatMap((FunctionDefinition func) -> unboundFunctionsOf(func.body).stream()).collect(Collectors.toCollection(HashSet::new)));
        res.addAll(context.getVarDefs().stream()
                    .flatMap((Expression expr) -> unboundFunctionsOf(expr).stream()).collect(Collectors.toCollection(HashSet::new)));
        res.removeAll(context.getFuncs());
        return res;
    }

    private Set<Variable> freeVarsOf(Expression expression) {
        return expression.getProperty(var ->
                   (var instanceof Variable ? (Variable)var : null));
    }

    private Set<FunctionApplication> unboundFunctionsOf(Expression expression) {
        return expression.getProperty(f ->
                (f instanceof FunctionApplication ? (FunctionApplication)f : null));
    }
}
