import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class E07AddressesWithEmployeeCount {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        StringBuilder sb = new StringBuilder();
        try {
            em.getTransaction().begin();
            em.createQuery("FROM Address AS a ORDER BY a.employees.size DESC, a.town.id", Address.class)
                    .getResultList()
                    .forEach(address -> sb.append(String.format("%s %s %d%n"
                            , address.getText()
                            , address.getTown().getName()
                            , address.getEmployees().size())));
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }

        System.out.println(sb);
    }
}
