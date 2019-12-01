package bg.bugstracker.web.beans;

import java.util.Iterator;

import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.bugstracker.service.UserService;
import bg.bugstracker.entity.UserModel;
import bg.bugstracker.service.UserServiceLocal;
import bg.bugstracker.web.utils.GeneralUtils;
import bg.bugstracker.web.utils.MessageUtils;

@ManagedBean(name = "createUserBean")
@ViewScoped
public class CreateUserBean {

	@Inject
	HttpServletRequest request;

	@EJB
	UserServiceLocal userService;

	private UserModel user;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
			+ "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*" + "(\\.[A-Za-z]{2,})$";
	private Pattern pattern;

	@PostConstruct
	public void init() {
		user = new UserModel();
	}

	public String createAction() {

		if (!validate()) {
			return null;
		}

		String cryptedPassword = GeneralUtils.encodeSha256Password(user.getPassword());
		user.setPassword(cryptedPassword);

		userService.save(user);

		return "/faces/managePanel?faces-redirect=true";
	}


	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	protected boolean validate() {
		boolean hasErrors = false;
		if (StringUtils.isBlank(user.getUsername())) {
			MessageUtils.addErrorMessage("error.required.username");
			hasErrors = true;
		}

		if (StringUtils.isBlank(user.getPassword())) {
			MessageUtils.addErrorMessage("error.required.password");
			hasErrors = true;
		}

		if (StringUtils.isBlank(user.getFullname())) {
			MessageUtils.addErrorMessage("error.required.firstname");
			hasErrors = true;
		}


		if (StringUtils.isBlank(user.getEmail())) {
			MessageUtils.addErrorMessage("error.required.email");
			hasErrors = true;
		}

		pattern = Pattern.compile(EMAIL_PATTERN);
		if (!pattern.matcher(user.getEmail()).matches()) {
			MessageUtils.addErrorMessage("error.invalid.email");
			hasErrors = true;
		}
		
		if ( userService.checkUserExists(user.getUsername(), null) == null){
		
		} else {
			MessageUtils.addErrorMessage("error.user.exists");
			hasErrors = true;
		}

		if (hasErrors) {
			return false;
		}

		return true;
	}

	public boolean hasErrors() {
		Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages();
		for (; messages.hasNext();) {
			FacesMessage message = messages.next();
			if (message.getSeverity().compareTo(FacesMessage.SEVERITY_ERROR) == 0) {
				return true;
			}
		}

		return false;
	}

}
