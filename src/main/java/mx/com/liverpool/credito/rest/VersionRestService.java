/**
 * 
 */
package mx.com.liverpool.credito.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mx.com.liverpool.credito.servicios.VersionService;
import mx.com.liverpool.credito.servicios.impl.BaseService;

/**
 * regresa la version y fecha de construccion del aplicativo
 * @author JCGARCESO
 *
 */
@RestController
public class VersionRestService extends BaseService {

	/**
	 * servicio de negocio
	 */
	@Autowired
	private VersionService versionService;

	
	/**
	 * regresa la versión del aplicativo
	 * @return
	 */
	@GetMapping( path = "/version", produces = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public String consultarVersion() {
		String regreso = versionService.consultarVersion();
		logger.info("regreso {}", regreso);
		return regreso;
	}
	
}
