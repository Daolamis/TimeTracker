package fi.timetracker.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
/** 
 * @author Petteri Parviainen
 */
public class SessionInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse res, Object arg2, Exception arg3)
			throws Exception {
		// nothing
		
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2, ModelAndView mav) throws Exception {
		// nothing
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object handler) throws Exception {		
		if(handler instanceof LoginController){
			// kun ollaan menossa logincontrol:lle niin session tarkastamista ei tehdä
			return true; 
		}
		
		HttpSession session = req.getSession(false);
		if(session == null || session.getAttribute("loginData") == null){
			 res.sendRedirect("loginController");
			return false;
		}else{
			return true;	
		}		
	}
}
