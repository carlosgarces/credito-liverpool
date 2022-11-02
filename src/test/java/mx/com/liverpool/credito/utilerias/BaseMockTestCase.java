package mx.com.liverpool.credito.utilerias;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.jupiter.api.Disabled;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.util.ReflectionUtils;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.http.RequestMethod;
import com.github.tomakehurst.wiremock.matching.UrlPattern;

import mx.com.liverpool.credito.servicios.impl.BaseService;

/**
 *
 * mock base test
 * 
 * @author jcgarces
 *
 */

public abstract class BaseMockTestCase extends BaseService {


	
	/**
	 * @throws java.lang.Exception
	 */
	static void setUpBeforeClass(WireMockServer wm) throws Exception {
		if(wm == null) {
			wm = new WireMockServer(options().port(9998));
		}
		if(!wm.isRunning()) {
			wm.start();
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	static void tearDownAfterClass(WireMockServer wm) throws Exception {
		if(wm.isRunning()) {
			wm.stop();
		}
	}

	/**
	 * crear el stub 
	 * @param wm
	 * @param recurso
	 * @param codigo
	 * @param respuesta
	 */
	void crearStub(WireMockServer wm, String contexto,  String recurso, int codigo, String respuesta, RequestMethod requestMethod) {
		String urlPathMatching = new StringBuilder().append(contexto).append(recurso).toString();
		crearStub(wm, urlPathMatching, codigo, respuesta, requestMethod);
	}
	
	/**
	 * crear el stub
	 * @param wm
	 * @param contextoRecurso
	 * @param codigo
	 * @param respuesta
	 * @param requestMethod
	 */
	void crearStub(WireMockServer wm, String contextoRecurso, int codigo, String respuesta, RequestMethod requestMethod) {
		crearStub(wm, contextoRecurso, codigo, respuesta, requestMethod, MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE);
	}

	
	/**
	 * crea stub
	 * @param wm
	 * @param pathPattern
	 * @param codigo
	 * @param respuesta
	 * @param requestMethod
	 * @param requestContentType
	 * @param responseContentType
	 */
	void crearStub(WireMockServer wm, UrlPattern pathPattern, int codigo, String respuesta, RequestMethod requestMethod, String requestContentType, String responseContentType ){
		
		ResponseDefinitionBuilder mockResp;
		MappingBuilder mockReq;
		if(RequestMethod.GET.equals(requestMethod)) {
			mockReq = get(pathPattern);
		} else if(RequestMethod.POST.equals(requestMethod)) {
			mockReq = post(pathPattern);
		} else if(RequestMethod.PUT.equals(requestMethod)) {
			mockReq = put(pathPattern);
		} else if(RequestMethod.DELETE.equals(requestMethod)) {
			mockReq = delete(pathPattern);
		} else {
			throw new NullPointerException("Metodo Request invalido");
		}
		
		mockResp = aResponse().withStatus(codigo).withHeader("Content-Type", responseContentType);
		mockResp = mockResp.withBody(respuesta);
		wm.stubFor(mockReq.willReturn(mockResp));
		
	}
	
	/**
	 * crear el stub 
	 * @param wm
	 * @param recurso
	 * @param codigo
	 * @param respuesta
	 */
	void crearStub(WireMockServer wm, String contextoRecurso, int codigo, String respuesta, RequestMethod requestMethod, String requestContentType, String responseContentType) {
		String urlPathMatching = new StringBuilder().append(contextoRecurso).toString();
		logger.info("url expuesta {}", urlPathMatching);
		UrlPattern urlPattern = urlPathMatching(urlPathMatching);
		crearStub(wm, urlPattern, codigo, respuesta, requestMethod, requestContentType, responseContentType);
	}
	
	/**
	 * contexto de spring
	 */
	protected ApplicationContext applicationContext;

	/**
	 * carga el contexto
	 * 
	 * @return
	 */
	protected ApplicationContext cargaContexto() {
			AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
			applicationContext.register(
					);
			applicationContext.refresh();
		return applicationContext;
	}

	
	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		if (applicationContext == null) {
			try{
				applicationContext = cargaContexto();
			}catch(Exception e){
				applicationContext = cargaContexto();
			}
		}
		return applicationContext;
	}

	/**
	 * recupera el bean indicad del contexto
	 * @param nombreBean
	 * @return
	 */
	protected Object obtenerBean(String nombreBean){
		return getApplicationContext().getBean(nombreBean);
	}
	
	/**
	 * cambia el valor de una propiedad
	 * @param nombreAtributo
	 * @param instancia
	 * @param valor
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 */
	protected void cambiaValor(String nombreAtributo, Object instancia, Object valor) {
		Field atributo;
		try {
			try{
				atributo = ReflectionUtils.findField(instancia.getClass(), nombreAtributo);
			} catch (Exception e1){
				atributo = instancia.getClass().getDeclaredField(nombreAtributo);
			}
			
			ReflectionUtils.makeAccessible(atributo);
			ReflectionUtils.setField(atributo, instancia, valor);
		} catch (Exception e) {
			logger.error("no se a podido cambiar el valor ", e);
		}
		
	}
 
	
	/**
	 * obtiene el string base64 del zip
	 * @return
	 * @throws IOException 
	 */
	protected String obtenerArchivoZip(String nombre) throws IOException {
		Path path = Paths.get(nombre);
		path = path.toAbsolutePath();
		byte[] bytes= Files.readAllBytes(path);
		return Base64Utils.encodeToString(bytes);
	}

	/**
	 * convertir el sip en base64. se usa pen locales solamente
	 */	
	public void testConvierteBase64(String nombre) {
		try {
			String base64 = obtenerArchivoZip(nombre);
			Path path = Paths.get(nombre.concat(".txt"));
			Files.write(path, base64.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE);
		} catch (Exception e) {
			logger.error("no se ha podido leer el archivo zip {}", e);			
		}
	}
	
	/**
	 * toma el base64 y lo converte al arccivo original
	 * @param origen
	 * @param destino
	 * @author JCGARCES
	 * Jul 12, 2021
	 * @throws IOException 
	 */
	@Disabled
	public void testConvertirBase64aImagen(Path origen, Path destino) throws IOException {
		byte[] datos = Files.readAllBytes(origen);
		String d = new String(datos);
		byte[] des = Base64Utils.decodeFromString(d);
		
		Files.write(destino, des, StandardOpenOption.CREATE);
		
	}
	
	
	 /**
  	 * lee el contenido como un string
  	 * @param nombre
  	 * @return
  	 * @author JCGARCES
  	 * May 21, 2021
	 * @throws Exception 
  	 */
  	protected String leerArchivoString (String nombre) throws Exception {
  		Path path = Paths.get(System.getProperty("mx.com.procesar.configuracion.properties"), nombre);
  		
  		try {
  			return new String(Files.readAllBytes(path));
  		} catch (IOException e) {
  			logger.error("No se pudo abrir archivo");
  			throw new Exception();
  		}
  	}
  	
  	/**
  	 * lee el contenido como un string
  	 * @param nombre
  	 * @return
  	 * @author JCGARCES
  	 * May 21, 2021
	 * @throws Exception 
  	 */
  	protected byte[] leerArchivoBytes(String nombre) throws Exception {
  		Path path = Paths.get(System.getProperty("mx.com.procesar.configuracion.properties"), nombre);
  		
  		try {
  			return Files.readAllBytes(path);
  		} catch (IOException e) {
  			logger.error("No se pudo abrir archivo");
  			throw new Exception();
  		}
  	}
  	
}
