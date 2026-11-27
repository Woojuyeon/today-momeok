package controller.page;

import controller.Controller;
import controller.user.UserSessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.service.GroupManager;
import model.domain.Group;

public class MakeGroupPageController implements Controller {
    private final GroupManager groupManager = new GroupManager();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            HttpSession session = request.getSession(false);
            String userId = session == null ? null : UserSessionUtils.getLoginUserId(session);
            if (userId == null) {
                return "redirect:/login";
            }
            Group group = (Group) session.getAttribute("group");
            if (group == null) {
                group = groupManager.createGroup(userId, null);
                session.setAttribute("group", group);
            }
            request.setAttribute("group", group);
            return "/group/makegroup.jsp";
        }

        return null;
    }
}
