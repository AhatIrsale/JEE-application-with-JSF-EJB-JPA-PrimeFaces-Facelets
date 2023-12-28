package entity;

import entity.Employe;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-12-28T23:00:51")
@StaticMetamodel(Machine.class)
public class Machine_ { 

    public static volatile SingularAttribute<Machine, String> reference;
    public static volatile SingularAttribute<Machine, BigDecimal> prix;
    public static volatile SingularAttribute<Machine, Employe> employe;
    public static volatile SingularAttribute<Machine, Date> dateAchat;
    public static volatile SingularAttribute<Machine, Integer> id;
    public static volatile SingularAttribute<Machine, String> marque;

}