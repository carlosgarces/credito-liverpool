/**
 * 
 */
package mx.com.liverpool.credito.utilerias;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.tomakehurst.wiremock.WireMockServer;

/**
 * cliente rest
 * @author jcgarces
 *
 */
@SpringBootTest(classes= {mx.com.liverpool.credito.servicios.config.ServiceConfig.class})
class RestServiceClientUtilsMockTestCase extends BaseMockTestCase{

	/**
	 * utileri a probar
	 */
	@Autowired
	private RestServiceClientUtils serviceClientUtils ;
	
	/**
	 * servidor mock
	 */
	static WireMockServer wm ;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		if(wm == null) {
			wm = new WireMockServer(options().port(9998));
		}
		wm.start();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		if(wm.isRunning()) {
			wm.stop();
			wm.shutdownServer();
		}
	}
	
	/**
	 * Test method for {@link mx.com.procesar.servicios.traspasos.recepcionarchivos.utilerias.impl.RestServiceClientUtilsImpl#ejecutarServicioGet(java.lang.String, java.lang.String, java.lang.Class)}.
	 */
	@Test
	void testEjecutarServicioPut() {		 		 
	
	}
	
	/**
	 * consuoir un servicio post
	 */
	@Test
	void testEjecutarServicioPost() {
	}
	

	/**
	 * post sin header
	 */
	@Test
	void testEjecutarServicioPostwithHeaders() {
	}
	
	/**
	 * post para objetos
	 */
	@Test
	void testEjecutarServicioPostParaObjetos(){
	}
	

	/**
	 * delete
	 */
	@Test
	void testEjecutarServicioDelete() {
	}
	
	/**
	 * xml 
	 */
	@Test
	void testEjecutarServicePostXml() {

	}

}
