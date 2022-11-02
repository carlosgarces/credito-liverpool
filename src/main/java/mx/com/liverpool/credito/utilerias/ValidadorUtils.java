package mx.com.liverpool.credito.utilerias;


/**
 * utileria pora validaciones varias
 * @author jcgarces
 * @since 25/05/2017
 */
public interface ValidadorUtils {

	/**
	 * valida si es nulo
	 * @isAnyNull
	 * @param values
	 * @return
	 */
	boolean esAlgunoNulo(Object[] values);


	/**
	 * valida si es email
	 * @isEmail
	 * @param value
	 * @return
	 */
	boolean esEmail(String value);

	/**
	 * valida si es boleano
	 * @isBoolean
	 * @param value
	 * @return
	 */
	boolean esBoolean(String value);

	/**
	 * valida si es nuemrico
	 * @isNumeric
	 * @param value
	 * @return
	 */
	boolean esNumero(String value);

	/**
	 * valida si es alfabetico 
	 * @isAlpha
	 * @param value
	 * @return
	 */
	boolean esAlpha(String value);


	/**
	 * valida si el vacio o nulo
	 * @isEmpty
	 * @param value
	 * @return
	 */
	boolean esVacio(Object value);

    /**
     * valida un formato
     * @param valorAtributo
     * @param expRegular
     * @param businessExceptionError
     * @throws BiometricosException
     */
    Boolean esFormatoValido( String valorAtributo, String expRegular) ;

    /**
     * valida si el arreglo es vacio o nulo
     * @param value
     * @return
     */
	Boolean esArregloVacio(Object value);
    
    
}