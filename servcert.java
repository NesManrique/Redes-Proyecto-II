import java.io.*;
import java.net.*;
//SAX classes
import org.xml.sax.*;
import org.xml.sax.helpers.*;
//JAXP 1.1
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.sax.*; 

public class servcert{

    public static void main(String args[]){

        if(args.length==8 && args[0].equals("-p") && args[2].equals("-d") && args[4].equals("-h") && args[6].equals("-b")){

        }else if(args.length==6 && args[0].equals("-p") && args[2].equals("-d") && args[4].equals("-h")){

        }else if(args.length==6 && args[0].equals("-d") && args[2].equals("-h") && args[4].equals("-b")){

        }else if(args.length==4 && args[0].equals("-d") && args[2].equals("-h")){

        }else if(args.length==1 && args[0].equals("-help")){
            System.out.println("Uso: java servcert [-p <puerto>] -d <directorio que almacena los certificados> -h <nombre de dominio de buscert>");
            System.out.println("\t\t[-b <puerto de escucha de buscCert>]\n");
            System.out.println("\t<puerto>: puerto de escucha de peticiones de sercert. 5000 por default.");
            System.out.println("\t<directorio que almacena los certificados>: contiene el camino absoluto o relativo \n\t\t del directorio donde estarán los certificados del repositorio.");
            System.out.println("\t<nombre de dominio de buscert>: ip o nombre de dominio de buscert");
            System.out.println("\t<puerto de escucha de buscert>: puerto de escucha del buscador. 4000 por default.");
        }else{
            System.out.println("Error en las opciones. Para ver una ayuda ejecute java servcert -help");
            System.exit(1);
        }

    }

}
