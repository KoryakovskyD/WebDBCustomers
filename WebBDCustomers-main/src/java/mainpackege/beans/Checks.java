/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackege.beans;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dkory
 */
@Stateless
public class Checks implements ChecksLocal {

    @Override
    public boolean validIP(String ip) {
        if (ip == null || ip.isEmpty()) return false;
    ip = ip.trim();
    if ((ip.length() < 6) & (ip.length() > 15)) return false;

    try {
        Pattern pattern = Pattern.compile("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?).){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    } catch (PatternSyntaxException ex) {
        return false;
    }
    }

    @Override
    public boolean validParams(String type, String model, String city, String street, String num, String extra, String ip) {
        if (city.isEmpty() || street.isEmpty() || num.isEmpty() || city.length()>100 || street.length()>100 || extra.length()>200) {
            return false;
        }
        
        if (type.isEmpty() || model.isEmpty() || type.length()>100 || model.length()>100) {
            return false;
        }
        
        return validIP(ip);
    }

    @Override
    public boolean validParams(HttpServletRequest request) {
        
        String type = Objects.toString(request.getParameter("type"),"");
        String model = Objects.toString(request.getParameter("model"),"");
        String ip = Objects.toString(request.getParameter("ip"),"");
        String city = Objects.toString(request.getParameter("city"),"");
        String street = Objects.toString(request.getParameter("street"),"");
        String num = Objects.toString(request.getParameter("num"),"");
        String extra = Objects.toString(request.getParameter("extra"),"");
        System.out.println("-------------------------------");
        if (city.isEmpty() || street.isEmpty() || num.isEmpty() || city.length()>100 || street.length()>100 || extra.length()>200) {
            request.setAttribute("msg", "Uncorrect parameters");
            return false;
        }
        
        if (type.isEmpty() || model.isEmpty() || type.length()>100 || model.length()>100) {
            request.setAttribute("msg", "Uncorrect parameters");
            return false;
        }
        
        return validIP(ip);
    }
}
