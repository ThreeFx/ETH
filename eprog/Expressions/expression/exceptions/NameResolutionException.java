/**
 *
 */

package expression.exceptions;

/**
 * Thrown when a context does not provide a defintion for a variable
 */
public class NameResolutionException extends EvaluationException {
    public NameResolutionException(String message) {
        super(message);
    }
}
