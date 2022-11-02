package mx.com.liverpool.credito.servicios.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.com.liverpool.credito.servicios.VersionService;

/**
 * 
 *  consuitlar version del aplicativo
 * @author JCGARCESO
 *
 */
@Service("versionService")
public class VersionServiceImpl extends BaseService implements VersionService {

	/**
	 * version
	 */
	@Value("${version}")
	private String version;
	
	/**
	 * fecha
	 */
	@Value("${fecha}")
	private String fecha;
	
	/**
	 * perfil
	 */
	@Value("${profile}")
	private String perfil;
	
	/**
	 * consultar version y fecha de construcción del aplicativo
	 * @return String
	 */
	@Override
	public String consultarVersion() {
		String regreso = new StringBuilder()
				.append("version : ")
				.append(version)
				.append(System.lineSeparator())
				.append("Fecha : ")
				.append(fecha)
				.append(System.lineSeparator())
				.append("Perfil : ")
				.append(perfil)
				.toString();
		logger.info("Respuesta {}", regreso);
		return regreso;
	}

}
