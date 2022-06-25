/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackege.beansRepo;

import entity.Address;
import entity.Client;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Дмитрий
 */
@Local
public interface RepositoryLocal {
    List<Address> getAllAddress();
    List<Client> getAllClients();
    Address getAddress(Address address);
    void deleteClient(Client client);
    void addClient(int idClient, String type, String model, String ip, String city, String street, int num, int subnum, int flat, String extra);
    void updateClient(int idClient, String type, String model, String ip,int idAddress, String city,
                String street, int num, int subnum, int flat, String extra);
}
