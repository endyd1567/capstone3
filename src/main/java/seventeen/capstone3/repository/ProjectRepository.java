package seventeen.capstone3.repository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import seventeen.capstone3.domain.Member;
import seventeen.capstone3.domain.Project;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Getter @Setter
@RequiredArgsConstructor
public class ProjectRepository {

    private final EntityManager em;

    public void save(Project project) {
        em.persist(project);
    }

    public Project findOne(Long id) {
        return em.find(Project.class, id);
    }

    public List<Project> findAll() {
        return em.createQuery("select m from Project m", Project.class)
                .getResultList();
    }

    public List<Project> findByName(String name) {
        return em.createQuery("select m from Project m where m.name = :name", Project.class)
                .setParameter("name", name)
                .getResultList();
    }
}

