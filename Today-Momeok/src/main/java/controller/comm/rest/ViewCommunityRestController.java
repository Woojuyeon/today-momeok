package controller.comm.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import controller.Controller;
import model.domain.Community;
import model.service.UserManager;

public class ViewCommunityRestController implements Controller {
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {			
    	
    	if (request.getMethod().equals("GET")) { 	// GET request
    		String communityId = (String)request.getAttribute("param");
 			int commId = Integer.parseInt(communityId);
			Community comm = UserManager.getInstance().findCommunity(commId);	// 커뮤니티 상세 정보 검색 (회원들의 정보도 함께 검색됨)		
					
			request.setAttribute("result", comm);	// 커뮤니티 객체를 request에 저장			
    	}
    	
		return null;	// uri 대신 null 리턴
    }
}
