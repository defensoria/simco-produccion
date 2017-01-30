/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gob.dp.simco.comun;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.Part;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author carlos
 */
public class FunctionUtil {
    
    private static final Logger log = Logger.getLogger(FunctionUtil.class);
    
    public static String uploadArchive(Part fil) {
            String nameArchive = getFilename(fil);
            String extencion = getFileExtension(getFilename(fil));
            if (StringUtils.isNoneBlank(nameArchive)) {
                String formato = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
                String ruta = formato + extencion;
                File file = new File(ConstantesUtil.FILE_SYSTEM + ruta);
                try (InputStream input = fil.getInputStream()) {
                    Files.copy(input, file.toPath());
                } catch (IOException ex) {
                    log.error(ex);
                }
                return ruta;
            }
        return null;
    }
    
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }

    private static String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
    }
    
    public static final double redondear( double numero, int decimales ) {
        return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
    }
    
    
    public static Integer calcularMesesAFecha(Date fechaInicio, Date fechaFin) {
        try {
            //Fecha inicio en objeto Calendar
            Calendar startCalendar = Calendar.getInstance();
            startCalendar.setTime(fechaInicio);
            //Fecha finalización en objeto Calendar
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(fechaFin);
            //Cálculo de meses para las fechas de inicio y finalización
            int startMes = (startCalendar.get(Calendar.YEAR) * 12) + startCalendar.get(Calendar.MONTH);
            int endMes = (endCalendar.get(Calendar.YEAR) * 12) + endCalendar.get(Calendar.MONTH);
            //Diferencia en meses entre las dos fechas
            int diffMonth = endMes - startMes;
            //Si la el día de la fecha de finalización es menor que el día de la fecha inicio
            //se resta un mes, puesto que no estaria cumpliendo otro periodo.
            //Para esto ocupo el metoddo ponerAnioMesActual
            Date aFecha = ponerAnioMesActual(fechaInicio,fechaFin).getTime();
            if(formatearDate(fechaFin, "dd/MM/yyyy").compareTo(
                    formatearDate(aFecha,"dd/MM/yyyy")) < 0){
                diffMonth = diffMonth - 1;
            }
            //Si la fecha de finalización es menor que la fecha de inicio, retorno que los meses
            // transcurridos entre las dos fechas es 0
            if(diffMonth < 0){
                diffMonth = 0;
            }
            return diffMonth;
        } catch (Exception e) {
            return 0;
        }
    }
    
    public static Calendar ponerAnioMesActual(Date fecha, Date fechaActual) {
        try {
            Calendar cActual = Calendar.getInstance();
            cActual.setTime(fechaActual);
            Calendar cfecha = Calendar.getInstance();
            cfecha.setTime(fecha);
            //la fecha nueva
            Calendar c1 = Calendar.getInstance();
            c1.set(cActual.get(Calendar.YEAR), cActual.get(Calendar.MONTH), cfecha.get(Calendar.DATE));
            return c1;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Date formatearDate(Date fecha, String pattern) {
        SimpleDateFormat formato = new SimpleDateFormat(pattern);
        Date fechaFormateada = null;
        try {
            fechaFormateada = formato.parse(formato.format(fecha));
            return fechaFormateada;
        } catch (Exception ex) {
            return fechaFormateada;
        }
    } 
    
    
    public static String formateaDecimal(double numero){
		if(numero > 0){
			Locale locale  = new Locale("en", "UK");
			String pattern = "###,###.##";

			DecimalFormat decimalFormat = (DecimalFormat)
			        NumberFormat.getNumberInstance(locale);
			decimalFormat.applyPattern(pattern);

			String format = decimalFormat.format(numero);
			return format;
		}
		return null;
	}
    
}
