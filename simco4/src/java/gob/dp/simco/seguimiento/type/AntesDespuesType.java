package gob.dp.simco.seguimiento.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

public enum AntesDespuesType {

	ANTES("ANT", "Antes de"),

        DESPUES("DES", "Despues de");   
	
	/** La Constante list. */
	private static final List<AntesDespuesType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, AntesDespuesType> lookup = new HashMap<>();

	static {
		for (AntesDespuesType s : EnumSet.allOf(AntesDespuesType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	
	private AntesDespuesType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return this.getValue();
	}

	public static List<SelectItem> getList() {
		List<SelectItem> rList = new ArrayList<>();
		for (AntesDespuesType s : list) {
			SelectItem select = new SelectItem();
			select.setValue(s.getKey());
			select.setLabel(s.getValue());
			rList.add(select);
		}
		return rList;
	}
        
	public static AntesDespuesType get(String key) {
		return lookup.get(key);
	}
}
