/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Client;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import jaxb.Transformer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author dkory
 */
public class DemoDOM {
    public static List<Client> getClientByModel(String filterName){
        List<Client> list = new LinkedList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(Transformer.fileDOM);
            Element root = document.getDocumentElement();
            NodeList childList = root.getChildNodes();
            for(int i=0 ; i<childList.getLength() ; i++){
                Node node = childList.item(i);
                if(node.getNodeType()==Node.ELEMENT_NODE && node.getNodeName().equals("client")){
                    Element child = (Element)node;
                    String filt = child.getAttribute("model");
                    if(filt.toLowerCase().contains(filterName.toLowerCase())){
                        Client client = new Client();
                        client.setIdclient(Integer.parseInt(child.getAttribute("idClient")));
                        client.setDevicetype(child.getAttribute("deviceType"));
                        client.setModel(child.getAttribute("model"));
                        client.setIp(child.getAttribute("ip"));
                        list.add(client);
                    }
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DemoDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) { 
            Logger.getLogger(DemoDOM.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DemoDOM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}
