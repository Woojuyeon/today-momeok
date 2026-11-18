package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.comm.DeleteCommunityController;
import controller.comm.ListCommunityController;
import controller.comm.UpdateCommunityController;
import controller.comm.ViewCommunityController;
import controller.comm.rest.CreateCommunityRestController;
import controller.comm.rest.ViewCommunityRestController;
import controller.user.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        // mappings.put("/", new ForwardController("index.jsp"));        
        // mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));  // 아래의 요청으로 통합
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/list", new ListUserController());
        mappings.put("/user/view", new ViewUserController());        
        // mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));  // 아래의 요청으로 통합
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/delete", new DeleteUserController());
        // mappings.put("/user/update/form", new UpdateUserController());	// 아래의 요청으로 통합
        mappings.put("/user/update", new UpdateUserController());	
        
        // 커뮤니티 관련 request mapping
        // mappings.put("/community/create", new CreateCommunityController_deprecated());
        mappings.put("/community/create", new ForwardController("/community/creationForm.jsp"));
        mappings.put("/community/delete", new DeleteCommunityController());
        mappings.put("/community/update", new UpdateCommunityController());
		mappings.put("/community/list", new ListCommunityController());
        mappings.put("/community/view", new ViewCommunityController());
        
        // 커뮤니티 생성 및 상세정보 요청에 대한 REST controller mapping 설정 추가    
        mappings.put("/rest/community/create", new CreateCommunityRestController());
        mappings.put("/rest/community/view/", new ViewCommunityRestController());

        logger.info("Initialized Request Mapping!");
    }

    public Controller findController(String uri) {	
    	// 주어진 uri에 대응되는 controller 객체를 찾아 반환
    	return mappings.get(uri);
    }
    
    public Map.Entry<String, Controller> findRestController(String uri) {	
    	// uri 끝부분에 parameter 포함 가능
    	for (Map.Entry<String, Controller> entry : mappings.entrySet()) {    		
    		if (uri.startsWith(entry.getKey()))  
    			return entry;		// map entry를 반환
    	}
    	return null;
    }
}
