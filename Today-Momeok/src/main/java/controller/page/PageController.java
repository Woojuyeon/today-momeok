package controller.page;

import controller.Controller;
import controller.user.UserSessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.service.GroupManager;

public class PageController implements Controller {
    private final GroupManager groupManager = new GroupManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!"GET".equals(request.getMethod())) {
            response.setHeader("Allow", "GET");
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return null;
        }

        HttpSession session = request.getSession(false);
        String userId = session == null ? null : UserSessionUtils.getLoginUserId(session);
        if (userId == null) {
            return "redirect:/user/login";
        }
        // session 정보를 불러오기 기능을 넣은 후 작동
//        String userName = (String) session.getAttribute(UserSessionUtils.USER_NAME_SESSION_KEY);
//        request.setAttribute("group", groupManager.createGroup(userId, userName));
        return "/group/makegroup.jsp";
    }
}
