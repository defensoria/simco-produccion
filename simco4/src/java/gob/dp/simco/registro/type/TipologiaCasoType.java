/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.registro.type;


import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public enum TipologiaCasoType {
        
        AGL("01", "AGL"),
                
        AGN("02", "AGN"),
        
        AGR("03", "AGR"),
        
        COM("04", "COM"),
        
        CIC("05", "CIC"),
        
        DEM("06", "DEM"),
        
        ELE("07", "ELE"),
        
        LAB("08", "LAB"),
        
        SOC("09", "SOC"),
        
        OTR("10", "OTR");
	
	/** La Constante list. */
	private static final List<TipologiaCasoType> list = new ArrayList<>();
	
	/** La Constante lookup. */
	private static final Map<String, TipologiaCasoType> lookup = new HashMap<>();

	static {
		for (TipologiaCasoType s : EnumSet.allOf(TipologiaCasoType.class)) {
			list.add(s);
			lookup.put(s.getKey(), s);
		}

	}
	
	/** El key. */
	private final String key;
	
	/** El value. */
	private final String value;

	
	private TipologiaCasoType(String key, String value) {
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

        public static String tipoClasificacionNombre(String codigo){
            if(StringUtils.equals(codigo, AGL.key))
                return AGL.value;
            if(StringUtils.equals(codigo, AGN.key))
                return AGN.value;
            if(StringUtils.equals(codigo, AGR.key))
                return AGR.value;
            if(StringUtils.equals(codigo, COM.key))
                return COM.value;
            if(StringUtils.equals(codigo, CIC.key))
                return CIC.value;
            if(StringUtils.equals(codigo, DEM.key))
                return DEM.value;
            if(StringUtils.equals(codigo, ELE.key))
                return ELE.value;
            if(StringUtils.equals(codigo, LAB.key))
                return LAB.value;
            if(StringUtils.equals(codigo, SOC.key))
                return SOC.value;
            if(StringUtils.equals(codigo, OTR.key))
                return OTR.value;
            return "";
        }
        
	public static TipologiaCasoType get(String key) {
		return lookup.get(key);
	}
}
