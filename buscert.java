import java.io.*;
import java.net.*;

public class buscert{

    public static void main(String args[]){

        if(args.length==2 && args[0].equals("-p")){
            
        }else if(args.length==1 && args[0].equals("-help")){
            System.out.println("Uso: java buscert [-p <puerto>]");
            System.out.println("\t-p <puerto>: numero del puerto de escucha de buscert, por defecto, 4000.");
        }else if(args.length==0){
            
        }else{
            System.out.println("Error en las opciones. Para ver una ayuda ejecute java buscert -help");
            System.exit(1);
        }
    }
} 
