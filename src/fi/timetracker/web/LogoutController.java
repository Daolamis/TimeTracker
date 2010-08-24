package fi.timetracker.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * @author Petteri Parviainen
 */
public class LogoutController extends AbstractController {

	public ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) {
		request.getSession().invalidate();
		return new ModelAndView("login");
	}

}
