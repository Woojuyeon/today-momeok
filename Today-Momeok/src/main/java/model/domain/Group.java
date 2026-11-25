package model.domain;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupId;
    private final List<User> members = new ArrayList<>();
    private boolean filterFoodLog;
    private int filterDays;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<User> getMembers() {
        return members;
    }

    public boolean addMember(User member) {
        return members.add(member);
    }

    public boolean isFilterFoodLog() {
        return filterFoodLog;
    }

    public void setFilterFoodLog(boolean filterFoodLog) {
        this.filterFoodLog = filterFoodLog;
    }

    public int getFilterDays() {
        return filterDays;
    }

    public void setFilterDays(int filterDays) {
        this.filterDays = filterDays;
    }
}
