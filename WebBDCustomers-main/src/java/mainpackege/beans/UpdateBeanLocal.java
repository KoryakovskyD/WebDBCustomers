/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackege.beans;

import javax.ejb.Local;
import entity.Client;

/**
 *
 * @author dkory
 */
@Local
public interface UpdateBeanLocal {
    boolean deleteClient(int id);
    Client addClient(int idClient, String type, String model, String ip, String city,
                String street, int num, int subnum, int flat, String extra);
    void updateClient(int idClient, String type, String model, String ip,int idAddress, String city,
                String street, int num, int subnum, int flat, String extra);
    
}

