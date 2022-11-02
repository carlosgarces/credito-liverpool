/**
 * Esta utilidad permite realizar operaciones de parseo entre entidades JSON y
 * clases java
 */
package mx.com.liverpool.credito.utilerias.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import mx.com.liverpool.credito.utilerias.JsonUtil;

/**
 * Metodo de parseo de objetos a Json y visceversa
 * @author cmejia 08-10-2015
 * @version 1.0
 * @updated 13-mar-2019 05:57:12 p.m.
 */
@Component("jsonUtil")
public class JsonUtilImpl  implements JsonUtil{

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(JsonUtilImpl.class);
	
	/**
	 * constante para log
	 */
	private static final String ERROR = "Ocurrio un error al procesar : ";
	
	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.biometricos.biometricos4h.utilerias.JsonUtil#parseObjectToJson(java.lang.Object)
	 */
	@Override
	public <T> String convertirObjetoJson(T obj) {

		ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
		mapper.writer().withDefaultPrettyPrinter();
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException jsonEx) {
			logger.error("Ocurrio un error al crear el json del objeto.", jsonEx);
		}
		return json;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.biometricos.biometricos4h.utilerias.JsonUtil#parseListObjectToJson(java.util.List)
	 */

	@Override
	public <T> String convertirListaObjetosJson(List<T> objs) {

		ObjectMapper mapper = new ObjectMapper();
		mapper.writer().withDefaultPrettyPrinter();
		StringBuilder json = new StringBuilder().append("[");
		String regreso;
 
		try {			
			if(objs != null ){
				for (T t : objs) {
					json.append(mapper.writeValueAsString(t)).append(",");
				}
			}

		} catch (JsonProcessingException jsonEx) {
			logger.error("Ocurrio un error al crear el json del objeto.", jsonEx);
		}
		json.append("]");
		regreso = StringUtils.replace(json.toString(), "},]", "}]");

		return regreso;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.biometricos.biometricos4h.utilerias.JsonUtil#parseJsonToObject(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> T convertirJsonObjeto(String json, Class<T> typeParamClass) {

		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		T obj = null;
		try {
			obj = mapper.readValue(json.getBytes( StandardCharsets.UTF_8 ), typeParamClass);
		} catch (Exception ex) {
			logger.info("Ocurrio un error al : procesar ", ex);
		}
		return obj;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.traspasos.portalservicios.utilerias.JsonUtil#parseXmlToObject(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> T parseXmlToObject(String xml, Class<T> typeParamClass) {
		ObjectMapper objectMapper = new XmlMapper();
		objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		T t = null;
		try {
			t = objectMapper.readValue(xml, typeParamClass);
		} catch (IOException e) {
			logger.info(ERROR, e);
		}
		
		return t;
	}

	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.traspasos.portalservicios.utilerias.JsonUtil#parseObjectToXml(java.lang.Object)
	 */
	@Override
	public <T> String convertirObjetoAXml(T obj) {
		ObjectMapper objectMapper = new XmlMapper();
		String regreso = null;
		try {
			regreso =  objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.info(ERROR, e);
		}
		return regreso;
	}
	
	/*
	 * (non-Javadoc)
	 * @see mx.com.procesar.servicios.biometricos.serviciobiometricocomunes.utilerias.JsonUtil#parseJsonToObjectList(java.lang.String, java.lang.Class)
	 */
	@Override
	public <T> List<T> convertirJsonListaObjetos(String json, Class<T> typeClass){
		
		ObjectMapper mapper = new ObjectMapper();
		List<T> regreso = null;
		
		try {
			CollectionType type = mapper.getTypeFactory().constructCollectionType(List.class, typeClass);
			regreso =  mapper.readValue(json.getBytes(StandardCharsets.UTF_8), type);
		} catch (IOException e) {
			logger.info("Ocurrio un error al convertir json: ", e);
		}
		return regreso;
	}
}
