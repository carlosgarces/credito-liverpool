package mx.com.liverpool.credito.utilerias;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.http.HttpHeaders;

import mx.com.liverpool.credito.excepciones.RestServiceClientUtilsExcepction;




/**
 * utileria para acceder a servicios rest
 * @author jcgarces
 * @version 1.0
 * @updated 13-mar-2019 05:57:12 p.m.
 */
public interface RestServiceClientUtils {
	
	/**
	 * ejecuta servicio get 
	 * @param uri
	 * @return
	 */
	<R> R ejecutarServicioGet(String servidor, String uri, Class<R> clase);
	
	/**
	 * lista de objetos con get
	 * @param uri
	 * @param claseRespuesta de la lista
	 * @return
	 */
	<R> List<R> ejecutarServicioGetObjetos(String servidor, String uri, Class<R> claseRespuesta);
	
	/**
	 * ejecuta servicio post
	 * @param uri
	 * @param peticion
	 * @param claseRespuesta
	 * @return
	 */
	<P, R> R ejecutarServicioPost(String servidor, String uri, P peticion, Class<R> claseRespuesta);

	/**
	 * envia servicio post con header parametrizados
	 * @param url
	 * @param peticion
	 * @param headers
	 * @param claseRespuesta
	 * @return
	 */
	<P, R> R ejecutarServicioPostwithHeaders(String url, P peticion, HttpHeaders headers, Class<R> claseRespuesta);

	
	/**
	 * ejecuta servicio post
	 * @param uri
	 * @param peticion
	 * @param claseRespuesta
	 * @return
	 */
	<P, R> R ejecutarServicioPost(String url, P peticion, Class<R> claseRespuesta);
	
	/**
	 * ejecuta servicio post, recibiendo directamente el objeto sin el Responseentity
	 * @param uri
	 * @param peticion
	 * @param claseRespuesta
	 * @return
	 */
	<P, R> R ejecutarServicioPostParaObjetos(String servidor, String uri, P peticion, Class<R> claseRespuesta);

	/**
	 * ejecuta servicio PUT
	 * @param servidor
	 * @param uri
	 * @param peticion
	 */
	<P, R> R ejecutarServicioPut(String servidor, String uri, P parametro, Class<R> claseRespuesta);

	/**
	 * ejecuta un servicio delete
	 * @param servidor
	 * @param uri
	 */
	void ejecutarServicioDelete(String servidor, String uri);

	/**
	 * llama un servicio rest con un xml
	 * @param servidor
	 * @param uri
	 * @param peticion
	 * @param claseRespuesta
	 * @return
	 * @throws KeyStoreException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	<P, R> R ejecutarServicePostXml(String servidor, String uri, P peticion,
			Class<R> claseRespuesta) throws RestServiceClientUtilsExcepction;

	/**
	 * llama un servicio rest con un xml
	 * @param url
	 * @param peticion
	 * @param claseRespuesta
	 * @return
	 * @throws KeyStoreException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	<P, R> R ejecutarServicePostXml(String url, P peticion, Class<R> claseRespuesta) throws RestServiceClientUtilsExcepction;

	/**
	 * ejecuta delete
	 * @param uri
	 * @param peticion
	 * @param headers
	 * @param claseRespuesta
	 * @return
	 */
	<P, R> R ejecutarServicioDelete(String uri, P peticion, HttpHeaders headers, Class<R> claseRespuesta);

	/**
	 * ejectua servicio put con headers
	 * @param url
	 * @param parametro
	 * @param headers
	 * @param claseRespuesta
	 * @return
	 */
	<P, R> R ejecutarServicioPut(String url, P parametro, HttpHeaders headers, Class<R> claseRespuesta);


}
