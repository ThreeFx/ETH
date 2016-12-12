package expression.evaluation;

import expression.combinators.*;
import expression.exceptions.*;

import java.util.*;
//import java.util.function.*;

public class Context {
    private final Map<Variable, Expression> vars;
    private final Map<FunctionApplication, FunctionDefinition> funcs;

    public Context() {
        this(new HashMap<>(), new HashMap<>());
    }
    // Fuck the java Overload resolution
//    public Context(Map<String, Double> vars) {
//        this(vars, new HashMap<>());
//    }
//
//    public Context(Map<String, Function<Double, Double>> funcs) {
//        this(new HashMap<>(), funcs);
//    }

    public Context(Map<Variable, Expression> vars, Map<FunctionApplication, FunctionDefinition> funcs) {
        this.vars = vars;
        this.funcs = funcs;
    }

    public void addVar(Variable var, Expression expr) {
        this.vars.put(var, expr);
    }

    public boolean hasVar(Variable var) {
        return this.vars.containsKey(var);
    }

    public Expression getVar(Variable var) {
        return this.vars.get(var);
    }

    public Set<Variable> getVars() {
        return this.vars.keySet();
    }

    public void addFunc(FunctionApplication app, FunctionDefinition def) {
        this.funcs.put(app, def);
    }

    public boolean hasFunc(FunctionApplication app) {
        return this.funcs.containsKey(app);
    }

    public FunctionDefinition getFunc(FunctionApplication app) {
        return this.funcs.get(app);
    }

    public Set<FunctionApplication> getFuncs() {
        return this.funcs.keySet();
    }

    public Collection<FunctionDefinition> getDefinitions() {
        return this.funcs.values();
    }
}
