/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackege.beans;

import entity.Client;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import jaxb.Transformer;
import mainpackege.beansRepo.RepositoryLocal;
import service.DemoDOM;
import service.DemoSAX;


/**
 *
 * @author denis
 */
@Stateless
public class MyFilter implements MyFilterLocal {
    @EJB
        RepositoryLocal repo;

     @Override
    public List<Client> filterSAX(String filterName) {
        List<Client> list = DemoSAX.getClientByModel(filterName);
        return list;
    }
    
    @Override
    public List<Client> filterDOM(String filterName) {
        List<Client> list = DemoDOM.getClientByModel(filterName);
        return list;
    }

    @Override
    public void createXML() {
        List<Client> list = repo.getAllClients();
        Transformer.createXML(list);
        Transformer.createDomXML(list);
    }

    
}
