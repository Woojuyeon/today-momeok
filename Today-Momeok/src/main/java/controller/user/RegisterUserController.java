package controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.domain.Community;
import model.domain.User;
import model.service.ExistingUserException;
import model.service.UserManager;

public class RegisterUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       	if (request.getMethod().equals("GET")) {	// GET request: 회원정보 등록 form 요청	
    		List<Community> commList = UserManager.getInstance().findCommunityList();	// 커뮤니티 리스트 검색
			request.setAttribute("commList", commList);	
		
			return "/user/registerForm.jsp";   // 검색된 커뮤니티 리스트를 registerForm으로 전송    	
	    }	
       	
    	// POST request 처리    	
 		User user = new User(
			request.getParameter("userId"),
			request.getParameter("password"),
			request.getParameter("name"),
			request.getParameter("email"),
			request.getParameter("phone"),
			Integer.parseInt(request.getParameter("commId")));	// 커뮤니티 Id 추출 
		
        log.debug("Create User : {}", user);

		try {
			UserManager manager = UserManager.getInstance();
			manager.create(user);
	        return "redirect:/user/list";		// 사용자 리스트 요청으로 redirect
	        
		} catch (ExistingUserException e) {		// 예외 발생 시 회원가입 form으로 forwarding
           request.setAttribute("registerFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("user", user);
			return "/user/registerForm.jsp";
		}
    }
}
