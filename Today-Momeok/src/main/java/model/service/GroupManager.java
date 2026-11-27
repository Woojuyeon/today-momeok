package model.service;

import model.dao.UserDAO;
import model.domain.Group;
import model.domain.User;
import model.dto.UserDTO;

public class GroupManager {
    private static final int DEFAULT_FILTER_DAYS = 3;
    private final UserDAO userDAO;

    public GroupManager() {
        this(new UserDAO());
    }

    GroupManager(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserDTO findMemberById(String userId) {
        return userDAO.findById(userId);
    }

    public Group createGroup(String userId, String userName) {
        User member = new User();
        member.setUserId(userId);
        member.setName(userName);

        Group group = new Group();
        group.addMember(member);
        group.setFilterDays(DEFAULT_FILTER_DAYS);
        return group;
    }

    public boolean addMember(Group group, User member) {
        for (User existing : group.getMembers()) {
            if (existing.getUserId().equals(member.getUserId())) {
                return false;
            }
        }
        group.addMember(member);
        return true;
    }
}
