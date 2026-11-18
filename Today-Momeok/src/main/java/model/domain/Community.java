package model.domain;

import java.util.Date;
import java.util.List;

/**
 * 커뮤티니 관리를 위해 필요한 도메인 클래스. Community 테이블과 대응됨
 */
public class Community {
	private int id;
	private String name;
	private String description;
	private Date startDate;
	private String chairId;
	private int numOfMembers;
	private List<User> memberList;

	public Community() {}		// 기본 생성자
	
	public Community(int id, String name, String description, Date startDate, String chairId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.chairId = chairId;
		//this.setNumOfMembers(0);
	}
	
	public Community(int id, String name, String description, int numOfMembers) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.numOfMembers = numOfMembers;
	}

	public Community(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getChairId() {
		return chairId;
	}

	public void setChairId(String chairId) {
		this.chairId = chairId;
	}

	public int getNumOfMembers() {
		return numOfMembers;
	}

	public void setNumOfMembers(int numOfMembers) {
		this.numOfMembers = numOfMembers;
	}

	public List<User> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<User> memberList) {
		this.memberList = memberList;
	}

	@Override
	public String toString() {
		return "Community [id=" + id + ", name=" + name + ", description=" + description + ", startDate=" + startDate
				+ ", chairId=" + chairId + ", numOfMembers=" + numOfMembers + "]";
	}
}
