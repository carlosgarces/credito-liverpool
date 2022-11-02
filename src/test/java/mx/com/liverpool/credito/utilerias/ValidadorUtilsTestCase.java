package mx.com.liverpool.credito.utilerias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import mx.com.liverpool.credito.constantes.ExpresionesRegularConstants;


/**
 * validador
 * @author jcgarces
 *
 */
@SpringBootTest(classes= {mx.com.liverpool.credito.servicios.config.ServiceConfig.class})
public class ValidadorUtilsTestCase extends BaseTestCase{
	
	/**
	 * validador
	 */
	@Autowired
	private ValidadorUtils validadorUtils;
	


	/**
	 * es vacio o nulo
	 */
	@Test
	public void testIsEmptyObjectNull() {
		Object o = null;
		assertEquals(true, validadorUtils.esVacio(o));
	}
	
	/**
	 * es vacio o nulo
	 */
	@Test
	public void testIsEmptyObjectArrayNull() {
		Object[] o = null;
		assertEquals(true, validadorUtils.esVacio(o));
		o = new Object[]{};
		assertEquals(true, validadorUtils.esVacio(o));
		o = new Object[]{new Object()};
		assertEquals(false, validadorUtils.esVacio(o));
		
		byte[] by = null;
		assertEquals(true, validadorUtils.esVacio(by));
		by = new byte[]{};
		assertEquals(true, validadorUtils.esVacio(by));
		by = new byte[]{Byte.MIN_VALUE};
		assertEquals(false, validadorUtils.esVacio(by));
		
		char[] c = null;
		assertEquals(true, validadorUtils.esVacio(c));
		c=new char[]{};
		assertEquals(true, validadorUtils.esVacio(c));
		c=new char[]{'c'};
		assertEquals(false, validadorUtils.esVacio(c));
		
		boolean [] b = null;
		assertEquals(true, validadorUtils.esVacio(b));
		b=new boolean[]{};
		assertEquals(true, validadorUtils.esVacio(b));
		b=new boolean[]{true};
		assertEquals(false, validadorUtils.esVacio(b));
		
		short[] s = null;
		assertEquals(true, validadorUtils.esVacio(s));
		s = new short[]{};
		assertEquals(true, validadorUtils.esVacio(s));		
		s = new short[]{1};
		assertEquals(false, validadorUtils.esVacio(s));
		
		int[] i = null;
		assertEquals(true, validadorUtils.esVacio(i));
		i = new int[]{};
		assertEquals(true, validadorUtils.esVacio(i));
		i = new int[]{1};
		assertEquals(false, validadorUtils.esVacio(i));
		
		
		long[] l = null;
		assertEquals(true, validadorUtils.esVacio(l));
		l = new long[]{};
		assertEquals(true, validadorUtils.esVacio(l));
		l = new long[]{1L};
		assertEquals(false, validadorUtils.esVacio(l));
		
		float[]f = null;
		assertEquals(true, validadorUtils.esVacio(f));
		f = new float[]{};
		assertEquals(true, validadorUtils.esVacio(f));
		f = new float[]{1.0f};
		assertEquals(false, validadorUtils.esVacio(f));
		
		double [] d = null;
		assertEquals(true, validadorUtils.esVacio(d));
		d = new double[]{};
		assertEquals(true, validadorUtils.esVacio(d));
		d = new double[]{3.4d};
		assertEquals(false, validadorUtils.esVacio(d));
		
		
	}
	
	/**
	 * es vacio o nulo
	 */
	@Test
	public void testIsEmptyObjectString() {
		
		assertEquals(true, validadorUtils.esVacio(""));
	}
	
	/**
	 * es vacio o nulo
	 */
	@Test
	public void testIsEmptyObjectLong() {
		
		assertEquals(false, validadorUtils.esVacio(1L));
	}
	
	/**
	 * es vacio o nulo
	 */
	@Test
	public void testIsEmptyObjectColelction() {
		List<String> l = new ArrayList<String>();
		assertEquals(true, validadorUtils.esVacio(l));
	}


	/**
	 * alfabetico
	 */
	@Test
	public void testIsAlpha() {
		assertEquals(true, validadorUtils.esAlpha("WSWW"));
	}
	
	/**
	 * alfabetico
	 */
	@Test
	public void testIsAlpha2() {
		assertEquals(false, validadorUtils.esAlpha("222"));
		assertEquals(false, validadorUtils.esAlpha(null));
	}

	/**
	 * numerico
	 */
	@Test
	public void testIsNumeric() {
		assertEquals(true, validadorUtils.esNumero("222"));
	}
	
	/**
	 * numerico
	 */
	@Test
	public void testIsNumeric2() {
		assertEquals(false, validadorUtils.esNumero("X"));
		assertEquals(false, validadorUtils.esNumero(null));
	}

	/** 
	 * booleano
	 */
	@Test
	public void testIsBoolean() {
		assertEquals(false, validadorUtils.esBoolean("X"));
	}
	
	/**
	 * booleano
	 */
	@Test
	public void testIsBoolean2() {
		assertEquals(true, validadorUtils.esBoolean("true"));
		assertEquals(false, validadorUtils.esBoolean(null));
	}

	/**
	 * email
	 */
	@Test
	public void testIsEmail() {
		assertEquals(false, validadorUtils.esEmail("true"));
	}
	
	/**
	 * email
	 */
	@Test
	public void testIsEmail2() {
		assertEquals(true, validadorUtils.esEmail("jcged@rett.com"));
		assertEquals(false, validadorUtils.esEmail(null));
	}

	/**
	 * nulos
	 */
	@Test
	public void testIsAnyNull() {
		Object o = null;
		assertEquals(true, validadorUtils.esAlgunoNulo((Object[])null));
		assertEquals(true, validadorUtils.esAlgunoNulo(new Object[] {"",o}));
		assertEquals(false, validadorUtils.esAlgunoNulo(new Object[] {"XX",0L}));
		

	}

	/**
	 * validar formato
	 */
	@Test
	public void testesEsFormatoValido(){
		assertTrue(validadorUtils.esFormatoValido("asdasd",ExpresionesRegularConstants.REG_EXP_ALPHA));
		assertFalse(validadorUtils.esFormatoValido("1222",ExpresionesRegularConstants.REG_EXP_ALPHA));
		assertFalse(validadorUtils.esFormatoValido("",null));
		assertFalse(validadorUtils.esFormatoValido("",ExpresionesRegularConstants.REG_EXP_ALPHA));
		assertFalse(validadorUtils.esFormatoValido("122",null));
		
	}
	
	/**
	 * test de metodos de la clase path. 
	 */
	@Test
	public void testPath() {
		Path path = Paths.get("D:\\dir1\\dir2\\dir3\\nombre.txt");		
		logger.info("getNameCount(): {}",  path.getNameCount());
		logger.info("toString(): {}",  path.getName(path.getNameCount() - 1));
		logger.info("toFile().getName(): {}",  path.getName(path.getNameCount() - 1).toFile().getName());
		
	}

}
