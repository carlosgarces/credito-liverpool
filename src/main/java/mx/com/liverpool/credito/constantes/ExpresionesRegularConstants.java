package mx.com.liverpool.credito.constantes;

/**
 * Expresiones regulares
 * @author jcgarces
 * @since 25/05/2017
 */
public final class ExpresionesRegularConstants {

	
	/**
	 * Expresi�n regular para identificadores Java
	 */
	public static final String REG_EXP_JAVA_CLASS = "^[a-zA-Z_$][\\w\\-_$]*(\\.[a-zA-Z_$]([\\w\\-_$]*))*$";
	/**
	 * Expresi�n regular para alfab�ticos
	 */
	public static final String REG_EXP_ALPHA = "[a-zA-Z]+";
	/**
	 * Expresi�n regular para num�ricos
	 */
	public static final String REG_EXP_NUMERIC = "[0-9]+";
	/**
	 * Expresi�n regular para booleanos
	 */
	public static final String REG_EXP_BOOLEAN = "true|false";
	/**
	 * Expresi�n regular para correos electr�nicos
	 */
	public static final String REG_EXP_EMAIL = "^[\\w_.-]+@([\\w_-]+)(.([\\w_-]+))+$";
	/**
	 * Expresi�n regular para rutas v�lidas
	 */
	public static final String REG_EXP_RELATIVE_PATH = "^(/{0,1}[\\w_\\-]+)*([\\w_\\-])+[\\.]([\\w_\\-])+$";
	/**
	 * Expresi�n regular para p�ginas zul o acciones do v�lidas
	 */
	public static final String REG_EXP_RELATIVE_ZUL_OR_DO_PATH = "^(/{0,1}[\\w_\\-]+)*([\\w_\\-])+\\.(zul|do)$";
	/**
	 * Expresi�n regular para JNDIs v�lidos
	 */
	public static final String REG_EXP_JNDI = "^[\\w_\\-]+/[\\w_-]+$";
	/**
	 * Expresi�n regular para IP
	 */
	public static final String REG_EXP_IP = "^((([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])(:\\d{1,5}){0,1})$";
	/**
	 * Expresi�n regular para HOST interno
	 */
	public static final String REG_EXP_INTERNAL_HOST = "^([A-Za-z][\\w_\\-]+(:\\d{1,5}){0,1})$";
	
	/**
	 * expresion regulkar poara clave de afore
	 */
	public static final String REG_EXP_CLAVE_AFORE = "5[0-9]{2}";
	
	/**
	 * formato de tipo de archivo
	 */
	public static final String REG_EXP_TIPO_ARCHIVO = "(0[1-5])";
	
	/**
	 * numerico de 5 posiciones
	 */
	public static final String REG_EXP_FORMATO_CURP = "([A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[A-Z0-9]{1}[0-9]{1})";
	
	/**
	 * numerico de 2 posiciones
	 */
	public static final String REG_EXP_FORMATO_TIPO_EXPEDIENTE = "[A-Z0-9]{2}";
	
	/**
	 * numerico de 2 posiciones
	 */
	public static final String REG_EXP_FORMATO_CLAVE_DOCUMENTO = "[A-Z0-9]{2}";
	
	/**
	 * numerico de 2 posiciones para solicitante
	 */
	public static final String REG_EXP_FORMATO_TIPO_SOLICITANTE = "(0[0-4]{1})";
	
	/**
	 * numerico de 2 posiciones para celular
	 */
	public static final String REG_EXP_FORMATO_CELULAR = "([0-9]{10})";
	
	/**
	 * numerico de 2 posiciones para celular
	 */
	public static final String REG_EXP_FORMATO_NSS = "([0-9]{11})";
	
	/**
	 * nombre de archivo de expediente electronico
	 */
	public static final String REG_EXP_FORMATO_NOMBRE_ARCHIVO_EXPEDIENTE = new StringBuilder()
			.append(REG_EXP_CLAVE_AFORE) // CALVE AFORE
			.append(REG_EXP_FORMATO_TIPO_EXPEDIENTE) //TIPO DE EXEPEDIENTE
			.append(REG_EXP_FORMATO_CURP) // CURP
			.append("[1|2]{1}") // TIPO TRABAJADOR
			.append(REG_EXP_FORMATO_CLAVE_DOCUMENTO) //CLAVE ARCHIVO
			.append("[1-9]{1}") //consecutivo de CLAVE ARCHIVO
			.append("[0-9]{8}") //fecha
			.append("[0-9]{3}") //consecutivo
			.toString()
			;

	/**
	 * zone id para mexico centro
	 */
	public static final String SERVICIO = "idServicio";
	
	/**
	 * zone id para mexico centro
	 */
	public static final String CLIENTE = "idCliente";
	
	/**
	 * zone id para mexico centro
	 */
	public static final String BUSINESS = "idEbusiness";
	
	/**
	 * constructor
	 */
	private ExpresionesRegularConstants() {
		// No se instancia
	} 
}
