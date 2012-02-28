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

public class clicert{

    public static void main(String args[]) throws IOException{

        String dir = null;
        String bushost = null;
        int puertobus = 4000;
        
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        if(args.length==6 && args[0].equals("-d") && args[2].equals("-h") && args[4].equals("-p")){
            dir = args[1];
            bushost = args[3];
            puertobus = Integer.parseInt(args[5]);
        }else if(args.length==4 && args[0].equals("-d") && args[2].equals("-h")){
            dir = args[1];
            bushost = args[3];
        }else if(args.length==1 && args[0].equals("-help")){
            System.out.println("Uso: java clicert -d <directorio de certificados descargados> -h <nombre de dominio, o direccion de ip de buscert>");
            System.out.println("\t\t[-p <puerto de buscCert>]\n");
            System.out.println("\t<directorio de certificados descargados>: contiene el camino absoluto o relativo \n\t\t del directorio donde estarán los certificados digitales descargados por el cliente.");
            System.out.println("\t<nombre de dominio, o direccion de ip de buscert>: maquina donde se encuentra corriendo el buscador");
            System.out.println("\t<puerto de busCert>: puerto de escucha del buscador busCert. 4000 por default.");
            System.exit(0);
        }else{
            System.out.println("Error en las opciones. Para ver una ayuda ejecute java clicert -help");
            System.exit(1);
        }

        System.out.println("dir dond guardar los certificados "+dir
                            +"\nip o nombre del buscador "+bushost
                            +"\npuerto de escucha del buscador "+puertobus+"\n");

        /*try{
            echoSocket = new Socket(bushost, puertobus);
            out =  new PrintWriter(echoSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        }catch(UnknownHostException e){
            System.err.println("Erro al conctarse al host: "+bushost);
            System.exit(1);
        }catch(IOException e){
            System.err.println("Couldn't get I/O for the connection to: "+bushost);
            System.exit(1);
        }*/

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String userInput=null;
        int ln=0;

        while((userInput = stdIn.readLine())!=null){
            if(userInput.equals("")){
                if(ln==0){
                    ln=ln+1;
                    System.out.println("Si inserta otra linea vacía el programa clicert terminara.");
                }else if(ln==1){
                    System.out.println("Clicer terminara ahora.");
                    System.exit(0);
                }
            }else{
                ln=0;

                //Transformar la entrada en XML
                String[] a = userInput.split(" ");
                for(int i=0; i<a.length; i++){
                    System.out.println(a[i]);
                }
                //Enviarla al buscador
                //Guardar el Certificado
                //Imprimir el Certificado
                //System.out.println("echo: "+ userInput);
            }
        }

        //out.close();
        //in.close();
        stdIn.close();
        //echoSocket.close();
    }
}
