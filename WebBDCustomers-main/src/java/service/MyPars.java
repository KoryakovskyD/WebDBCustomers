/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Client;
import java.util.LinkedList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author denis
 */
public class MyPars extends DefaultHandler{
    public static Client client;
    String qName = "";
    List<Client> list = new LinkedList<>();
    
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("characters: " + qName); 
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("endElement:" + qName);
        client = null;
        qName = "";
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        this.qName = qName;
        System.out.println("startElement: " + qName);
        
        if(qName.equals("client")){
           
            String idClient = attributes.getValue("idClient");
            int id = Integer.parseInt(idClient);
            String deviceType = attributes.getValue("deviceType");
            String model = attributes.getValue("model");
            String ip = attributes.getValue("ip");
            client = new Client();
                        client.setIdclient(id);
                        client.setDevicetype(deviceType);
                        client.setModel(model);
                        client.setIp(ip);
                        list.add(client);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End document");
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start document");
    }
    
    public List<Client> getClientList() {
        return list;
    }
    
}
