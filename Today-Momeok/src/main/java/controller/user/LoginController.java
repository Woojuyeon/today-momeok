package controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.service.UserManager;

public class LoginController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if (request.getMethod().equals("GET")) { 	// GET request: login form 요청 		
            return "/user/loginForm.jsp";		// login form으로 forwarding
    	}
    			
    	// POST request 처리    	
    	String userId = request.getParameter("userId");
		String password = request.getParameter("password");
    	log.debug("id/pwd : {}/{}", userId, password);
		
		try {
			// 모델에 로그인 처리를 위임
			UserManager manager = UserManager.getInstance();
			manager.login(userId, password);
	
			// 세션에 사용자 이이디 저장
			HttpSession session = request.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, userId);
            
            return "redirect:/user/list";		// 사용자 리스트 요청으로 redirection		
            
		} catch (Exception e) {
			/* UserNotFoundException이나 PasswordMismatchException 발생 시
			 * 다시 login form을 사용자에게 전송하고 오류 메세지도 출력
			 */
            request.setAttribute("loginFailed", true);
			request.setAttribute("exception", e);
            return "/user/loginForm.jsp";		// login form으로 forwarding		
		}	
    }
}
