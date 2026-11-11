package dto;

import java.io.Serializable;
import java.util.Date;

public class FoodLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int foodLogId;          // 식사 이력 번호 (PK, foodlog_id_sequence로 생성)
    private String userId;          // 먹은 사용자
    private Integer foodId;         // 음식 번호 (음식 삭제 시 NULL 가능)
    private Integer groupId;        // 그룹 번호 (혼자 먹었거나 그룹 삭제 시 NULL)
    private Date eatenDate;         // 먹은 날짜

    // 조회(v_food_log_detail) 시에만 채워짐. foodId가 null이면 모두 null/기본값
    private String foodName;
    private String foodCategory;
    private int foodCalories;
    private boolean foodDeleted;    // 음식이 soft delete 되었는지 boolean 처리

    public FoodLogDTO() { }

    // 식사 이력 추가 시 사용 (foodLogId는 시퀀스가 생성하므로 제외)
    public FoodLogDTO(String userId, Date eatenDate, Integer groupId, Integer foodId) {
        this.userId = userId;
        this.eatenDate = eatenDate;
        this.groupId = groupId;
        this.foodId = foodId;
    }

    public int getFoodLogId() {
        return foodLogId;
    }
    public void setFoodLogId(int foodLogId) {
        this.foodLogId = foodLogId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Integer getFoodId() {
        return foodId;
    }
    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }
    public Integer getGroupId() {
        return groupId;
    }
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
    public Date getEatenDate() {
        return eatenDate;
    }
    public void setEatenDate(Date eatenDate) {
        this.eatenDate = eatenDate;
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
    public boolean isFoodDeleted() {
        return foodDeleted;
    }
    public void setFoodDeleted(boolean foodDeleted) {
        this.foodDeleted = foodDeleted;
    }

    @Override
    public String toString() {
        return "FoodLogDTO [foodLogId=" + foodLogId + ", userId=" + userId
                + ", foodId=" + foodId + ", groupId=" + groupId
                + ", eatenDate=" + eatenDate + ", foodName=" + foodName + "]";
    }
}