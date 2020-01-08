package thuis.dullaert.johan.exceptions;

public class DAOException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);		
	}

	/**
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}
	
	

}
