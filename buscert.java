import java.io.*;
import java.net.*;
import java.util.*;

public class buscert{
    public static void main(String args[]){
        int port = 4000;
        ServerSocket busqsocket = null;
        Socket server = null; 
        ArrayList<String []> servidores = new ArrayList<String []>(); 


        PrintWriter out = null;
        BufferedReader in = null; 
        String inputLine, outputLine;

        if(args.length==2 && args[0].equals("-p")){
            port = Integer.parseInt(args[1]);
        }else if(args.length==1 && args[0].equals("-help")){
            System.out.println("Uso: java buscert [-p <puerto>]");
            System.out.println("\t-p <puerto>: numero del puerto de escucha de buscert, por defecto, 4000.");
        }

        /* Abrimos Puerto para conexion con clicert y los servcert */
        try{
            busqsocket = new ServerSocket(port);
        
        }catch (IOException e){
            System.err.println("No se puede escuchar en el puerto: " + port);
            System.exit(1);
        }

        Socket clientsocket = null;

        /* Almacenamos la entrada del usuario para sacar las estadisticas */
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        Certf p = new Certf();
        String aux = null;

        String data = null ;
        String resp = null ;
        while(true){
            /* Aceptamos conexion */
            try{
                clientsocket = busqsocket.accept();
            }catch (IOException e){
                System.err.println("Error aceptando conexion " + port);
                continue ;
            }

            try{
                out = new PrintWriter(clientsocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientsocket.getInputStream()));
            }catch (IOException e){
                System.out.println("Error obteniendo input/output stream");    
            }

            try{
                /*Leemos el input del socket */
                data = in.readLine();
                if(data.equals("CLIENTE")){
                    /*Leemos el query */
                    Certf.log("entre a cliente");
                    data = in.readLine();
                    System.out.println("clicert: " + data);
                    /*Enviamos el query al servidor */
                    for(int i=0; i < servidores.size() ; i++){
                        server = new Socket(servidores.get(i)[0] , servidores.get(i)[1]);
                        out = new PrintWriter(server.getOutputStream(), true);
                        in = new BufferedReader(new InputStreamReader(server.getInputStream()));

                        out.println("BUSCADOR");
                        out.println(data); 
                        Certf.log("envie data al servidor");
                    }
                }else if(data.equals("SERVER")){
                    String[] info = new String[2];
                    info[0] = clientsocket.getInetAddress();
                    info[1] = clientsocket.getPort();

                    servidores.add(info);

                    /*Leemos el certificado */
                    Certf.log("Leere data del servidor");
                    data = in.readLine();
                    System.out.println("server: " + data + "\n");
                    /*Enviamos certificado al cliente */
                    out.println("BUSCADOR");
                    out.println(data);
                }
                out.close();
                in.close();
                clientsocket.close();
                //busqsocket.close();
            } catch(IOException e){
                System.out.println("Error procesando datos buscert");
            }

        }
}
} 
