package entity;

import entity.Client;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-20T22:42:02")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> city;
    public static volatile SingularAttribute<Address, String> street;
    public static volatile SingularAttribute<Address, Integer> flat;
    public static volatile SingularAttribute<Address, Integer> num;
    public static volatile SingularAttribute<Address, String> extra;
    public static volatile SingularAttribute<Address, Integer> subnum;
    public static volatile SingularAttribute<Address, Integer> id;
    public static volatile SingularAttribute<Address, Client> idclient;

}