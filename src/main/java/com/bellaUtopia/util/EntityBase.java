package com.bellaUtopia.util;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * 
 * @author Walter Insaurralde <kirawoi@gmail.com>
 *
 */

@Embeddable
public class EntityBase implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long id;

	
}
