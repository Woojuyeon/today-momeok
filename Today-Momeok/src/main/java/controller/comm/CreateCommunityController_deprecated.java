package controller.comm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.domain.Community;
import model.service.UserManager;

public class CreateCommunityController_deprecated implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateCommunityController_deprecated.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if (request.getMethod().equals("GET")) { 	// GET request: creation form 요청 		
            return "/community/creationForm.jsp";		// creation form으로 forwarding
    	}
    			
    	// POST request 처리    	
 		Community comm = new Community(0,
    		request.getParameter("name"),
			request.getParameter("desc"),
			null, null);		
        
		try {
			UserManager manager = UserManager.getInstance();
			manager.createCommunity(comm);
			
	    	log.debug("Create Community : {}", comm);
	        return "redirect:/community/list";	// 성공 시 커뮤니티 리스트 화면으로 redirect
	        
		} catch (Exception e) {		// 예외 발생 시 입력 form으로 forwarding
            request.setAttribute("creationFailed", true);
			request.setAttribute("exception", e);
			request.setAttribute("comm", comm);
			return "/community/creationForm.jsp";
		}
    }
}
