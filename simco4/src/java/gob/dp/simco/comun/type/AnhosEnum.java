/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun.type;

/**
 *
 * @author carlos
 */
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.model.SelectItem;

public enum AnhosEnum {
    
    ANHO_2017("2017", "2017"),
	
    ANHO_2016("2016", "2016"),
    
    ANHO_2015("2015", "2016"),
    
    ANHO_2014("2014", "2014"),
    
    ANHO_2013("2013", "2013"),
    
    ANHO_2012("2012", "2012"),
    
    ANHO_2011("2011", "2011"),
    
    ANHO_2010("2010", "2010"),
    
    ANHO_2009("2009", "2009"),
    
    ANHO_2008("2008", "2008"),
    
    ANHO_2007("2007", "2007"),
    
    ANHO_2006("2006", "2006"),
    
    ANHO_2005("2005", "2005"),
    
    ANHO_2004("2004", "2004");
	
	
	
	/** La Constante list. */
	private static final List<AnhosEnum> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, AnhosEnum> lookup = new HashMap<>();

	static {
		for (AnhosEnum s : EnumSet.allOf(AnhosEnum.class)) {
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
	private AnhosEnum(String key, String value) {
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

	public static List<SelectItem> getList() {
		List<SelectItem> rList = new ArrayList<>();
		for (AnhosEnum s : list) {
			SelectItem select = new SelectItem();
			select.setValue(s.getKey());
			select.setLabel(s.getValue());
			rList.add(select);
		}
		return rList;
	}
	/**
	 * 
	 * Metodo constructor del Enum TipoViaType con par&aacute;metro.
	 * 
	 * @param key
	 *            Par&aacute;metro de tipo String que determina el key del tipo
	 *            de via.
	 * @return void.
	 */
	public static AnhosEnum get(String key) {
		return lookup.get(key);
	}
}
