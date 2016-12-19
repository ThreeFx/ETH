/**
 *
 */

package expression.exceptions;

/**
 * Thrown when a context does not provide a defintion for a variable
 */
public class NoSuchOperationException extends ParseException {
    public NoSuchOperationException(String message) {
        super(message);
    }
}
