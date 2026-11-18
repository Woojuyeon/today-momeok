package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FoodLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int foodLogId;          // 식사 이력 번호 (PK, foodlog_id_sequence로 생성)
    private String userId;          // 먹은 사용자
    private Integer foodId;         // 음식 번호 (음식이 실제 삭제된 경우 NULL 가능)
    private Integer groupId;        // 그룹 번호 (그룹 없이 먹었으면 NULL)
    private Date eatenDate;         // 먹은 날짜

    // 조회 시 채우는 표시 정보. 멤버 이름은 연결된 그룹의 현재 구성 기준이다.
    private String foodName;
    private List<String> members = new ArrayList<String>();

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
    public List<String> getMembers() {
        return members;
    }
    public void setMembers(List<String> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "FoodLogDTO [foodLogId=" + foodLogId + ", userId=" + userId
                + ", foodId=" + foodId + ", groupId=" + groupId
                + ", eatenDate=" + eatenDate + ", foodName=" + foodName
                + ", members=" + members + "]";
    }
}
