import java.io.*;
import java.util.*;
import java.net.*;

public class clicert{

    public static void main(String args[]){

        if(args.length==6 && args[0].equals("-d") && args[2].equals("-h") && args[4].equals("-p")){

        }else if(args.length==4 && args[0].equals("-d") && args[2].equals("-h")){

        }else if(args.length==1 && args[0].equals("-help")){
            System.out.println("Uso: java clicert -d <directorio de certificados descargados> -h <nombre de dominio, o direccion de ip de buscert>");
            System.out.println("\t\t[-p <puerto de buscCert>]\n");
            System.out.println("\t<directorio de certificados descargados>: contiene el camino absoluto o relativo \n\t\t del directorio donde estarán los certificados digitales descargados por el cliente.");
            System.out.println("\t<nombre de dominio, o direccion de ip de buscert>: maquina donde se encuentra corriendo el buscador");
            System.out.println("\t<puerto de busCert>: puerto de escucha del buscador busCert. 4000 por default.");
        }else{
            System.out.println("Error en las opciones. Para ver una ayuda ejecute java clicert -help");
            System.exit(1);
        }
        
    }
}
