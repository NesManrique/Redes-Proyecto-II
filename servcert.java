import java.io.*;
import java.net.*;

public class servcert{

    ArrayList<Certf> Cert;
    String Dir;
    //int listenPort;
    //int buscPort;

    public servcert(String dir){
        Dir=dir;
        Cert=abrirdir(dir);
    }

    public static void main(String args[]){
        int servport = 5000;
        String dir = null;
        String bushost = null;
        int busport = 4000;

        Socket busqsocket = null;
        PrintWriter out = null;
        BufferedReader in = null; 
        String inputLine, outputLine;
        Certf p = new Certf();


        if(args.length==8 && args[0].equals("-p") && args[2].equals("-d") && args[4].equals("-h") && args[6].equals("-b")){
            servport = Integer.parseInt(args[1]);
            dir = args[3];
            bushost = args[5];
            busport = Integer.parseInt(args[7]); 
        }else if(args.length==6 && args[0].equals("-p") && args[2].equals("-d") && args[4].equals("-h")){
            servport = Integer.parseInt(args[1]);
            dir = args[3];
            bushost = args[5];
        }else if(args.length==6 && args[0].equals("-d") && args[2].equals("-h") && args[4].equals("-b")){
            dir = args[1];
            bushost = args[3];
            busport = Integer.parseInt(args[5]); 
        }else if(args.length==4 && args[0].equals("-d") && args[2].equals("-h")){
            dir = args[1];
            bushost = args[3];
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

        
        try{
            busqsocket = new Socket(bushost, busport);
            out = new PrintWriter(busqsocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(busqsocket.getInputStream()));
        }catch(UnknownHostException e){
            System.err.println("Erro al conctarse al host: "+bushost);
            System.exit(1);
        }catch(IOException e){
            System.err.println("Couldn't get I/O for the connection to: "+bushost);
            System.exit(1);
        }

        try{

            while((inputLine = in.readLine()) != null){
                if(inputLine.equals("BUSCADOR")){
                    ServerSocket socket= null;
                    Socket aux = null; 
                    socket = new ServerSocket(servport);
                    aux = socket.accept();
                }


                System.out.println("buscert: " + inputLine);
                //outputLine = stdIn.readLine();
                out.println("SERVER");
                outputLine = p.cert2xml("servidor1","cert/mycert.pem");
                if(outputLine !=null){
                    System.out.println("server: " + outputLine + "\n");
                    out.println(outputLine);  
                }

            }

            out.close();
            in.close();
            busqsocket.close();
        } catch (IOException e){
            System.out.println("Error streams");
        }

    }
}
