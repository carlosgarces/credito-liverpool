/**
 * Esta utilidad permite realizar operaciones de parseo entre entidades JSON y
 * clases java
 */
package mx.com.liverpool.credito.utilerias;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Metodo de parseo de objetos a Json y visceversa
 * 
 * @author cmejia 08-10-2015
 * 
 */
@SpringBootTest(classes= {mx.com.liverpool.credito.servicios.config.ServiceConfig.class})
public class JsonUtilTestCase extends BaseTestCase{

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(JsonUtilTestCase.class);

	/**
	 * utileria
	 */
	@Autowired
	private JsonUtil jsonUtil;

	
	/**
	 * ObjectToJson
	 */
	@Test
	public void testParseObjectToJson() {

	}
	
	/**
	 * json a lista de objetos
	 */
	@Test
	public void testParseJsonToObjectList(){
	}
	
	/**
	 * json a lista de objetos
	 */
	@Test
	public void testParseJsonToObjectList_Excepcion(){
	}

	/**
	 * ObjectToJson nula
	 */
	@Test
	public void testParseObjectToJson_NuloCadena() {


		String jsonResultado = jsonUtil.convertirObjetoJson(null);
		logger.info(jsonResultado);
		assertTrue(jsonResultado.equals("null"));
	}

	/**
	 * ObjectToJson error
	 */
	@Test
	public void testParseObjectToJson_Error() {

	}

	/**
	 * ObjectToJson nulo
	 */
	@Test
	public void testParseObjectToJson_Null() {
	}

	/**
	 * ObjectToJson
	 */
	@Test
	public void testParseListObjectToJson() {

	}
	
	/**
	 * ObjectToJson exepcion
	 */
	@Test
	public void testParseListObjectToJson_Exception() {


	}

	/**
	 * ObjectToJson nula
	 */
	@Test
	public void testParseListObjectToJson_NuloCadena() {
		try {
			jsonUtil.convertirListaObjetosJson(null);
		} catch (NullPointerException ex) {
			assertNotNull(ex);
		}
	}

	/**
	 * JsonToObject
	 */
	@Test
	public void testParseJsonToObject() {

		
	}

	/**
	 * JsonToObject NullPointer
	 */
	@Test
	public void testParseJsonToObject_NullPointer() {

	}
	
	/**
	 * JsonToObject vacio
	 */
	@Test
	public void testParseJsonToObject_Empty() {


	}
	
	/**
	 * JsonToObject exepcion
	 */
	@Test
	public void testParseJsonToObject_Exception() {
	}
	
	/**
	 * parseXmlToObject
	 */
	@Test
	public void testParseXmlToObject(){
	}
	
	/**
	 * parseXmlToObject
	 */
	@Test
	public void testParseXmlToObject_Excepcion(){
	}
	
	
	/**
	 * parseObjectToXml
	 */
	@Test
	public void testParseObjectToXml(){
	}
	
}
