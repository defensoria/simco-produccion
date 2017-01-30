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

public enum ColorType {

	COLOR_AZUL(1, "blue"),

	COLOR_ROJO(2, "red"),
        
        COLOR_VERDE(3, "green"),
        
        COLOR_AMARILLO(4, "yellow"),
        
        COLOR_NARANJA(5, "orange"),
        
        COLOR_VIOLETA(6, "violet"),
        
        COLOR_GRIS(7, "silver"),
        
        COLOR_SALMON(8, "salmon"),
        
        COLOR_GRIS_OSCURO(9, "#FFFF1FFF");
        
        
        
	
	
	/** La Constante list. */
	private static final List<ColorType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, ColorType> lookup = new HashMap<>();

	static {
		for (ColorType s : EnumSet.allOf(ColorType.class)) {
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
	private ColorType(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
        
        public static String getValues(Integer keys){
		String val ="";
		if(Objects.equals(keys, ColorType.COLOR_AZUL.key) )
			val = ColorType.COLOR_AZUL.value;
                
                if(Objects.equals(keys, ColorType.COLOR_ROJO.key) )
			val = ColorType.COLOR_ROJO.value;
                
                if(Objects.equals(keys, ColorType.COLOR_VERDE.key) )
			val = ColorType.COLOR_VERDE.value;
                
                if(Objects.equals(keys, ColorType.COLOR_AMARILLO.key) )
			val = ColorType.COLOR_AMARILLO.value;
                
                if(Objects.equals(keys, ColorType.COLOR_NARANJA.key) )
			val = ColorType.COLOR_NARANJA.value;
                
                if(Objects.equals(keys, ColorType.COLOR_VIOLETA.key) )
			val = ColorType.COLOR_VIOLETA.value;
                
                if(Objects.equals(keys, ColorType.COLOR_GRIS.key) )
			val = ColorType.COLOR_GRIS.value;
                
                if(Objects.equals(keys, ColorType.COLOR_SALMON.key) )
			val = ColorType.COLOR_SALMON.value;
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

	public static ColorType get(String key) {
		return lookup.get(key);
	}
}
