package controller.comm;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Community;
import model.service.UserManager;

public class ListCommunityController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		
		List<Community> commList = UserManager.getInstance().findCommunityList();
		
		request.setAttribute("commList", commList);		// commList 객체를 request에 저장 		
		
		return "/community/listAndView.jsp";  	// 새로운 view로 이동(forwarding)     	
    }	
}
