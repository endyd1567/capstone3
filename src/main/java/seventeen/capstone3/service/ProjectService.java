package seventeen.capstone3.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import seventeen.capstone3.domain.Member;
import seventeen.capstone3.domain.Project;
import seventeen.capstone3.repository.MemberRepository;
import seventeen.capstone3.repository.ProjectRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    /**
     * 프로젝트 생성
     */
    @Transactional
    public Long create(Project project) {
        projectRepository.save(project);
        return project.getId();
    }

    //프로젝트 전체 조회
    public List<Project> findProjects() {
        return projectRepository.findAll();
    }


    public Project findOne(Long projectId) {
        return projectRepository.findOne(projectId);
    }




}
