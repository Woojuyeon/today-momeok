package dto;

import java.util.List;

public class GroupDTO {
	private List<UserDTO> members;

	public GroupDTO() {
		super();
	}

	public List<UserDTO> getMembers() {
		return members;
	}

	public void setMembers(List<UserDTO> members) {
		this.members = members;
	}
}
