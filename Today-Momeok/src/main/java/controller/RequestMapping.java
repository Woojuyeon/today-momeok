package controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.page.PageController;
import controller.user.*;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    // 각 요청 uri에 대한 controller 객체를 저장할 HashMap 생성
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {
    	// 각 uri에 대응되는 controller 객체를 생성 및 저장
        // mappings.put("/", new ForwardController("index.jsp"));        
        // mappings.put("/user/login/form", new ForwardController("/user/loginForm.jsp"));  // 아래의 요청으로 통합
        mappings.put("/login", new LoginController()); //맨 처음 로그인 페이지 화면
        
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/list", new ListUserController());
        mappings.put("/user/view", new ViewUserController());        
        // mappings.put("/user/register/form", new ForwardController("/user/registerForm.jsp"));  // 아래의 요청으로 통합
        mappings.put("/user/register", new RegisterUserController());
        mappings.put("/user/delete", new DeleteUserController());
        // mappings.put("/user/update/form", new UpdateUserController());	// 아래의 요청으로 통합
        mappings.put("/user/update", new UpdateUserController());	
        
        mappings.put("/group", new PageController());

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
