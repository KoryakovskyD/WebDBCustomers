/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackege.beans;

import entity.Address;
import java.util.List;
import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author dkory
 */
@Local
public interface SelectBeanLocal {
    List<Address> filter(HttpServletRequest request);
    List<String> getAllCities(List<Address> list);
}
