/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.seguimiento.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

public enum DiasUltimosType {

	ULTIMOS_7(7, "Ultimos 7 dias"),

	ULTIMOS_6(6, "Ultimos 6 dias"),
        
        ULTIMOS_5(5, "Ultimos 5 dias"),
        
        ULTIMOS_4(4, "Ultimos 4 dias"),
        
        ULTIMOS_3(3, "Ultimos 3 dias"),
        
        ULTIMOS_2(2, "Ultimos 2 dias"),
        
        ULTIMOS_1(1, "Ultimos 1 dias");
        
        
	
	
	/** La Constante list. */
	private static final List<DiasUltimosType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, DiasUltimosType> lookup = new HashMap<>();

	static {
		for (DiasUltimosType s : EnumSet.allOf(DiasUltimosType.class)) {
			list.add(s);
			lookup.put(s.getKey().toString(), s);
		}
	}
	
	/** El key. */
	private final Integer key;
	
	/** El value. */
	private final String value;
	

	/**
	 * Instancia un nuevo tipo via type.
	 *
	 * @param key el key
	 * @param value el value
	 */
	private DiasUltimosType(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
        
        public static List<SelectItem> getList() {
		List<SelectItem> rList = new ArrayList<>();
		for (DiasUltimosType s : list) {
			SelectItem select = new SelectItem();
			select.setValue(s.getKey());
			select.setLabel(s.getValue());
			rList.add(select);
		}
		return rList;
	}

	/**
	 * Obtiene key.
	 *
	 * @return Retorna un valor de tipo String para el key del tipo de via.
	 */
	public Integer getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}
	
	public String getDescription() {
		return this.getValue();
	}

	public static DiasUltimosType get(String key) {
		return lookup.get(key);
	}
}
