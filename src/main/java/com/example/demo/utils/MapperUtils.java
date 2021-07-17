package com.example.demo.utils;

import com.example.demo.DTO.EntidadDto;
import com.example.demo.entidad.AbstractEntidad;
import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.util.Assert.notNull;


/**
 * Clase encargada de todos los mapeos
 *
 * @author
 * @since 1.0
 *
 */
@Service(value = "mapper-utils")
public class MapperUtils {

	@Resource(name = "dozer-mapper")
	private Mapper mapper;

	@Autowired
	DozerBeanMapper dozerBeanMapper;


	/**
	 * Método para verificar que todos los parámetros han sido inicializados
	 * correctamente.
	 *
	 * @author
	 * @since 1.0
	 */
	public void init() {
		notNull(mapper, "La propiedad [mapperFactory] no ha sido cargada correctamente.");
	}

	/**
	 *
	 * Método para pasar información de una instancia de {@link AbstractEntidad} a una
	 * instancia de {@link EntidadDto}
	 *
	 * @author
	 * @since 1.0
	 *
	 * @param entidad
	 *            - Instancia de donde se obtienen los datos
	 * @param clazz
	 *            - Clase de la cual hay que crear una nueva instancia
	 * @return Una instancia de la clase declarada cargada con los datos de los
	 *         atributos que coinciden en ambas clases
	 */
	public <D extends EntidadDto> D mapeoEntidad(AbstractEntidad entidad, Class<D> clazz) {
		return mapper.map(entidad, clazz);
	}

	/**
	 *
	 * Método para pasar información de una instancia de {@link EntidadDto} a una
	 * instancia de {@link AbstractEntidad} *
	 *
	 * @author
	 * @since 1.0
	 *
	 * @param EntidadDto
	 *            - Instancia de donde se obtienen los datos
	 * @param clazz
	 *            - Clase de la cual hay que crear una nueva instancia
	 * @return Una instancia de la clase declarada cargada con los datos de los
	 *         atributos que coinciden en ambas clases
	 */
	public <E extends AbstractEntidad> E mapeoDto(EntidadDto dto, Class<E> clazz) {
		return mapper.map(dto, clazz);
	}

	/**
	 *
	 * Método para pasar información de una Lista de instancia de {@link AbstractEntidad} a una
	 * Lista de instancia de {@link EntidadDto}
	 *
	 * @author
	 * @since 1.0
	 *
	 * @param listaEntidad
	 *            - Lista de Instancias de donde se obtienen los datos
	 * @param clazz
	 *            - Clase de la cual hay que crear nuevas instancias
	 * @return Una instancia de la Lista cargada con los datos de los
	 *         atributos que coinciden en ambas clases
	 */
	public <D extends EntidadDto, E extends AbstractEntidad> List<D> mapeoListaEntidad(List<E> listaEntidad, Class<D> clazz) {
		List<D> listaMapeada = new ArrayList<>();
		for(E entidad : listaEntidad){
			D dto = mapper.map(entidad, clazz);
			listaMapeada.add(dto);
		}
		return listaMapeada;
	}

	/**
	 *
	 * Método para pasar información de una Lista de instancia de instancia de {@link EntidadDto} a una
	 * Lista de instancia de {@link AbstractEntidad} *
	 *
	 * @author
	 * @since 1.0
	 *
	 * @param listDto
	 *            - Lista de Instancias de donde se obtienen los datos
	 * @param clazz
	 *            - Clase de la cual hay que crear nuevas instancias
	 * @return Una instancia de la Lista cargada con los datos de los
	 *         atributos que coinciden en ambas clases
	 */
	public <E extends AbstractEntidad, D extends EntidadDto> List<E> mapListaDto(List<D> listDto, Class<E> clazz) {
		List<E> listaMapeada = new ArrayList<>();
		for(D dto : listDto){
			E entidad = mapper.map(dto, clazz);
			listaMapeada.add(entidad);
		}
		return listaMapeada;
	}

	/**
	 *
	 * Método para pasar información de un Set de {@link AbstractEntidad} a un
	 * Set de instancia de {@link EntidadDto}
	 *
	 * @author
	 * @since 1.0
	 *
	 * @param setEntidad
	 *            - Lista de Instancias de donde se obtienen los datos
	 * @param clazz
	 *            - Clase de la cual hay que crear nuevas instancias
	 * @return Una instancia de Set cargada con los datos de los
	 *         atributos que coinciden en ambas clases
	 */
	public <D extends EntidadDto, E extends AbstractEntidad> Set<D> mapeoSetEntidad(Set<E> listaEntidad, Class<D> clazz) {
		Set<D> setMapeado = new HashSet<>();
		for(E entidad : listaEntidad){
			D dto = mapper.map(entidad, clazz);
			setMapeado.add(dto);
		}
		return setMapeado;
	}

	/**
	 *
	 * Método para pasar información de una instancia de {@link Object} a una
	 * instancia de {@link EntidadDto}
	 *
	 * @author damian.paravati
	 * @since 1.0
	 *
	 * @param objeto
	 *            - Instancia de donde se obtienen los datos
	 * @param clazz
	 *            - Clase de la cual hay que crear una nueva instancia
	 * @return Una instancia de la clase declarada cargada con los datos de los
	 *         atributos que coinciden en ambas clases
	 */
	public <D extends EntidadDto> D mapeoObjeto(Object objeto, Class<D> clazz) {
		return mapper.map(objeto, clazz);
	}

	/**
	 *
	 * Método para pasar información de una instancia de {@link Object} a una
	 * instancia de {@link Object}
	 *
	 * @author damian.paravati
	 * @since 1.0
	 *
	 * @param objeto
	 *            - Instancia de donde se obtienen los datos
	 * @param clazz
	 *            - Clase de la cual hay que crear una nueva instancia
	 * @return Una instancia de la clase declarada cargada con los datos de los
	 *         atributos que coinciden en ambas clases
	 */
	public <D extends Object> D mapeoObjetoObjeto(Object objeto, Class<D> clazz) {
		return mapper.map(objeto, clazz);
	}

	/**
	 *
	 * Método para pasar información de una instancia de {@link Object} a una
	 * instancia de {@link Object}
	 *
	 * @author damian.paravati
	 * @since 1.0
	 *
	 * @param objeto
	 *            - Instancia de donde se obtienen los datos
	 * @param clazz
	 *            - Clase de la cual hay que crear una nueva instancia
	 * @param mapId
	 *            - id del xml que se quiere usar como mapper
	 * @return Una instancia de la clase declarada cargada con los datos de los
	 *         atributos que coinciden en ambas clases
	 */
	public <D extends Object> D mapeoObjetoObjeto(Object objeto, Class<D> clazz, String mapId) {
		return mapper.map(objeto, clazz, mapId);
	}

	/**
	 *
	 * Método para pasar información de una Lista de instancia de {@link Object} a una
	 * Lista de instancia de {@link EntidadDto}
	 *
	 * @author damian.paravati
	 * @since 1.0
	 *
	 * @param listaObjeto
	 *            - Lista de Instancias de donde se obtienen los datos
	 * @param clazz
	 *            - Clase de la cual hay que crear nuevas instancias
	 * @return Una instancia de la Lista cargada con los datos de los
	 *         atributos que coinciden en ambas clases
	 */
	public <D extends EntidadDto, E extends Object> List<D> mapeoListaObjeto(List<E> listaObjeto, Class<D> clazz) {
		List<D> listaMapeada = new ArrayList<>();
		for(E objeto : listaObjeto){
			D dto = mapper.map(objeto, clazz);
			listaMapeada.add(dto);
		}
		return listaMapeada;
	}

	/**
	 *
	 * Método para pasar información de una Lista de instancia de {@link Object} a una
	 * Lista de instancia de {@link Object}
	 *
	 * @author damian.paravati
	 * @since 1.0
	 *
	 * @param listaObjeto
	 *            - Lista de Instancias de donde se obtienen los datos
	 * @param clazz
	 *            - Clase de la cual hay que crear nuevas instancias
	 * @return Una instancia de la Lista cargada con los datos de los
	 *         atributos que coinciden en ambas clases
	 */
	public <D extends Object, E extends Object> List<D> mapeoListaObjetoObjeto(List<E> listaObjeto, Class<D> clazz) {
		List<D> listaMapeada = new ArrayList<>();
		for(E objeto : listaObjeto){
			D dto = mapper.map(objeto, clazz);
			listaMapeada.add(dto);
		}
		return listaMapeada;
	}

	/**
	 *
	 * Método para pasar información de una instancia de {@link Object} a una
	 * instancia dada de {@link Object}
	 *
	 * @author damian.paravati
	 * @since 1.0
	 *
	 * @param objeto
	 *            - Instancia de donde se obtienen los datos
	 * @param objetoDestino
	 *            - Instancia de objeto donde se deben cargar los datos.
	 *
	 */
	public void mapeoObjetoInstancia(Object objeto, Object objetoDestino) {
		mapper.map(objeto, objetoDestino);
	}

	/**
	 *
	 * Método para pasar información de una Lista de instancia de {@link Object} a una
	 * Lista de instancia de {@link Object}
	 *
	 * @author damian.paravati
	 * @since 1.0
	 *
	 * @param listaObjeto
	 *            - Lista de Instancias de donde se obtienen los datos
	 * @param clazz
	 *            - Clase de la cual hay que crear nuevas instancias
	 * @param mapId
	 *            - id del xml que se quiere usar como mapper
	 * @return Una instancia de la Lista cargada con los datos de los
	 *         atributos que coinciden en ambas clases
	 */
	public <D extends Object, E extends Object> List<D> mapeoListaObjetoObjeto(List<E> listaObjeto, Class<D> clazz, String mapId) {
		List<D> listaMapeada = new ArrayList<>();
			for(E objeto : listaObjeto){
				D dto = mapper.map(objeto, clazz, mapId);
				listaMapeada.add(dto);
			}
			return listaMapeada;
		}
}
