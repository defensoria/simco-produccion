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

public enum DiasProximosType {

	PROXIMOS_7(7, "Próximos 7 días"),

	PROXIMOS_6(6, "Próximos 6 días"),
        
        PROXIMOS_5(5, "Próximos 5 días"),
        
        PROXIMOS_4(4, "Próximos 4 días"),
        
        PROXIMOS_3(3, "Próximos 3 días"),
        
        PROXIMOS_2(2, "Próximos 2 días"),
        
        PROXIMOS_1(1, "Próximos 1 días");
        
        
	
	
	/** La Constante list. */
	private static final List<DiasProximosType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, DiasProximosType> lookup = new HashMap<>();

	static {
		for (DiasProximosType s : EnumSet.allOf(DiasProximosType.class)) {
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
	private DiasProximosType(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
        
        public static List<SelectItem> getList() {
		List<SelectItem> rList = new ArrayList<>();
		for (DiasProximosType s : list) {
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

	public static DiasProximosType get(String key) {
		return lookup.get(key);
	}
}
