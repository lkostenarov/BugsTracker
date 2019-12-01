package bg.bugstracker.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.bugstracker.entity.IssueStatusModel;
import bg.bugstracker.entity.UserModel;
import bg.bugstracker.service.IssueServiceLocal;
import bg.bugstracker.service.StatusServiceLocal;
import bg.bugstracker.service.UserServiceLocal;

@ManagedBean (name="selectUsersBean")
@ViewScoped
public class SelectUsersBean {
	
private UserModel selectedUser;
private long userId;
	
	@Inject
	HttpServletRequest request;
		
	@EJB
    UserServiceLocal UserService;
	
	@EJB
    IssueServiceLocal IssueService;
	
	@PostConstruct
	public void init() {
		String id = request.getParameter("id");

		if(StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)){
			selectedUser = IssueService.findById(Long.valueOf(id)).getAssignto();
		}
	}
	 
	public List<UserModel> getAllUsers() {
			List<UserModel> temp = UserService.findAllUsers();
			if (selectedUser!=null){
				temp.remove(selectedUser);
			}
			return temp;
	    }

	public UserModel getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(UserModel selectedUser) {
		this.selectedUser = selectedUser;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
