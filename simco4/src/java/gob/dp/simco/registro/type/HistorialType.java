/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.registro.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author carlos
 */
public enum HistorialType {
    
    HISTORIAL_CREACION("01", "Creacion", "GEN"),

    HISTORIAL_ACTUALIZACION("02", "Actualizacion", "GEN"),
    
    HISTORIAL_ELIMINACION("03", "Eliminacion", "GEN"),
    
    HISTORIAL_VINCULACION("04", "Vinculacion", "GEN"),
    
    HISTORIAL_DESVINCULACION("05", "Desvinculacion", "GEN"),
    
    
    
    ENTIDAD_ACTIVIDAD("01", "Actividad", "ENT"),
    
    ENTIDAD_ACTOR("02", "Actor", "ENT"),
    
    ENTIDAD_ACONTECIMIENTO("03", "Acontecimiento", "ENT"),
    
    ENTIDAD_CASO("04", "Caso", "ENT"),
    
    ENTIDAD_ACTAACUERDO("05", "ActaAcuerdo", "ENT"),
    
    ENTIDAD_MEDIOVERIFICACION("06", "MedioVerificacion", "ENT");
	
	
	/** La Constante list. */
	private static final List<HistorialType> list = new ArrayList<HistorialType>();
	
	/** La Constante lookup. */
	private static final Map<String, HistorialType> lookup = new HashMap<String, HistorialType>();

	static {
		for (HistorialType s : EnumSet.allOf(HistorialType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}
	}
	
	/** El key. */
	private final String key;
	
	/** El value. */
	private final String value;
	
	/** El tipo. */
	private final String tipo;

	/**
	 * Instancia un nuevo tipo via type.
	 *
	 * @param key el key
	 * @param value el value
         * @param tipo el value
	 */
	private HistorialType(String key, String value, String tipo) {
		this.key = key;
		this.value = value;
		this.tipo = tipo;
	}

	/**
	 * Obtiene key.
	 *
	 * @return Retorna un valor de tipo String para el key del tipo de via.
	 */
	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getTipo() {
		return tipo;
	}
	
	public String getDescription() {
		return this.getValue();
	}

	public static HistorialType get(String key) {
		return lookup.get(key);
	}

    
}
