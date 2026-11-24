package controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.dao.UserDAO;
import model.dto.UserDTO;

import java.util.List;


import controller.Controller;


public class RegisterUserController implements Controller {
    //private static final Logger log = LoggerFactory.getLogger(RegisterUserController.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
       	if (request.getMethod().equals("GET")) {	// GET request: 회원정보 등록 form 요청	
			return "/user/register.jsp";
	    }	
       	
    	// POST request 처리    	
       	String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
		
        UserDTO user = new UserDTO();
        user.setUserId(userId);
        user.setPassword(password);
        user.setAge(Integer.parseInt(age));
        user.setGender(gender);

        UserDAO userDAO = new UserDAO();

        UserDTO result = userDAO.createUser(user);

        if (result != null) {
            return "redirect:/login";
        } else {
            request.setAttribute("registerFailed", true);
            request.setAttribute("errorMessage", "회원가입에 실패했습니다.");
            return "/user/register.jsp";
        }
    }
}
