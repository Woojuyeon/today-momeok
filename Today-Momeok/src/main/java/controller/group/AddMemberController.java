package controller.group;

import controller.Controller;
import controller.user.UserSessionUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.dao.UserDAO;
import model.domain.Group;
import model.domain.User;
import model.dto.UserDTO;
import model.service.GroupManager;

public class AddMemberController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("POST")) {
            HttpSession session = request.getSession(false);
            if (session == null || !UserSessionUtils.hasLogined(session)) {
                return "redirect:/login";
            }
            Group group = (Group) session.getAttribute("group");
            if (group == null) {
                return "redirect:/group";
            }

            String userId = request.getParameter("userId");
            userId = userId == null ? "" : userId.trim();
            if (userId.isEmpty() || userId.length() > 10) {
                request.setAttribute("errorMessage", "아이디를 입력해 주세요.");
            } else {
                UserDTO found = new UserDAO().findById(userId);
                if (found == null) {
                    request.setAttribute("errorMessage", "존재하지 않는 아이디입니다.");
                } else {
                    User member = new User();
                    member.setUserId(found.getUserId());
                    member.setName(found.getUserName());
                    if (!new GroupManager().addMember(group, member)) {
                        request.setAttribute("errorMessage", "이미 추가된 사용자입니다.");
                    } else {
                        return "redirect:/group";
                    }
                }
            }
            request.setAttribute("group", group);
            return "/group/makegroup.jsp";
        }


        return null;
    }
}
