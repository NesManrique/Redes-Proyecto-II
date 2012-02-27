import nanoxml.*; 
import java.io.*;
import java.util.*;

public class xmlutils{

    private void log(String aMessage){
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
            System.out.println();
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
        }catch(expectedInput e){

        }

        v = certf.getChildren();

        //System.out.println(((XMLElement)v.get(1)).getName());

        fname = ((XMLElement)v.get(1)).getName();
        content = ((XMLElement)v.get(1)).getContent();

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("llego"));
            out.write(content);
            out.close();
        } catch (IOException e) {
            log("Problema Escribiendo el archivo: "+fname);
        }
    } 

    public static void main(String[] args){

            xmlutils p = new xmlutils();
            String a = p.cert2xml("server1",args[0]);
           
            p.xml2cert(a); 
    }

}
