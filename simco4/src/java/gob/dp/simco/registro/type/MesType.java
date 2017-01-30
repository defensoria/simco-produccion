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

public enum MesType {

	ENE("01", "ENE"),

	FEB("02", "FEB"),
	
	MAR("03", "MAR"),
	
	ABR("04", "ABR"),
	
	MAY("05", "MAY"),
	
	JUN("06", "JUN"),
	
	JUL("07", "JUL"),
	
	AGO("08", "AGO"),
	
	SEP("09", "SEP"),
	
	OCT("10", "OCT"),
	
	NOV("11", "NOV"),
	
	DIC("12", "DIC");
	
	/** La Constante list. */
	private static final List<MesType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, MesType> lookup = new HashMap<>();

	static {
		for (MesType s : EnumSet.allOf(MesType.class)) {
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
	private MesType(String key, String value) {
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

	public String getDescription() {
		return this.getValue();
	}

	public static MesType get(String key) {
		return lookup.get(key);
	}
}
