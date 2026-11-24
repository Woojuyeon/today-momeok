
package controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import controller.Controller;
import model.dao.UserDAO;
import model.dto.UserDTO;

public class LoginController implements Controller {

    @Override
    public String execute(HttpServletRequest request,
                          HttpServletResponse response) throws Exception {

        // GET 요청: 로그인 화면
        if (request.getMethod().equals("GET")) {
            return "/user/login.jsp";
        }

        // POST 요청: 로그인 처리
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        
        try {
        	UserDAO userDAO = new UserDAO();
            UserDTO user = userDAO.login(userId, password);
            
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getUserId());

            //리다이렉션
            return "redirect:/user/list";
            
        }catch(Exception e) {
        	request.setAttribute("loginFailed", true);
            request.setAttribute("errorMessage",
                    "아이디 또는 비밀번호가 일치하지 않습니다.");

            return "/user/login.jsp"; //로그인 화면으로 다시 돌아감
        }
    }
}
