/**
 * 
 */
package mx.com.liverpool.credito.excepciones;

/**
 * @author JCGARCESO
 *
 */
public class RestServiceClientUtilsExcepction extends Exception {

	/**
	 * Serial
	 */
	private static final long serialVersionUID = 5269450716432614549L;

	/**
	 * Constructor 
	 */
	public RestServiceClientUtilsExcepction() {
		// default
	}

	/**
	 * Constructor
	 * @param message
	 */
	public RestServiceClientUtilsExcepction(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * @param cause
	 */
	public RestServiceClientUtilsExcepction(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 */
	public RestServiceClientUtilsExcepction(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public RestServiceClientUtilsExcepction(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
