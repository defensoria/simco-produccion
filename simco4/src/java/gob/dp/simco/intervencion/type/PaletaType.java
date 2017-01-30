package gob.dp.simco.intervencion.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum PaletaType {
        
        BLUE("1", "blue"),
                
        RED("2", "red"), 
        
        GREEN("3", "green"), 
        
        YELLOW("4", "yellow"), 
        
        ORANGE("5", "orange"), 
        
        GRAY("6", "gray"), 
        
        PURPLE("7", "purple"), 
        
        PINK("8", "pink"), 

        MAROON("9", "maroon");

	
	/** La Constante list. */
	private static final List<PaletaType> list = new ArrayList<PaletaType>();
	
	/** La Constante lookup. */
	private static final Map<String, PaletaType> lookup = new HashMap<String, PaletaType>();

	static {
		for (PaletaType s : EnumSet.allOf(PaletaType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private String key;
	
	/** El value. */
	private String value;

	
	private PaletaType(String key, String value) {
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
        
	public static PaletaType get(String key) {
		return lookup.get(key);
	}
}
