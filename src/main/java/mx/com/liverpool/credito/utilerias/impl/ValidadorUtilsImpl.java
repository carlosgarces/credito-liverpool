package mx.com.liverpool.credito.utilerias.impl;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import mx.com.liverpool.credito.constantes.ExpresionesRegularConstants;
import mx.com.liverpool.credito.utilerias.ValidadorUtils;

/**
 * Clase con utiler�as diversas para la carga de classloaders, instancias por
 * reflexi�n y validaciones de nulidad
 * 
 * @author jcgarces
 * 
 */
@Component("validadorUtils")
public final class ValidadorUtilsImpl implements ValidadorUtils {

	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.traspasos.recepcionarchivos.utilerias.ValidadorUtils#isEmptyArray(java.lang.Object)
	 */
	@Override
	public Boolean esArregloVacio(Object value) {

		boolean isEmpty = true;
		if (value instanceof byte[]) {
			byte[] bytes = (byte[]) value;
			isEmpty = bytes.length == 0;
		} else if (value instanceof char[]) {
			char[] chars = (char[]) value;
			isEmpty = chars.length == 0;
		} else if (value instanceof boolean[]) {
			boolean[] booleans = (boolean[]) value;
			isEmpty = booleans.length == 0;
		} else if (value instanceof short[]) {
			short[] shorts = (short[]) value;
			isEmpty = shorts.length == 0;
		} else if (value instanceof int[]) {
			int[] ints = (int[]) value;
			isEmpty = ints.length == 0;
		} else if (value instanceof long[]) {
			long[] longs = (long[]) value;
			isEmpty = longs.length == 0;
		} else if (value instanceof float[]) {
			float[] floats = (float[]) value;
			isEmpty = floats.length == 0;
		} else if (value instanceof double[]) {
			double[] doubles = (double[]) value;
			isEmpty = doubles.length == 0;
		} else if (value instanceof Object[]) {
			Object[] array = (Object[]) value;
			isEmpty = array.length == 0;
		}

		return isEmpty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.procesar.testarq32.utilerias.ValidadorUtils\#isEmpty(java.lang
	 * .Object)
	 */
	@Override
	public boolean esVacio(Object value) {

		boolean isEmpty = value == null;
		if (!isEmpty) {
			if (value.getClass().isArray()) {
				isEmpty = esArregloVacio(value);
			} else if (value instanceof String) {
				String string = (String) value;
				isEmpty = string.trim().isEmpty();
			} else if (value instanceof Number) {
				Number number = (Number) value;
				isEmpty = number.intValue() == 0;
			} else if (value instanceof Collection<?>) {
				Collection<?> collection = (Collection<?>) value;
				isEmpty = collection.isEmpty();
			} else if (value instanceof Map) {
				isEmpty = ((Map<?,?>) value).isEmpty();
			}
		}

		return isEmpty;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.procesar.testarq32.utilerias.ValidadorUtils\#isAlpha(java.lang
	 * .String)
	 */
	@Override
	public boolean esAlpha(String value) {
		return value != null && value
				.matches(ExpresionesRegularConstants.REG_EXP_ALPHA);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.procesar.testarq32.utilerias.ValidadorUtils\#isNumeric(java.lang
	 * .String)
	 */
	@Override
	public boolean esNumero(String value) {
		return value != null && value
				.matches(ExpresionesRegularConstants.REG_EXP_NUMERIC);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.procesar.testarq32.utilerias.ValidadorUtils\#isBoolean(java.lang
	 * .String)
	 */
	@Override
	public boolean esBoolean(String value) {
		return value != null && value
				.matches(ExpresionesRegularConstants.REG_EXP_BOOLEAN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.procesar.testarq32.utilerias.ValidadorUtils\#isEmail(java.lang
	 * .String)
	 */
	@Override
	public boolean esEmail(String value) {
		return value != null && value
				.matches(ExpresionesRegularConstants.REG_EXP_EMAIL);
	}

	/*
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.procesar.testarq32.utilerias.ValidadorUtils\#isAnyNull(java.lang
	 * .Object[])
	 */
	@Override
	public boolean esAlgunoNulo(Object[] values) {
		if (values == null) {
			return true;
		}
		for (Object o : values) {
			if (o == null) {
				return true;
			}
		}
		return false;
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.procesar.servicios.biometricos.biometricos4h.utilerias.ValidadorUtils#validateFormato
	 * (java.lang.String, java.lang.String, java.lang.String,
	 * mx.com.procesar.servicios.biometricos.biometricos4h.excepciones.RecepcionArchivoException)
	 */
	@Override
	public Boolean esFormatoValido(String valorAtributo, String expRegular) {
		Boolean regreso = Boolean.FALSE;
		if (!ObjectUtils.isEmpty(valorAtributo) && !ObjectUtils.isEmpty(expRegular)) {
			regreso = valorAtributo.matches(expRegular);
		}
		return regreso;
	}

	

	


}
