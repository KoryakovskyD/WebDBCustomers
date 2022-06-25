/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaxb;

import entity.Address;
import entity.Client;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;



/**
 *
 * @author dkory
 */
public class Transformer {
    public static final File file = new File("D:/Clients.xml");
    public static final File fileDOM = new File("D:/PersonsDom.xml");
    static{
        try {
            if(!file.exists()) file.createNewFile();
            if(!fileDOM.exists()) fileDOM.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Transformer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createXML(List<Client> clientsList){
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<clients>\n");
        for(Client c : clientsList){
            sb.append("<client ").append("idClient=\"").append(c.getIdclient()).append("\" ");
            sb.append("deviceType=\"").append(c.getDevicetype()).append("\" ");
            sb.append("model=\"").append(c.getModel()).append("\" ");
            sb.append("ip=\"").append(c.getIp()).append("\"></client>\n");
        }
        try(FileWriter writer = new FileWriter(file) ){
            writer.write(sb.toString());
        }catch(IOException e){}
    }
    
    
    public static void createDomXML(List<Client> clientsList){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            Element root = document.createElement("clients");
            document.appendChild(root);
            for(Client c : clientsList){
                Element child = document.createElement("client");
                child.setAttribute("idClient", String.valueOf(c.getIdclient()));
                child.setAttribute("deviceType", c.getDevicetype());
                child.setAttribute("model", c.getModel());
                child.setAttribute("ip", c.getIp());
                
                root.appendChild(child);
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            javax.xml.transform.Transformer transformer = tf.newTransformer();
            DOMSource doms = new DOMSource(document);
            StreamResult result = new StreamResult(fileDOM);
            transformer.transform(doms, result);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Transformer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Transformer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
