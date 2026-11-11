package dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import dto.FoodLogDTO;
import util.JDBCUtil;

public class FoodLogDAO {
    //식사이력조회
    public List<FoodLogDTO> getFoodLog(String userId){
        
        JDBCUtil jdbcUtil = new JDBCUtil();

        List<FoodLogDTO> foodlogList = new ArrayList<FoodLogDTO>();

        String query = "SELECT food_log_id, users_user_id, eaten_date, groups_group_id, "
                + "food_id, food_name, food_category, food_calories, is_deleted "
                + "FROM v_food_log_detail "
                + "WHERE users_user_id = ? "
                + "ORDER BY eaten_date DESC, food_log_id DESC";
        Object[] param = new Object[] { userId };

        try {
            jdbcUtil.setSqlAndParameters(query, param);
            ResultSet rs = jdbcUtil.executeQuery();
            FoodLogDTO log;
            while (rs.next()) {
                log = new FoodLogDTO();
                log.setFoodLogId(rs.getInt("food_log_id"));
                log.setUserId(rs.getString("users_user_id"));
                log.setEatenDate(rs.getTimestamp("eaten_date"));
 
                int groupId = rs.getInt("groups_group_id");
                log.setGroupId(rs.wasNull() ? null : groupId);
 
                // v_food_log_detail 이 LEFT JOIN 이라 음식이 삭제됐으면 food_id 가 NULL
                int foodId = rs.getInt("food_id");
                if (!rs.wasNull()) {
                    log.setFoodId(foodId);
                    log.setFoodName(rs.getString("food_name"));
                    log.setFoodCategory(rs.getString("food_category"));
                    log.setFoodCalories(rs.getInt("food_calories"));
                    log.setFoodDeleted(rs.getInt("is_deleted") == 1);
                }
                foodlogList.add(log);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return foodlogList;     // 아무것도 없으면 빈 리스트 반환
    }
    
    //식사 이력 추가 (성공 1, 실패 -1)
    public int createLog(String userId, Date date, Integer groupId, Integer foodId) {
        JDBCUtil jdbcUtil = new JDBCUtil();
        
        String query = "INSERT INTO food_log "
                + "(food_log_id, users_user_id, food_food_id, groups_group_id, eaten_date) "
                + "VALUES (foodlog_id_sequence.NEXTVAL, ?, ?, ?, ?)";
        
        Date eaten = (date != null) ? date : new Date();
        Object[] param = new Object[] { userId, foodId, groupId, new Timestamp(eaten.getTime()) };

        try {
            jdbcUtil.setSqlAndParameters(query, param);
            int result = jdbcUtil.executeUpdate();
            jdbcUtil.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            jdbcUtil.rollback();
            return -1;
        } finally {
            jdbcUtil.close();
        }
     }
}
