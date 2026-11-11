package dto;

import java.util.List;

public class GroupDTO {
	private int groupId;
	private List<UserDTO> members;
	private boolean filterFoodLog;
	private int filterDays;

	public boolean getFilterFoodLog() {
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

	public GroupDTO() {
		super();
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public GroupDTO(List<UserDTO> members) {
		super();
		this.members = members;
	}

	public List<UserDTO> getMembers() {
		return members;
	}

	public void setMembers(List<UserDTO> members) {
		this.members = members;
	}
}
