package mx.com.liverpool.credito.utilerias;

import java.util.List;

/**
 * Metodo de parseo de objetos a Json y visceversa
 * @author cmejia 08-10-2015
 * @version 1.0
 * @updated 13-mar-2019 05:57:12 p.m.
 */
public interface JsonUtil {

	/**
	 * convierte ena cadena JSON a objeto de la clase T
	 * @param json
	 * @param typeParamClass
	 * @return
	 */
	<T> T convertirJsonObjeto(String json, Class<T> typeParamClass);

	/**
	 * convierte un alista de objetos a su correspondiente cadena JSON
	 * @param objs
	 * @return
	 */
	<T> String convertirListaObjetosJson(List<T> objs);

	/**
	 * 
	 * Este metodo parsea un objeto a un String con formato JSON
	 * 
	 * @param obj el objeto que se quiere parsear a formato JSON
	 * @return un String con formato JSON del objeto parseado
	 */
	<T> String convertirObjetoJson(T obj);

	/**
	 * convierte xml a objeto
	 * @param json
	 * @param typeParamClass
	 * @return
	 */
	<T> T parseXmlToObject(String json, Class<T> typeParamClass);
	
	/**
	 * convierte objeto a xml
	 * @param obj
	 * @return
	 */
	<T> String convertirObjetoAXml(T obj);

	/**
	 * cre auna lista de objetos T
	 * @param json
	 * @param typeClass
	 * @return
	 */
	<T> List<T> convertirJsonListaObjetos(String json, Class<T> typeClass);
	
}
