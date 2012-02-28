import nanoxml.*; 
import java.io.*;
import java.util.*;

public class Certf{

    String Iss;
    String Sub;
    String ValA;
    String ValB;
    String Hash;
    String Fing;

    public Certf(){};

    public Certf(String is, String subj, String vala, String valb, String hash,
                    String fing){
        Iss = is;
        Sub = subj;
        ValA = vala;
        ValB = valb;
        Hash = hash;
        Fing = fing;;
        
    }

    public void printcert(){
        log(this.Iss);
        log(this.Sub);
        log(this.ValA);
        log(this.ValB);
        log(this.Hash);
        log(this.Fing);
    }

    public void printList(ArrayList<Certf> l){
        ListIterator<Certf> it = l.listIterator();

        while(it.hasNext()){
            it.next().printcert();
        }
    }

    public Certf descifrado(String message){
        String[] s = new String[6];

        try{
            String[] command = {"openssl x509 -noout -in " + message + " -issuer",
                "openssl x509 -noout -in " + message + " -subject",
                "openssl x509 -noout -in " + message + " -hash",
                "openssl x509 -noout -in " + message + " -fingerprint",
                "openssl x509 -noout -in " + message + " -dates",
            } ;

            for(int i=0; i<command.length; i++){
                Process child = Runtime.getRuntime().exec(command[i]);

                BufferedReader stdInput = new BufferedReader(new 
                        InputStreamReader(child.getInputStream()));

                if(i==4){
                    if((s[i]=stdInput.readLine())==null){
                        return null;
                    }else{
                        s[i]=s[i].replaceFirst("[\\w ]+=\\s*","");
                    }

                    if((s[i+1]=stdInput.readLine())==null){
                        return null;
                    }else{
                        s[i+1]=s[i+1].replaceFirst("[\\w ]+=\\s*","");
                    }
                }else{
                    if((s[i]=stdInput.readLine())==null){
                        return null;
                    }else{
                        s[i]=s[i].replaceFirst("[\\w ]+=\\s*","");
                    }
                }
                
                stdInput.close();
            }

            Certf c = new Certf(s[0],s[1], s[4], s[5], s[2], s[3]);

            return c;

        } catch(IOException e){
            System.out.println("Error");
            return null;
        }

    }


    public void abrirdir(File dir,ArrayList<Certf> L) throws NullPointerException{
        Certf cer;
        File files[] = dir.listFiles();
        if(files == null){
            throw new NullPointerException();
        }
        for(int i = 0; i< files.length; i++ ){
            if(files[i].isFile()){
                if((cer=descifrado(files[i].getPath()))!=null){
                    L.add(cer);
                }else{
                    continue;
                }
            } else if(files[i].isDirectory()){
                abrirdir(files[i],L);
            }
        }
    }

    public static void log(Object aMessage){
        System.out.println(aMessage);
    }

    public String cert2xml(String serv, String filecert){
        String all = "";
        try{
            BufferedReader input = new BufferedReader(new FileReader(filecert));
            String line = null;
            while((line = input.readLine())!=null){
                all = all + line + "\n"; 
            }
            input.close();
        }catch(IOException ex){
            log("Error abriendo el archivo: "+filecert);
        }
        XMLElement certf = new XMLElement();
        XMLElement certname = new XMLElement();
        XMLElement servname = new XMLElement();
        certf.setName("Certificate");
        certname.setName(filecert);
        servname.setName(serv);
        certname.setContent(all);
        certf.addChild(servname);
        certf.addChild(certname);
        all = certf.toString();
        if(all.contains("&#xa;")){
            all = all.replace("&#xa;","\n");
        }
        return all;
    }

    public void xml2cert(String certxml){
        String fname="";
        String content="";
        Vector v = new Vector();

        XMLElement certf = new XMLElement();
        try{
            certf.parseString(certxml);
        }catch(XMLParseException e){
            log("Error leyendo el archivo xml");
        }

        v = certf.getChildren();

        fname = ((XMLElement)v.get(1)).getName();
        content = ((XMLElement)v.get(1)).getContent();

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fname));
            out.write(content);
            out.close();
        } catch (IOException e) {
            log("Problema Escribiendo el archivo: "+fname);
        }
    } 

    /*public String query2xml(String query){
        String[] subs = query.split(" ");
        for(int i=0; i<subs.length; i++){
            log(subs[i]);
        }

    }*/

    public static void main(String[] args){

            /*xmlutils p = new xmlutils();
            String a = p.cert2xml("server1",args[0]);
            //p.log(a);
            p.xml2cert(a); */

            File dir = new File(args[0]);
            Certf c = new Certf();
            ArrayList<Certf> ac = new ArrayList<Certf>();
            try{
                c.abrirdir(dir,ac);
            }catch(NullPointerException e){
                log("Error leyendo directorios");
            }
            
            c.printList(ac);
    }

}
