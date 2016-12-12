package expression.evaluation;

import expression.combinators.*;
import expression.exceptions.*;

import java.util.*;
import java.util.function.*;

/**
 * A custom (e.g. user defined) function. It must be Unary.
 * The parameter of the function is always called x. During
 * evaluation the evaluator replace x with the parameter, so
 * no naming problems will occur.
 */
public class FunctionDefinition {
    private final String name;
    final Variable parameter;
    final Expression body;

    public FunctionDefinition(String name, Variable parameter, Expression body) {
        this.name = name;
        this.parameter = parameter;
        this.body = body;
    }

    /**
     * Apply the function to the argument provided by replacing
     * all occurrences of the variable with the argument.
     */
    public Expression applyTo(Expression arg) {
        return this.body.clone().replaceVariable(this.parameter, arg);
    }

    //public int hashCode() {
    //    return this.name.hashCode();
    //}

    //public boolean equals(Object obj) {
    //    if (obj instanceof FunctionDefinition) {
    //        return ((FunctionDefinition)obj).name.equals(this.name);
    //    } else {
    //        return false;
    //    }
    //}
}

