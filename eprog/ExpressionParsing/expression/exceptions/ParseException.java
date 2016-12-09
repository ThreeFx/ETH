/**
 *
 */

package expression.exceptions;

/**
 * Indicates a problem with parsing or evaluating an expression.
 */
public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }
}
