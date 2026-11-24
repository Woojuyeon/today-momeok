package model.service;

import java.util.ArrayList;
import java.util.List;

import dto.GroupDTO;
import dto.UserDTO;

public class GroupManager {
    private static final int DEFAULT_FILTER_DAYS = 3;

    public GroupDTO createGroup(String userId, String userName) {
        UserDTO member = new UserDTO();
        member.setUserId(userId);
        member.setUserName(userName);

        List<UserDTO> members = new ArrayList<>();
        members.add(member);

        GroupDTO group = new GroupDTO(members);
        group.setFilterDays(DEFAULT_FILTER_DAYS);
        return group;
    }
}
