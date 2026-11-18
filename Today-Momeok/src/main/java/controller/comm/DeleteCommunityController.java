package controller.comm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.Controller;
import model.domain.Community;
import model.service.MemberExistsException;
import model.service.UserManager;

public class DeleteCommunityController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteCommunityController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception { 	
    	int commId = Integer.parseInt(request.getParameter("commId"));
    	log.debug("Delete Community : {}", commId);
    	UserManager manager = UserManager.getInstance();
    	try {
    		manager.removeCommunity(commId);	
	        return "redirect:/community/list";	// 커뮤니티 리스트 요청으로 redirect
	        
		} catch (MemberExistsException e) {		// 예외 발생 시 커뮤니티 조회 화면으로 forwarding
			List<Community> commList = manager.findCommunityList();	// 커뮤니티 리스트 검색
			request.setAttribute("commList", commList);		// commList 객체를 request에 저장 		
			request.setAttribute("deleteFailed", true);
			request.setAttribute("exception", e);
			return "/community/listAndView.jsp";
		}	
	}
}
