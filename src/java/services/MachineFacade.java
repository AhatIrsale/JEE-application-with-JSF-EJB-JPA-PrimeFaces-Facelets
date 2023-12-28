/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entity.Employe;
import entity.Machine;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author asus
 */
@Stateless
public class MachineFacade extends AbstractFacade<Machine> {
    @PersistenceContext(unitName = "WebApplicationtestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    public List<Machine> findByEmploye(Employe employe) {
    return em.createQuery("SELECT m FROM Machine m WHERE m.employe = :employe")
             .setParameter("employe", employe)
             .getResultList();
        }
    
    public List<Employe> findAllEmployes() {
    Query query = em.createQuery("SELECT e FROM Employe e");
    return query.getResultList();
}
    
    public List<Machine> findByEmployeAndYear(Employe employe, int year) {
    return em.createQuery("SELECT m FROM Machine m WHERE m.employe = :employe AND YEAR(m.dateAchat) = :year")
            .setParameter("employe", employe)
            .setParameter("year", year)
            .getResultList();
    }
    public List<Object[]> findMachinesByYear() {
    try {
       return em.createQuery("SELECT FUNCTION('YEAR', m.dateAchat), COUNT(m) FROM Machine m GROUP BY FUNCTION('YEAR', m.dateAchat)")
         .getResultList();

       // return em.createQuery("SELECT YEAR(m.date_achat), COUNT(m.id) FROM Machine m GROUP BY YEAR(m.date_achat)")
              //  .getResultList();
    } catch (Exception e) {
        // Gérer l'exception (par exemple, loguer l'erreur)
        e.printStackTrace();
        throw new EJBException("Erreur lors de la récupération des machines par année."+ e, e);
    }
}



    public MachineFacade() {
        super(Machine.class);
    }
    
}
