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
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import jaxb.Transformer;
import org.xml.sax.SAXException;

/**
 *
 * @author dkory
 */
public class DemoSAX {
    public static List<Client> getClientByModel(String filterName){
        List<Client> list = new LinkedList<>();
        MyPars myPars = new MyPars();
        
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(Transformer.file, myPars);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DemoSAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DemoSAX.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DemoSAX.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Client> finalList = new LinkedList<>();
        for (Client c : myPars.getClientList()) {
            if (c.getModel().equals(filterName)) {
                finalList.add(c);
            }
        }
        
        return finalList;
    }

}
