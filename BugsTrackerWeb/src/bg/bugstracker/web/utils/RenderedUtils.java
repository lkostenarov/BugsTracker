package bg.bugstracker.web.utils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import bg.bugstracker.entity.UserModel;

@ManagedBean (name="renderedUtils")
@SessionScoped
public class RenderedUtils {

	@Inject
	HttpServletRequest request;
	
	public boolean guestRender() {
         UserModel loggedUser = (UserModel) request.getSession().getAttribute("LOGGED_USER");
         if(loggedUser.getId() >0 ){
        	 return true;
         } else {
             return false;
         }
	}
}
