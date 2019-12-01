package bg.bugstracker.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.bugstracker.entity.IssueModel;
import bg.bugstracker.entity.IssueStatusModel;
import bg.bugstracker.service.IssueServiceLocal;
import bg.bugstracker.service.StatusServiceLocal;

@ManagedBean (name="selectIssueStatusBean")
@ViewScoped
public class SelectIssueStatusBean {

	private IssueStatusModel selectedStatus;
	private long StatusId;
	
	@Inject
	HttpServletRequest request;
		
	@EJB
    StatusServiceLocal StatusService;
	
	@EJB
    IssueServiceLocal issueService;
	
	@PostConstruct
	public void init() {
		String id = request.getParameter("id");

		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){
			selectedStatus = issueService.findById(Long.valueOf(id)).getIssueStatus() ;
		}
	}
	 
	public List<IssueStatusModel> getAllStatus() {
			List<IssueStatusModel> temp = StatusService.findAllStatus();
			temp.remove(selectedStatus);
			return temp;
	    }
	

	public IssueStatusModel getSelectedStatus() {
		return selectedStatus;
	}

	public void setSelectedStatus(IssueStatusModel selectedStatus) {
		this.selectedStatus = selectedStatus;
	}

	public long getStatusId() {
		return StatusId;
	}

	public void setStatusId(long statusId) {
		StatusId = statusId;
	}
	 
	
}
