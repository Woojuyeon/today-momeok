package controller.comm.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Controller;
import model.domain.Community;
import model.service.UserManager;

public class CreateCommunityRestController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateCommunityRestController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	if (request.getMethod().equals("POST")) {		
    		// POST request 처리    	
	    	String jsonData = request.getReader().readLine();	// message body에 전송된 JSON data를 읽음
	    	log.debug("input data: {}", jsonData); 
	    
	    	ObjectMapper objMapper = new ObjectMapper(); 	// Jackson2 ObjectMapper
	    	Community comm = objMapper.readValue(jsonData, Community.class); // Community 객체로 변환
	
			UserManager.getInstance().createCommunity(comm);
	    	log.debug("Create Community : {}", comm);
	   	
			request.setAttribute("result", comm);						
	    	return null;
    	
/*				
	    	Community comm = new Community(0,
	    		request.getParameter("name"),
				request.getParameter("desc"),
				null, null, null);		
	      
			try {
				UserManager.getInstance().createCommunity(comm);
				
		    	log.debug("Create Community : {}", comm);
		        return "redirect:/community/list";	// 성공 시 커뮤니티 리스트 화면으로 redirect
		        
			} catch (Exception e) {		// 예외 발생 시 입력 form으로 forwarding
	            request.setAttribute("creationFailed", true);
				request.setAttribute("exception", e);
				request.setAttribute("comm", comm);
				return "/community/creationForm.jsp";
			}
*/
    	}
    	return null;
    }
}
