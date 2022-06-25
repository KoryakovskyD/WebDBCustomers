/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackege.beans;

import javax.ejb.Local;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dkory
 */
@Local
public interface ChecksLocal {
    boolean validIP(String ip);
    boolean validParams(String type, String model, String city, String street, String num, String extra, String ip);
    boolean validParams(HttpServletRequest request);
}
