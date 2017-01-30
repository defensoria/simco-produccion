/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.type;

/**
 *
 * @author carlos
 */

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EstadoCasoType {

BORRADOR("01", "Borrador"),	

PROPUESTA("02", "Propuesta"),

PRECONFLICTO("03", "Pre-Conflicto"),

ACTIVO("04", "Activo"),

LATENTE("05", "Latente"),

RESUELTO("06", "Resuelto"),

	RETIRADO("07", "Retirado");
	
	/** La Constante list. */
	private static final List<EstadoCasoType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, EstadoCasoType> lookup = new HashMap<>();

	static {
		for (EstadoCasoType s : EnumSet.allOf(EstadoCasoType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private final String key;
	
	/** El value. */
	private final String value;

	/**
	 * Instancia un nuevo tipo via type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private EstadoCasoType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	/**
	 * Obtiene key.
	 *
	 * @return Retorna un valor de tipo String para el key del tipo de via.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Obtiene value.
	 *
	 * @return Retorna un valor de tipo String para el valor del tipo de via.
	 */
	public String getValue() {
		return value;
	}

	public static EstadoCasoType get(String key) {
		return lookup.get(key);
	}
}
