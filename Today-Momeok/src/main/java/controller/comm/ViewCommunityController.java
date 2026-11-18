package controller.comm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Community;
import model.service.UserManager;

public class ViewCommunityController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
		int commId = Integer.parseInt(request.getParameter("commId"));
		Community comm = UserManager.getInstance().findCommunity(commId);	// 커뮤니티 정보 검색			
		
		request.setAttribute("community", comm);	// 커뮤니티 정보 저장			
		
		return "/community/view.jsp";				// 커뮤니티 보기 화면으로 이동
    }
}
