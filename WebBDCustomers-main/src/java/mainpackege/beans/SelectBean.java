/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackege.beans;

import entity.Address;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateful;
import javax.servlet.http.HttpServletRequest;
import javax.ejb.EJB;
import mainpackege.beansRepo.RepositoryLocal;

/**
 *
 * @author dkory
 */
@Stateful
public class SelectBean implements SelectBeanLocal {
    @EJB
        RepositoryLocal repo;

    @Override
    public List<Address> filter(HttpServletRequest request) {
        
        List<Address> addressList = repo.getAllAddress();
        String curCity = Objects.toString(request.getParameter("curCity"), "").trim();
        String addr = Objects.toString(request.getParameter("addr"), "");
        
        String curStreet="";
        int curNum=0;
        
        if (!addr.isEmpty()) {
            String[] words = addr.split(" ");
            curStreet = words[0];
            curNum = Integer.parseInt(words[1]);
        }
        List<Address> tmpList = new LinkedList<>();
        for(Address a : addressList) {
            if(!a.getCity().contains(curCity)) continue;
            if(!a.getStreet().contains(curStreet)) continue;
            if(a.getNum()!=curNum && curNum!=0) continue;
            tmpList.add(a);
        }
        if(!curCity.isEmpty() || !addr.isEmpty()) addressList = new ArrayList<>(tmpList);
        return addressList;
    }

    @Override
    public List<String> getAllCities(List<Address> list) {
        List<String> resList = new ArrayList();
        
        for (Address a : list) {
            if (!resList.contains(a.getCity()))
                resList.add(a.getCity());
        }
        return resList;
    }
}
