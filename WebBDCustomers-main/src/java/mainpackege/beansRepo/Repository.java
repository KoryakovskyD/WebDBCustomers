/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackege.beansRepo;

import entity.Address;
import entity.Client;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author Дмитрий
 */
@Stateless
public class Repository implements RepositoryLocal {
    @PersistenceContext
    EntityManager em;
    

    @Override
    public List<Address> getAllAddress() {
        return em.createNamedQuery("Address.findAll").getResultList();
    }

    @Override
    public List<Client> getAllClients() {
        return em.createNamedQuery("Client.findAll").getResultList();
    }

    @Override
    public Address getAddress(Address address) {
        for (Address a : getAllAddress()) {
            if (a.getId() == address.getId()) return a;
        }
        return null;
    }   

    @Override
    public void deleteClient(Client client) {
        em.refresh(client);
        em.remove(client);
    }

    @Override
    public void addClient(int idClient, String type, String model, String ip, String city, String street, int num, int subnum, int flat, String extra) {
        Address address = new Address();
        Client client = new Client();
        int id=0;
       
        for(Address c : getAllAddress()) {
            if (c.getId() > id) {
                id = c.getId();
            }
        }
        

        client.setDevicetype(type);
        client.setModel(model);
        client.setIp(ip);
        
        address.setId(id+1);
        address.setIdclient(client);
        address.setCity(city);
        address.setStreet(street);
        address.setNum(num);
        address.setSubnum(subnum);
        address.setFlat(flat);
        address.setExtra(extra);
        
        
        
        
        em.persist(client);
        em.persist(address);
        
       
    }

    @Override
    public void updateClient(int idClient, String type, String model, String ip, int idAddress, String city,
                String street, int num, int subnum, int flat, String extra) {
        for (Client c : getAllClients()) {
            
            if (c.getIdclient() == idClient) {
                c.setIdclient(idClient);
                c.setDevicetype(type);
                c.setModel(model);
                c.setIp(ip);
                
                Collection<Address> tmpListAddress = c.getAddressCollection();
                for (Address a : tmpListAddress) {
                    if (a.getId().equals(idAddress)) {
                        a.setCity(city);
                        a.setStreet(street);
                        a.setNum(num);
                        a.setSubnum(subnum);
                        a.setFlat(flat);
                        a.setExtra(extra);
                        em.persist(a);
                    }
                }
                em.persist(c);
            }
        }
    }
}
