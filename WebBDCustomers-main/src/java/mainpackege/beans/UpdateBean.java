/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackege.beans;

import java.util.List;
import javax.ejb.Stateful;
import entity.Client;
import javax.ejb.EJB;
import mainpackege.beansRepo.RepositoryLocal;

/**
 *
 * @author dkory
 */
@Stateful
public class UpdateBean implements UpdateBeanLocal {
    @EJB
        RepositoryLocal repo;
   
    List<Client> clientsList;

    @Override
    public boolean deleteClient(int id) {
        
        clientsList = repo.getAllClients();
        System.out.println("id=" + id);
        for(Client c : clientsList) {
            if (c.getIdclient()==id) {
                System.out.println("id=" + id + "; idClien=" + c.getIdclient());
                    repo.deleteClient(c);
                    clientsList.remove(c);
                    return true;
            }
        }

        return true;
    } 

    @Override
    public Client addClient(int idClient, String type, String model, String ip, String city, String street, int num, int subnum, int flat, String extra) {
       repo.addClient(idClient, type, model, ip, city, street, num, subnum, flat, extra);
       return null;
    }
    
    @Override
    public void updateClient(int idClient, String type, String model, String ip, int idAddress, String city, String street, int num, int subnum, int flat, String extra) {
        repo.updateClient(idClient, type, model, ip,idAddress, city, street, num, subnum, flat, extra);
    }
}
