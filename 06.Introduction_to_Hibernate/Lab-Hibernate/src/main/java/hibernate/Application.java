package hibernate;

import hibernate.entities.Student;
import org.hibernate.Session;
import hibernate.utils.HibernateUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        Session session = HibernateUtils.getSession();

        session.beginTransaction();

        //your code here
        Student student1 = new Student();

        //hibernate save data
        session.save(student1);
        //hibernate get data by Get
        Student student2 = session.get(Student.class, 1);

        student1.setName("Pesho");
        student1.setRegistrationDate(new Date());

        //hibernate retrieve data by Query
        List<Student> studentList = session.createQuery(
                "FROM Student "
        ).list();

        for (Student student : studentList) {
            System.out.println(student);
        }

        //hibernate retrieve data by Criteria
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery();
        Root<Student> r = criteria.from(Student.class);
        criteria.select(r).where(builder.like(r.get("name"), "P%"));

        List<Student> studentList1 = session.createQuery(criteria).getResultList();
        for (Student student : studentList1) {
            System.out.println(student.getName());
        }


        session.getTransaction().commit();
        session.close();
    }
}
