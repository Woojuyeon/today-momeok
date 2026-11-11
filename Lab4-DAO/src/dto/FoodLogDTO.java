package dto;

import java.sql.Date;
import java.util.List;

public class FoodLogDTO {
	private Date date;
	private List<String>members;
	private String foodName;
	
	public FoodLogDTO() {
		super();
	}
	
	public Date getDate() {
		return date;
	}	
	public void setDate(Date date) {
		this.date = date;
	}
	public List<String> getMembers() {
		return members;
	}
	public void setMembers(List<String> members) {
		this.members = members;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
}
