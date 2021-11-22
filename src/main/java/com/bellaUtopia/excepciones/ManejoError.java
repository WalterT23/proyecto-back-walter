package com.bellaUtopia.excepciones;

public abstract class ManejoError {
	//Generales
	public static final String ERROR_ARGUMETO_MAL_FORMADO = "Argumento mal formado.";
	public static final String ERROR_REQUIERE_FILTROS = "Requiere de parámtros de tipo filtros={}";
	public static final String ERROR_ENTITY_NULL_VACIO = "Entity nulo o con más de una fila.";
	
	//Para los DAOs
	public static final String ERROR_OBTENER = "Error al obtener la entidad.";
	public static final String ERROR_INSERTAR = "Error al insertar en la tabla.";
	public static final String ERROR_ELIMINAR = "Error al eliminar datos de la tabla.";
	public static final String ERROR_ACTUALIZAR = "Error al actualizar en la tabla.";
	public static final String ERROR_LISTAR = "Error al listar.";
	
	//Para las vistas
	public static final String ERROR_QUERY_NATIVO = "Error al ejecutar la consulta nativa sql: ";
	public static final String ERROR_OBJETO_NULL = "El objeto a eliminar no existe";
	
	
	//Para peticiones y respuestas del servidor
	public static final String ERROR_400 = "La petición REST se encuentra mal formada.";
	public static final String ERROR_401 = "Error de autenticación.";
	public static final String ERROR_403 = "Acceso no permitido.";
	public static final String ERROR_404 = "Recurso REST no encontrado.";
	public static final String ERROR_405 = "El método HTTP no soportado.";
	public static final String ERROR_406 = "El tipo de contenido del cliente no soportado.";
	public static final String ERROR_415 = "El tipo de post del cliente no soportado";
	public static final String ERROR_503 = "Servidor temporalmente caído.";
	public static final String ERROR_500 = "Error interno: ";
	
	
	
	public static final int ESTADO_ERROR_APLICACION = 505;
	public static final String REGISTRO_NO_ENCONTRADO = "No se encontraron registros";
	public static final String ERROR_RESULTADO_MULTIPLE = "No se puede mostrar el entity cuando el resultado no es singular.";
	

	
}
