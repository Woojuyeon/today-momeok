package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

import dto.UserDTO;
import dto.FoodLogDTO;

public class FoodLogDAO {
    //식사이력조회
    public List<FoodLogDTO> getFoodLog(String userId){
        
        JDBCUtil jdbcUtil = new JDBCUtil();

        List<FoodLogDTO> foodList = new ArrayList<>();

        String query = "SELECT userId, date, group, foodId "
                + "FROM FOOD_LOG "
                + "WHERE user_id = ? "
                + "ORDER BY food_date DESC";

        try {
            jdbcUtil.setSqlAndParameters(query,
                    new Object[] {userId});

            ResultSet rs = jdbcUtil.executeQuery();

            while (rs.next()) {

                FoodLogDTO foodLog = new FoodLogDTO();

                /*
                foodLog.setUserId(rs.getString("USER_ID"));
                foodLog.setDate(rs.getDate("FOOD_DATE").toString());
                foodLog.setGroup(rs.getString("FOOD_GROUP"));
                foodLog.setFoodId(rs.getInt("FOOD_ID"));

                foodList.add(foodLog);
                */
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            jdbcUtil.close();
        }
        
        return foodList;
    }
    
    //식사 이력 추가
    public void createLog(String userId, Date date, String group, String foodId) {
        JDBCUtil jdbcUtil = new JDBCUtil();
        
        String query = "INSERT INTO FOODLOG "
                + "(userId, date, group, foodId) "
                + "VALUES (?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?)";
        
        try {
            jdbcUtil.setSqlAndParameters(query, new Object[] {userId, date, group, foodId});

            

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            jdbcUtil.close();
        }
    }

}
