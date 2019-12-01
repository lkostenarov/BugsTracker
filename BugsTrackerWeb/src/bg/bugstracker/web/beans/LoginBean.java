package bg.bugstracker.web.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import bg.bugstracker.entity.UserModel;
import bg.bugstracker.service.UserServiceLocal;
import bg.bugstracker.web.utils.GeneralUtils;
import bg.bugstracker.web.utils.MessageUtils;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private HttpServletRequest request;
	
	public String username;
	private String password;
	
    @EJB
    UserServiceLocal userService;

	private static final String LIST_LOGIN_REDIRECT = "/faces/listAllIssues?faces-redirect=true";
	private static final String LOGIN_PAGE_REDIRECT = "/faces/login?faces-redirect=true";
	
	
	@PostConstruct
	public void init(){
		//TO DO
	}
	
	public String login() {
		
		String encryptedPass = GeneralUtils.encodeSha256Password(password);
		UserModel userModel = userService.loginUser(username, encryptedPass);
		
		if (null == userModel) {
			MessageUtils.addErrorMessage("login.error.invalid.credentials");
			return "";
		} else {
            request.getSession().setAttribute("LOGGED_USER", userModel);
            return LIST_LOGIN_REDIRECT;
		}
	}
	
	
	public String loginAsGuest() {
		
		String encryptedPass = GeneralUtils.encodeSha256Password(password);
		UserModel userModel = new UserModel(0 ,"Guest","","Guest User","",1);
		
        request.getSession().setAttribute("LOGGED_USER", userModel);
        return LIST_LOGIN_REDIRECT;
	}
	
	public String listAllIssues() {
		return LIST_LOGIN_REDIRECT;
	}
	
	public String logout(){
		request.getSession().invalidate();
		return LOGIN_PAGE_REDIRECT;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
