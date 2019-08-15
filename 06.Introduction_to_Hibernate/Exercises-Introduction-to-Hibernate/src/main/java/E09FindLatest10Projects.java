import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class E09FindLatest10Projects {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = emf.createEntityManager();

        StringBuilder sb = new StringBuilder();
        List<Project> projects = new ArrayList<>();

        try {
            em.getTransaction().begin();

            projects = em
                    .createQuery("FROM Project as p ORDER BY p.startDate", Project.class)
                    .setMaxResults(10)
                    .getResultList();

            projects.stream()
                    .sorted(Comparator.comparing(Project::getName));

            em.getTransaction().commit();

            projects.forEach(project -> sb
                    .append("Project name: ")
                    .append(project.getName())
                    .append(System.lineSeparator())
                    .append("\tProject Description: ")
                    .append(project.getDescription())
                    .append(System.lineSeparator())
                    .append("tProject Start Date: ")
                    .append(project.getStartDate())
                    .append("\tProject End Date: ")
                    .append(project.getStartDate())
            );

            System.out.println(sb.toString().trim());

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
