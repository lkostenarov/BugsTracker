package bg.bugstracker.service;

import java.util.List;


import javax.ejb.Local;

import bg.bugstracker.entity.IssueStatusModel;


@Local
public interface StatusServiceLocal {
	List<IssueStatusModel> findAllStatus();
	IssueStatusModel findById(Long id);
}
