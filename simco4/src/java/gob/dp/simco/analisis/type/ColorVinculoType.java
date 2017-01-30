/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gob.dp.simco.analisis.type;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public enum ColorVinculoType {

	COLOR_ALIANZA(1, "yellow"),

	COLOR_VINCULO_CERCANO(2, "green"),
        
        COLOR_VINCULO_DEBIL(3, "blue"),
        
        COLOR_TENSION(4, "red"),
        
        COLOR_CONFLICTO(5, "black");
        
        
	
	
	/** La Constante list. */
	private static final List<ColorVinculoType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, ColorVinculoType> lookup = new HashMap<>();

	static {
		for (ColorVinculoType s : EnumSet.allOf(ColorVinculoType.class)) {
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
	private ColorVinculoType(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
        
        public static String getValues(Integer keys){
		String val ="";
		if(Objects.equals(keys, ColorVinculoType.COLOR_ALIANZA.key) )
			val = ColorVinculoType.COLOR_ALIANZA.value;
                
                if(Objects.equals(keys, ColorVinculoType.COLOR_VINCULO_CERCANO.key) )
			val = ColorVinculoType.COLOR_VINCULO_CERCANO.value;
                
                if(Objects.equals(keys, ColorVinculoType.COLOR_VINCULO_DEBIL.key) )
			val = ColorVinculoType.COLOR_VINCULO_DEBIL.value;
                
                if(Objects.equals(keys, ColorVinculoType.COLOR_TENSION.key) )
			val = ColorVinculoType.COLOR_TENSION.value;
                
                if(Objects.equals(keys, ColorVinculoType.COLOR_CONFLICTO.key) )
			val = ColorVinculoType.COLOR_CONFLICTO.value;
                
		return val;
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

	public static ColorVinculoType get(String key) {
		return lookup.get(key);
	}
}
