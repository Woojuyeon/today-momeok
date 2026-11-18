package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.FoodDTO;
import util.JDBCUtil;

public class PreferenceDAO {
	private JDBCUtil jdbcUtil = null;
	
	
	public PreferenceDAO() {
		jdbcUtil = new JDBCUtil();// JDBCUtil 객체 생성
	}
	
	// 선호음식리스트
	public List<FoodDTO> findLikes(String userId) {
		List<FoodDTO> foodList = new ArrayList<FoodDTO>();
		 String query = "SELECT food.food_id, food_name, food_category, food_calories , food_image "
		 		+ "FROM food join like_food ON food.food_id=like_food.food_id "
		 		+ "WHERE user_id=?";
		 Object[] param = new Object[] { userId };
		 
		 try {
				ResultSet rs = null;
				
	    		jdbcUtil.setSqlAndParameters(query, param);
	    		rs = jdbcUtil.executeQuery();
	    		FoodDTO food;
	            while (rs.next()) {
	            	food = new FoodDTO();
	            	food.setFoodId(rs.getInt("food_id"));
	            	food.setFoodName(rs.getString("food_name"));
	            	food.setFoodCategory(rs.getString("food_category"));
	            	food.setFoodCalories(rs.getInt("food_calories"));
	            	food.setFoodImage(rs.getBytes("food_image"));
	            	foodList.add(food);
	            }
	
	        } catch (Exception e) {
	            e.printStackTrace();
	
	        } finally {
	            jdbcUtil.close();
	        }
			return foodList;		// 아무것도 없으면 빈 리스트 반환
	}
	
	// 불호음식리스트
	public List<FoodDTO> findHates(String userId) {
		List<FoodDTO> foodList = new ArrayList<FoodDTO>();
		 String query = "SELECT food.food_id, food_name, food_category, food_calories , food_image "
		 		+ "FROM food join hate_food ON food.food_id=hate_food.food_id "
		 		+ "WHERE user_id=?";
		 Object[] param = new Object[] { userId };
		 
		 try {
				ResultSet rs = null;
				
	    		jdbcUtil.setSqlAndParameters(query, param);
	    		rs = jdbcUtil.executeQuery();
	    		FoodDTO food;
	            while (rs.next()) {
	            	food = new FoodDTO();
	            	food.setFoodId(rs.getInt("food_id"));
	            	food.setFoodName(rs.getString("food_name"));
	            	food.setFoodCategory(rs.getString("food_category"));
	            	food.setFoodCalories(rs.getInt("food_calories"));
	            	food.setFoodImage(rs.getBytes("food_image"));
	            	foodList.add(food);
	            }
	
	        } catch (Exception e) {
	            e.printStackTrace();
	
	        } finally {
	            jdbcUtil.close();
	        }
			return foodList;		// 아무것도 없으면 빈 리스트 반환
	}
}
