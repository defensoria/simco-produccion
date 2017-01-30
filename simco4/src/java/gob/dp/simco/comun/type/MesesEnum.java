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

public enum MesesEnum {

	ENERO("01", "Enero"),

	FEBRERO("02", "Febrero"),
	
	MARZO("03", "Marzo"),
	
	ABRIL("04", "Abril"),
	
	MAYO("05", "Mayo"),
	
	JUNIO("06", "Junio"),
	
	JULIO("07", "Julio"),
	
	AGOSTO("08", "Agosto"),
	
	SEPTIEMBRE("09", "Septiembre"),
	
	OCTUBRE("10", "Octubre"),
	
	NOVIEMBRE("11", "Noviembre"),
	
	DICIEMBRE("12", "Diciembre");
	
	/** La Constante list. */
	private static final List<MesesEnum> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, MesesEnum> lookup = new HashMap<>();

	static {
		for (MesesEnum s : EnumSet.allOf(MesesEnum.class)) {
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
	private MesesEnum(String key, String value) {
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
		for (MesesEnum s : list) {
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
	public static MesesEnum get(String key) {
		return lookup.get(key);
	}
}
