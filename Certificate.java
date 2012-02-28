import javax.crypto.*;
import java.io.*;
import java.util.*;
import java.lang.*;

public class Certificate{

    public void descifrado(String message){
        String s = null;

        try{
            String[] command = {"openssl x509 -noout -in " + message + " -issuer",
                "openssl x509 -noout -in " + message + " -subject",
                "openssl x509 -noout -in " + message + " -dates",
                "openssl x509 -noout -in " + message + " -issuer -subject -dates"
            } ;

            Process child = Runtime.getRuntime().exec(command[0]);
            BufferedReader stdInput = new BufferedReader(new 
                    InputStreamReader(child.getInputStream()));

            System.out.println("Salida del comando:");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
        } catch(IOException e){
            System.out.println("Error");
        }

    }


    public void abrirdir(File dir){ 
        File files[] = dir.listFiles();
        for(int i = 0; i< files.length; i++ ){
            if(files[i].isFile()){
                System.out.println("descifrare " + files[i].getPath());
                descifrado(files[i].getPath());
            } else if(files[i].isDirectory()){
                abrirdir(files[i]);
            }
        }
    }

    public static void main(String args[]){
        Certificate s = new Certificate();

        File dir = new File(args[0]);
        
        if(!dir.isDirectory()){
            System.out.println("Pathname no es un directorio");
            System.exit(1);
        }
    
        s.abrirdir(dir);
    }
}
