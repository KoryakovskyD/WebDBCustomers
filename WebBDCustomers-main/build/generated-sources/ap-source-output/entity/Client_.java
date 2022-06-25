package entity;

import entity.Address;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-06-20T22:42:02")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile CollectionAttribute<Client, Address> addressCollection;
    public static volatile SingularAttribute<Client, String> ip;
    public static volatile SingularAttribute<Client, String> model;
    public static volatile SingularAttribute<Client, Integer> idclient;
    public static volatile SingularAttribute<Client, String> devicetype;

}