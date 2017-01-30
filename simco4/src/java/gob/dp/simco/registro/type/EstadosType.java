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

public enum EstadosType {

	ACTOR_ACTIVO("ACTIV", "Activo", "Actor"),

	ACTOR_INACTIVO("INACT", "Inactivo", "Actor");
	
	
	/** La Constante list. */
	private static final List<EstadosType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, EstadosType> lookup = new HashMap<>();

	static {
		for (EstadosType s : EnumSet.allOf(EstadosType.class)) {
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
	private EstadosType(String key, String value, String tipo) {
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

	public static EstadosType get(String key) {
		return lookup.get(key);
	}
}
