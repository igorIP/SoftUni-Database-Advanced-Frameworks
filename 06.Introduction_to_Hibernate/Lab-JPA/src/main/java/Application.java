import entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class Application {
    public static void main(String[] args) {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("school");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        //persist obj
        Student student1 = new Student("Pesho", new Date());
        Student student2 = new Student("Gosho", new Date());

        em.persist(student1);
        em.persist(student2);
        //find obj
        Student foundStudent = em.find(Student.class, 1);

        //JPA delete objects
        //em.remove(student2);

        //detach
        em.detach(foundStudent);

        //update the detached version
        student2.setName("John");

        //JPA merge objects
        //Merge the state of detached entity into a managed copy of the detached entity.
        Student attachedStudent = student2.storeUpdatedStudent(em, student2);


        em.getTransaction().commit();
        em.close();
        //factory.close();
    }
}
