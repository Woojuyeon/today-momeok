package controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.domain.User;
import model.service.UserManager;

public class DeleteUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
    	// 로그인 여부 확인
    	if (!UserSessionUtils.hasLogined(request.getSession())) {
            // return "redirect:/user/login/form";		
    		return "redirect:/user/login";		// login form 요청으로 redirect
    	}
    	
    	String deleteId = request.getParameter("userId");
    	log.debug("Delete User : {}", deleteId);

		UserManager manager = UserManager.getInstance();		
		HttpSession session = request.getSession();	
	
		if (!deleteId.equals("admin") && 	// 삭제하려는 사용자가 관리자가 아니어야 함 (관리자 계정은 삭제 불가)
			(UserSessionUtils.isLoginUser("admin", session) || 	// 현재 사용자가 관리자이거나 (다른 계정 삭제 가능)
			 UserSessionUtils.isLoginUser(deleteId, session))) { 	// 현재 사용자가 자신의 계정을 삭제하는 경우 (가능)
			  				
			manager.remove(deleteId);				// 사용자 정보 삭제
			
			if (UserSessionUtils.isLoginUser("admin", session)) {	// 로그인한 사용자가 관리자 	
				return "redirect:/user/list";		// 사용자 리스트로 이동
			}
			else {									// 로그인한 사용자는 이미 삭제됨
				return "redirect:/user/logout";		// logout 처리
			}
		}
		
		/* 삭제가 불가능한 경우 */
		User user = manager.findUser(deleteId);	// 사용자 정보 검색
		request.setAttribute("user", user);						
		request.setAttribute("deleteFailed", true);
		String msg = (UserSessionUtils.isLoginUser("admin", session)) 
				   ? "시스템 관리자 정보는 삭제할 수 없습니다."		
				   : "타인의 정보는 삭제할 수 없습니다.";													
		request.setAttribute("exception", new IllegalStateException(msg));            
		return "/user/view.jsp";		// 사용자 보기 화면으로 이동 (forwarding)	
	}
}
