package expression.combinators;

import expression.exceptions.*;
import expression.evaluation.*;

import java.util.*;
import java.util.function.*;

/**
 * A simple model of a function application using the name
 * of the function and the argument - the definition of the
 * function is looked up at evaluation time.
 */
public class FunctionApplication extends Expression {
    public final String name;
    private Expression argument;

    public FunctionApplication(String name, Expression argument) {
        // Since the function is resolved using a context which is previously unknown
        // We cannot specify the current function.
        this.name = name;
        this.argument = argument;
    }

    public double evaluateWith(Context context) throws EvaluationException {
        // If the current function is defined
        if (context.hasFunc(this)) {
            // We will use the function in the dictionary.
            // We do NOT update this.f because evaluation is dictionary-sensitive
            // and we do not want to be stuck with old definitions.
            // we have to clone here to guarantee that context.getFunc is
            // not modified

            FunctionDefinition def = context.getFunc(this);
//            System.out.println(def.applyTo(this.argument).toString());
            return def.applyTo(this.argument).evaluateWith(context);
        } else {
            System.out.println(context.getFunc(this));
            throw new NameResolutionException("Could not resolve function name '" + name + "'");
        }
    }

    public <T> Set<T> getProperty(Function<Expression, T> selector) {
        Set<T> res = new HashSet<T>();
        T prop = selector.apply(this);

        if (prop != null) res.add(prop);

        res.addAll(this.argument.getProperty(selector));
        return res;
    }

    public Expression replaceVariable(Variable var, Expression newExpr) {
        this.argument = this.argument.replaceVariable(var, newExpr);
        return this;
    }

    public FunctionApplication clone() {
        return new FunctionApplication(this.name, argument.clone());
    }

    public String toString() {
        return this.name + "(" + this.argument.toString() + ")";
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof FunctionApplication) {
            return ((FunctionApplication)obj).name.equals(this.name);
        } else {
            return false;
        }
    }
}

