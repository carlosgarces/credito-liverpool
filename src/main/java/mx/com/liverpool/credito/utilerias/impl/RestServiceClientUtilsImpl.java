/**
 * 
 */
package mx.com.liverpool.credito.utilerias.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import mx.com.liverpool.credito.constantes.NumerosConstant;
import mx.com.liverpool.credito.excepciones.RestServiceClientUtilsExcepction;
import mx.com.liverpool.credito.utilerias.JsonUtil;
import mx.com.liverpool.credito.utilerias.RestServiceClientUtils;
import mx.com.liverpool.credito.utilerias.ValidadorUtils;


/**
 * utileria para acceder a servicios rest
 * 
 * @author jcgarces
 * @version 1.0
 * @updated 13-mar-2019 05:57:12 p.m.
 */
@Component("restServiceClientUtils")
public class RestServiceClientUtilsImpl implements RestServiceClientUtils {

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(RestServiceClientUtilsImpl.class);
	
	/**
	 * mx.com.procesar.servicios.traspasos.portalservicios.utilerias de json
	 */
	@Autowired
	private JsonUtil jsonUtil;
	
	/**
	 * valiudador 
	 */
	@Autowired
	private ValidadorUtils validadorUtils;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mx.com.procesar.esar.portal.util.RestServiceClientUtils#ejecutaServicio
	 * (java.lang.String)
	 */
	@Override
	public <R> R ejecutarServicioGet(String servidor, String uri, Class<R> clase) {
		String url = obtenUrl(servidor, uri);
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Servicio : {}", url);
		ResponseEntity<R> respuesta = restTemplate.getForEntity(url, clase);
		logger.info("Servicio GET: {}", url);
		logger.debug("respuesta : {}", jsonUtil.convertirObjetoJson(respuesta));
		return respuesta.getBody();
	}

	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.traspasos.recepcionarchivos.servicios.RestServiceClientUtils#ejecutaServicioGetObjetos(java.lang.String, java.lang.String, java.lang.Class)
	 */
	@Override
	public <R> List<R> ejecutarServicioGetObjetos(String servidor, String uri,
			Class<R> claseRespuesta) {
		String url = obtenUrl(servidor, uri);
		RestTemplate restTemplate = new RestTemplate();
		List<R> regreso;		
		logger.info("llamando GET {}", url);
		List<R> respuesta = restTemplate.getForObject(url, List.class);
		if (!validadorUtils.esVacio(respuesta) && !respuesta.stream().getClass().equals(claseRespuesta)) {
			regreso = new ArrayList<>();
			for (R actual : respuesta) {
				R r = jsonUtil.convertirJsonObjeto(
						jsonUtil.convertirObjetoJson(actual), claseRespuesta);
				regreso.add(r);
			}
		} else {
			regreso = respuesta;
		}
		logger.info("Servicio GET - Lista: {}", url);
		logger.info("respuesta : {}", regreso);
		return regreso;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.traspasos.recepcionarchivos.servicios.RestServiceClientUtils#ejecutaServicioPost(java.lang.String, java.lang.String, java.lang.Object, java.lang.Class)
	 */
	@Override
	public <P, R> R ejecutarServicioPost(String servidor, String uri,
			P peticion, Class<R> claseRespuesta) {
		String url = obtenUrl(servidor, uri);
		return ejecutarServicioPost(url, peticion, claseRespuesta);
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.traspasos.recepcionarchivos.utilerias.RestServiceClientUtils#ejecutarServicioPostwithHeaders(java.lang.String, java.lang.Object, org.springframework.http.HttpHeaders, java.lang.Class)
	 */
	@Override
	public <P, R> R ejecutarServicioPostwithHeaders(String url, P peticion, HttpHeaders headers, Class<R> claseRespuesta) {
		if(headers == null) {
			headers = new HttpHeaders();
		}
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<P> parametro = new HttpEntity<>(peticion, headers);
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Servicio POST HEADERS: {}", url);
		logger.info("peticion : {}", jsonUtil.convertirObjetoJson(peticion));
		ResponseEntity<R> respuesta = restTemplate.postForEntity(url, parametro,
				claseRespuesta);
		logger.debug("respuesta : {}", jsonUtil.convertirObjetoJson(respuesta));
		return respuesta.getBody();
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.traspasos.recepcionarchivos.utilerias.RestServiceClientUtils#ejecutarServicioPost(java.lang.String, java.lang.Object, java.lang.Class)
	 */
	@Override
	public <P, R> R ejecutarServicioPost(String url, P peticion, Class<R> claseRespuesta) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<P> parametro = new HttpEntity<>(peticion, headers);
			RestTemplate restTemplate = new RestTemplate();
			logger.info("Servicio POST: {}, peticion {}", url,jsonUtil.convertirObjetoJson(peticion));
			ResponseEntity<R> respuesta = restTemplate.postForEntity(url, parametro,
					claseRespuesta);		
			logger.info("respuesta : {}", jsonUtil.convertirObjetoJson(respuesta));
			return respuesta.getBody();
		}catch (Exception e) {
				logger.error("Error", e);
				return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.traspasos.recepcionarchivos.servicios.RestServiceClientUtils#ejecutaServicioPostParaObjetos(java.lang.String, java.lang.String, java.lang.Object, java.lang.Class)
	 */
	@Override
	public <P, R> R ejecutarServicioPostParaObjetos(String servidor, String uri,
			P peticion, Class<R> claseRespuesta) {
		String url = obtenUrl(servidor, uri);
		RestTemplate restTemplate = new RestTemplate();
		logger.info("Servicio : {}, peticion {}", url, jsonUtil.convertirObjetoJson(peticion));
		R respuesta = restTemplate.postForObject(url, peticion, claseRespuesta);
		logger.info("Servicio post {}", url);
		logger.debug("respuesta : {}", jsonUtil.convertirObjetoJson(respuesta));
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.traspasos.recepcionarchivos.utilerias.RestServiceClientUtils#ejecutarServicioPut(java.lang.String, java.lang.String, java.lang.Object, java.lang.Class)
	 */
	@Override
	public <P, R> R ejecutarServicioPut(String servidor, String uri, P parametro, Class<R> claseRespuesta) {
		String url = obtenUrl(servidor, uri);
		return  ejecutarServicioPut(url, parametro, null, claseRespuesta);
	}

	
	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.traspasos.recepcionarchivos.utilerias.RestServiceClientUtils#ejecutarServicioPut(java.lang.String, java.lang.String, java.lang.Object, org.springframework.http.HttpHeaders, java.lang.Class)
	 */
	@Override
	public <P, R> R ejecutarServicioPut(String url, P parametro, HttpHeaders headers,  Class<R> claseRespuesta) {
		
		RestTemplate restTemplate = new RestTemplate();
		if(headers == null) {
			headers = new HttpHeaders();
		}
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<P> peticion = new HttpEntity<>(parametro, headers);
		logger.info("Servicio : PUT -> {}, peticion {}", url, jsonUtil.convertirObjetoJson(peticion));
		ResponseEntity<R> responseEntity =  restTemplate.exchange(url, HttpMethod.PUT, peticion, claseRespuesta);
		logger.info("Respuesta PUT {}", responseEntity);
		return responseEntity.getBody();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.procesar.servicios.traspasos.consultatuafore.servicios.
	 * RestServiceClientUtils#ejecutaServicioDelete(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public <P,R> R ejecutarServicioDelete(String uri, P peticion, HttpHeaders headers, Class<R> claseRespuesta) {
		RestTemplate restTemplate = new RestTemplate();
		if(headers == null) {
			headers = new HttpHeaders();
		}
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<P> entidad = new HttpEntity<>(peticion, headers);
		logger.info("Ejecutando DELETE {} -> {}", uri, jsonUtil.convertirObjetoJson(entidad));
		ResponseEntity<R> responseEntity =  restTemplate.exchange(uri, HttpMethod.DELETE, entidad, claseRespuesta);
		logger.info("Respuesta DELETE {}", responseEntity.getBody());
		return responseEntity.getBody(); 
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.procesar.servicios.traspasos.consultatuafore.servicios.
	 * RestServiceClientUtils#ejecutaServicioDelete(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void ejecutarServicioDelete(String servidor, String uri) {
		String url = obtenUrl(servidor, uri);
		ejecutarServicioDelete(url, null, null, Object.class); 
	}

	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.traspasos.portalservicios.servicios.RestServiceClientUtils#ejecutaSslPostXml(java.lang.String, java.lang.String, java.lang.Object, java.lang.Class)
	 */
	@Override
	public <P, R> R ejecutarServicePostXml(String servidor, String uri, P peticion, Class<R> claseRespuesta)
			throws RestServiceClientUtilsExcepction {
		String url = obtenUrl(servidor, uri);		
		return ejecutarServicePostXml(url, peticion, claseRespuesta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mx.com.procesar.servicios.traspasos.portalservicios.servicios.
	 * RestServiceClientUtils#ejecutaPostXml(java.lang.String, java.lang.Object,
	 * java.lang.Class)
	 */
	@Override
	public <P, R> R ejecutarServicePostXml(String url, P peticion,
			Class<R> claseRespuesta) throws RestServiceClientUtilsExcepction {

		ResponseEntity<R> respuesta = null;
		RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		HttpComponentsClientHttpRequestFactory rf = (HttpComponentsClientHttpRequestFactory) restTemplate
				.getRequestFactory();
		rf.setBufferRequestBody(true);
		restTemplate.setRequestFactory(rf);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_XML);
		String requestXml = jsonUtil.convertirObjetoAXml(peticion);
		logger.debug("peticion {}", requestXml);
		Long inicio = System.currentTimeMillis();
		logger.info("Llamando a servicio post {}", url);
		try {
			 respuesta = restTemplate.postForEntity(url, peticion, claseRespuesta);
			logger.debug("respuesta {}", respuesta);

		} catch (Exception e) {
			logger.error("Fallo al consumir servicio ", e);
			throw new RestServiceClientUtilsExcepction(
					"Error al consumir servicio ", e);
		} finally {
			Long fin = System.currentTimeMillis();
			logger.info("Tiempo {} segundos.\n ", ((fin - inicio) / NumerosConstant.MIL));
		}
		logger.debug("Respuesta {}", respuesta.getBody());

		return respuesta.getBody();

	}


	/**
	 * obtiene la ruta completa del servicio
	 * 
	 * @param ipServidor
	 * @param servicio
	 * @return
	 */
	private String obtenUrl(String ipServidor, String servicio) {
		StringBuilder regreso = new StringBuilder();
		regreso.append(ipServidor).append(servicio);
		return regreso.toString();
	}
	

}
