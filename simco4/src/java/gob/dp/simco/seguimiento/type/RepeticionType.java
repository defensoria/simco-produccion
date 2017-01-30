package gob.dp.simco.seguimiento.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

public enum RepeticionType {

	DIARIO("DIA", "Diario");

	//SEMANAL("SEM", "Semanal");
        
	
	/** La Constante list. */
	private static final List<RepeticionType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, RepeticionType> lookup = new HashMap<>();

	static {
		for (RepeticionType s : EnumSet.allOf(RepeticionType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	
	private RepeticionType(String key, String value) {
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
		for (RepeticionType s : list) {
			SelectItem select = new SelectItem();
			select.setValue(s.getKey());
			select.setLabel(s.getValue());
			rList.add(select);
		}
		return rList;
	}
        
	public static RepeticionType get(String key) {
		return lookup.get(key);
	}
}
