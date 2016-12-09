    //package expression.parser;
package expression.parser;
//import expression;

import expression.combinators.*;
import expression.exceptions.*;
import expression.tokenizer.*;

//import java.lang.reflect.Constructor;

public class ExpressionParser {
    private Tokenizer tokenizer;
    private Expression expression;

    private final static String ILLEGAL_TERM =
        "Error: Illegal term format: Expected one of:\n"
          + "    ( `expr` )\n"
          + "    func( `expr` )\n"
          + "    atom\n"
          + "but got ";
    private final static String ILLEGAL_EXPRESSION =
        "Error: Illegal expression format: Expected one of:\n"
          + "    `term`\n"
          + "    `term` op `term`\n"
          + "but got ";
    private final static String ILLEGAL_ATOM =
        "Error: Illegal atom format! Expected `var` or `num` but got ";
    private final static String TOO_MUCH =
        "Error: Illegal format: Expected nothing but got ";
    private final static String UNMATCHED_PAREN =
        "Error: Unmatched parenthesis, expected `)` but got ";

    public ExpressionParser() {
        this.tokenizer = null;
    }

    public Expression parse(String expr) throws ParseException {
        this.tokenizer = new Tokenizer(expr);

        Expression result = this.parseExpression();

        if (this.tokenizer.hasNext()) {
            throw new ParseException(TOO_MUCH + this.getTokenizerState());
        }

        return result;
    }

    private Expression parseExpression() throws ParseException {
        Expression e1 = this.parseTerm();

        if (this.tokenizer.hasNextOp()) {
            String op = this.tokenizer.nextOp();
            Expression e2 = this.parseTerm();
            e1 = ParserDefaults.createBinaryExpression(op, e1, e2);
        }
        
        return e1;
    }

    private Expression parseTerm() throws ParseException {
        if (!this.tokenizer.hasNext()) {
            throw new ParseException(ILLEGAL_TERM + this.getTokenizerState());
        }

        // CASE 1: Paren
        if (this.tokenizer.hasNextOpen()) { // (
            this.tokenizer.nextOpen();

            Expression inner = this.parseExpression();
            
            if (this.tokenizer.hasNextClose()) {
                this.tokenizer.nextClose();
                return inner;
            } else {
                throw new ParseException(UNMATCHED_PAREN + this.getTokenizerState());
            }

        }
        
        // CASE 2: Function
        if (this.tokenizer.hasNextFunc()) { // f(
            String funcName = this.tokenizer.nextFunc();
            funcName = funcName.substring(0, funcName.length() - 1); // eliminate the paren

            Expression argument = this.parseExpression();

            if (this.tokenizer.hasNextClose()) {
                this.tokenizer.nextClose();
                return ParserDefaults.createUnaryExpression(funcName, argument);
            } else {
                throw new ParseException(UNMATCHED_PAREN + this.getTokenizerState());
            }
        }

        // CASE 3: Atomic
        if (this.tokenizer.hasNextNum() || this.tokenizer.hasNextVar()) {
            return this.parseAtom();
        }

        throw new ParseException(ILLEGAL_EXPRESSION + this.getTokenizerState());
    }

    private Expression parseAtom() throws ParseException {
        if (this.tokenizer.hasNextNum()) {
            return new Literal(this.tokenizer.nextNum());
        }

        if (this.tokenizer.hasNextVar()) {
            return new Variable(this.tokenizer.nextVar());
        }

        throw new ParseException(ILLEGAL_ATOM + this.getTokenizerState());
    }

    private String getTokenizerState() {
        if (this.tokenizer.hasNext()) {
            return this.tokenizer.next();
        } else {
            return "nothing";
        }
    }
}
