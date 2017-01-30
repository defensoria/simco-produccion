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

public enum TipoRelacionType {

	VINCULO_ALIANZA(1, "ALI"),

	VINCULO_CERCANO(2, "CER"),
        
        VINCULO_DEBIL(3, "DEB"),
        
        VINCULO_TENSION(4, "TEN"),
        
        VINCULO_CONFLICTO(5, "CON");
        
        
	
	
	/** La Constante list. */
	private static final List<TipoRelacionType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, TipoRelacionType> lookup = new HashMap<>();

	static {
		for (TipoRelacionType s : EnumSet.allOf(TipoRelacionType.class)) {
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
	private TipoRelacionType(Integer key, String value) {
		this.key = key;
		this.value = value;
	}
        
        public static int getKeys(String value){
		int key = 0;
		if(value.equals(TipoRelacionType.VINCULO_ALIANZA.value))
			key = TipoRelacionType.VINCULO_ALIANZA.key;

		if(value.equals(TipoRelacionType.VINCULO_CERCANO.value))
			key = TipoRelacionType.VINCULO_CERCANO.key;
                
                if(value.equals(TipoRelacionType.VINCULO_DEBIL.value))
			key = TipoRelacionType.VINCULO_DEBIL.key;
                
                if(value.equals(TipoRelacionType.VINCULO_TENSION.value))
			key = TipoRelacionType.VINCULO_TENSION.key;
                
                if(value.equals(TipoRelacionType.VINCULO_CONFLICTO.value))
			key = TipoRelacionType.VINCULO_CONFLICTO.key;
                
		return key;
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

	public static TipoRelacionType get(String key) {
		return lookup.get(key);
	}
}
