package model.service;

import model.domain.Group;
import model.domain.User;

public class GroupManager {
    private static final int DEFAULT_FILTER_DAYS = 3;

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
