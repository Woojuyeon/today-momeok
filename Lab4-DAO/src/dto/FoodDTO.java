package service.dto;

import java.io.Serializable;
import java.util.Arrays;

// 음식 정보를 저장하기 위한 DTO (food 테이블의 컬럼과 1:1 대응)
public class FoodDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int foodId;				// 음식 번호 (PK, food_id_sequence로 생성)
	private String foodName;		// 음식명 (UNIQUE)
	private String foodCategory;	// 음식 카테고리: ASN, CHN, ETC, JPN, KOR, WES
	private int foodCalories;		// 칼로리 (0 ~ 10000)
	private byte[] foodImage;		// 음식 이미지 (BLOB, 목록 조회 시에는 null)

	public FoodDTO() { }

	// 음식 추가 시 사용 (foodId는 시퀀스가 생성하므로 제외)
	public FoodDTO(String foodName, String foodCategory, int foodCalories) {
		this.foodName = foodName;
		this.foodCategory = foodCategory;
		this.foodCalories = foodCalories;
	}

	public FoodDTO(int foodId, String foodName, String foodCategory, int foodCalories, byte[] foodImage) {
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodCategory = foodCategory;
		this.foodCalories = foodCalories;
		this.foodImage = foodImage;
	}

	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodCategory() {
		return foodCategory;
	}
	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}
	public int getFoodCalories() {
		return foodCalories;
	}
	public void setFoodCalories(int foodCalories) {
		this.foodCalories = foodCalories;
	}
	public byte[] getFoodImage() {
		return foodImage;
	}
	public void setFoodImage(byte[] foodImage) {
		this.foodImage = foodImage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof FoodDTO)) return false;
		return foodId == ((FoodDTO) obj).foodId;	// 음식 번호(PK)가 같으면 같은 음식
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(foodId);
	}

	@Override
	public String toString() {
		return "FoodDTO [foodId=" + foodId + ", foodName=" + foodName
				+ ", foodCategory=" + foodCategory + ", foodCalories=" + foodCalories
				+ ", foodImage=" + (foodImage == null ? "없음" : foodImage.length + " bytes") + "]";
	}
}
