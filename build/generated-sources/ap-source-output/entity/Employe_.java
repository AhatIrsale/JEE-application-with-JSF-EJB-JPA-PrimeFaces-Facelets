package entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-12-28T23:00:51")
@StaticMetamodel(Employe.class)
public class Employe_ { 

    public static volatile SingularAttribute<Employe, Integer> id;
    public static volatile SingularAttribute<Employe, BigDecimal> salaire;
    public static volatile SingularAttribute<Employe, String> nom;
    public static volatile SingularAttribute<Employe, String> prenom;

}