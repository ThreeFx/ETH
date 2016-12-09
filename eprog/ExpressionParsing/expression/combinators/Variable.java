package expression.combinators;

import expression.evaluation.Context;
import expression.exceptions.*;

import java.util.*;
import java.util.function.*;

/**
 * A variable. The value of the variable is resolved in the context.
 */
public class Variable extends Atom {
    public final String name;
    public Variable(String name) {
        this.name = name;
    }
    
    /**
     * Evaluates the variable with the current value stores in the context.
     * Throws if variable is not defined.
     */
    public double evaluateWith(Context context) throws EvaluationException {
        if (context.hasVar(this)) {
            return context.getVar(this).evaluate();
        } else {
            throw new NameResolutionException("Could not resolve variable name '" + name + "'");
        }
    }

    public <T> Set<T> getProperty(Function<Expression, T> selector) {
        Set<T> res = new HashSet<T>();
        T prop = selector.apply(this);
        
        if (prop != null) res.add(prop);

        return res;
    }

    public Expression replaceVariable(String varName, Expression newExpr) {
        if (this.name.equals(varName)) {
            //System.out.println("replacing parameter '" + varName + "' by expression " + newExpr.toString());
            return newExpr.clone();
        } else {
            return this;
        }
    }

    public Variable clone() {
        return new Variable(this.name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return this.name;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Variable) {
            return ((Variable)obj).name.equals(this.name);
        } else {
            return false;
        }
    }
}
