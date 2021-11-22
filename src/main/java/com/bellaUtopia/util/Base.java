package com.bellaUtopia.util;

import java.lang.reflect.Method;
import java.util.HashMap;

import com.bellaUtopia.excepciones.ApplicationException;

public abstract class Base<P>{
	/**
	 * Retorna los parámetros no nulos con sus respectivos valores con 
	 * java reflection
	 * @param filtros
	 * @return Un hashmap con la propiedad y su valor
	 * @author Walter Insaurralde <kirawoi@gmail.com>
	 * @throws ApplicationException
	 */
	public HashMap<String, Object> getParametros(P filtros) throws ApplicationException{
		HashMap<String, Object> map = new HashMap<String, Object>();
		Method[] metodos = filtros.getClass().getMethods();
    	for(int x = 0 ;  x < metodos.length ; x++){
    		
    		if(metodos[x].getName().startsWith("get") && "getClass".compareTo(metodos[x].getName())!=0){

        		System.out.println("TIPO DE RETORNO: "+ metodos[x].getReturnType().toString());
        		System.out.println("TIPO DE RETORNO GENERICO: "+ metodos[x].getGenericReturnType().toString());
        		if(metodos[x].getReturnType().toString().equals("String")){
            		System.out.println("Es String: -" +metodos[x].getName() );    			
        		}else{
            		System.out.println("No String: -" +metodos[x].getName() );
        		}
        		
    			try{
    				Method method= metodos[x];
    				Object valor = metodos[x].invoke(filtros, null);
    				
    				String propiedad = getPropiedad(method.getName());
    				if(propiedad != null && valor != null){
    					
    					map.put(propiedad,valor);
    				}
    			}catch (Exception ex) {
    				throw new ApplicationException("Error al obtener parámetros con java reflection");
				}
    			
    			
    		}
    	}
    	
		return map;
		
	}
	
	/**
	 * Obtiene la propiedad a partir del nombre del método
	 * por ej. getNombre => nombre
	 * @param metodo
	 * @return Nombre de la propiedad de un método de un pojo
	 * @author Walter Insaurralde <kirawoi@gmail.com>
	 */
	private String getPropiedad(String metodo){
			String propiedad = metodo.substring(3);
		propiedad = propiedad.substring(0, 1).toLowerCase()+propiedad.substring(1);
		return propiedad;
		
	}
}
