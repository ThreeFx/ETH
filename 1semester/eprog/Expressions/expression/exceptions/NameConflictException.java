/**
 *
 */

package expression.exceptions;

/**
 * Thrown when a context does not provide a defintion for a variable
 */
public class NameConflictException extends EvaluationException {
    public NameConflictException(String message) {
        super(message);
    }
}
