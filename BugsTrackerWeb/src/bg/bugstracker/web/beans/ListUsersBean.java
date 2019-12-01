package bg.bugstracker.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import bg.bugstracker.entity.UserModel;
import bg.bugstracker.service.UserServiceLocal;


@ManagedBean(name = "listUsersBean")
@ViewScoped
public class ListUsersBean {
	
	@EJB
	UserServiceLocal userService;

	@PostConstruct
	public void init() {
	}

	public String editAction() {
		return "/faces/editUser";
	}

	public String createAction() {
		return "/faces/createUser";
	}

	public List<UserModel> getAllUsers() {
		return userService.findAllUsers();
	}

}
