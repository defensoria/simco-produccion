package gob.dp.simco.seguimiento.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

public enum TiempoType {
        
        DIA("DIA", "Dias");
                
        //MES("MES", "Meses");   
	
	/** La Constante list. */
	private static final List<TiempoType> list = new ArrayList<TiempoType>();
	
	/** La Constante lookup. */
	private static final Map<String, TiempoType> lookup = new HashMap<String, TiempoType>();

	static {
		for (TiempoType s : EnumSet.allOf(TiempoType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	
	private TiempoType(String key, String value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	/*public String getDescription() {
		return this.getValue();
	}*/

	public static List<SelectItem> getList() {
		List<SelectItem> rList = new ArrayList<>();
		for (TiempoType s : list) {
			SelectItem select = new SelectItem();
			select.setValue(s.getKey());
			select.setLabel(s.getValue());
			rList.add(select);
		}
		return rList;
	}
        
	public static TiempoType get(String key) {
		return lookup.get(key);
	}
}
