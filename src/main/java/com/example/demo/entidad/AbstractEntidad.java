package com.example.demo.entidad;

import javax.persistence.MappedSuperclass;

/**
 * Clase abstracta en la cima de la jerarquía de entidades persistentes
 * 

 */
@MappedSuperclass
public interface AbstractEntidad {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public abstract int hashCode();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public abstract boolean equals(Object object);
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public abstract String toString();

}
