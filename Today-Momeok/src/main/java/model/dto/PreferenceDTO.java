package dto;

import java.util.List;

public class PreferenceDTO {
	private List<String> like;
	private List<String> hate;
	public List<String> getLike() {
		return like;
	}
	public void setLike(List<String> like) {
		this.like = like;
	}
	public List<String> getHate() {
		return hate;
	}
	public void setHate(List<String> hate) {
		this.hate = hate;
	}
	public PreferenceDTO() {
		super();
	}
	
}
