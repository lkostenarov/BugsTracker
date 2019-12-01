package bg.bugstracker.service;

import java.util.List;


import javax.ejb.Local;

import bg.bugstracker.entity.ProjectModel;


@Local
public interface ProjectServiceLocal {
	List<ProjectModel> findAllProjects();
	ProjectModel findById(Long id);
	ProjectModel save(ProjectModel entity);
	ProjectModel update(ProjectModel entity);
}
