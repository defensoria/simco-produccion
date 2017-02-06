package gob.dp.simco.comun.mb;

import gob.dp.simco.comun.MessagesUtil;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author c
 */
public abstract class AbstractManagedBean implements Serializable{
    
    protected MessagesUtil msg;
    
    protected String separador = "/"; //linux
    //protected String separador = "\\"; //windows
    
    protected static String FILE_SYSTEM="/home/glassfish/glassfish4/glassfish/domains/domain1/docroot/filesystem/";//linux
    //protected static String FILE_SYSTEM="/home/carlos/Desktop/proyect/glassfish4/glassfish/domains/domain1/docroot/filesystem/";//linux
    //protected static String FILE_SYSTEM="C:/server/glassfish-4.0/glassfish4/glassfish/domains/domain1/docroot/filesystem/";//windows
    
   //System.out.println(InetAddress.getLocalHost().getHostAddress());
     //   System.out.println(InetAddress.getLocalHost().getHostName());
    //
    public String hostAddress(){
        try {
                return "http://"+InetAddress.getLocalHost().getHostAddress().concat(":8080/filesystem/");
        } catch (UnknownHostException e) {
        }
        return null;
    }
    
    public String retornapath(String cadena){
        int cont=0;
        for (int i=0;i< cadena.length();i++){
            if(separador.equals(cadena.substring(i, i+1))){
                cont = i;
            }
        }
        return cadena.substring(0, cont);
    }
    
    
    public String retornaRutaPath(){
    FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest  request = (HttpServletRequest)ec.getRequest();
    String path = request.getPathTranslated();
    return retornapath(retornapath(path));
    }
    
    
    
    public FacesContext context = FacesContext.getCurrentInstance();
    
    public AbstractManagedBean() {
        msg = new MessagesUtil();
    }
    
}