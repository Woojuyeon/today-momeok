package dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import dto.FoodDTO;

public class FoodDAO {
	private JDBCUtil jdbcUtil = null;

	public FoodDAO() {
		jdbcUtil = new JDBCUtil();
	}

	// 목록용 SELECT (이미지 제외)
	private static final String LIST_QUERY =
			"SELECT food_id, food_name, food_category, food_calories FROM food ";

	// 상세용 SELECT (이미지 포함)
	private static final String DETAIL_QUERY =
			"SELECT food_id, food_name, food_category, food_calories, food_image FROM food ";

	// 음식명 검색
	public List<FoodDTO> searchFoodsByName(String foodName) {
		List<FoodDTO> foodList = new ArrayList<FoodDTO>();
		String query = "SELECT food_id, food_name, food_category, food_calories FROM food " 
					+ "WHERE food_name LIKE ? AND is_delete=false ORDER BY food_name";
		Object[] param = new Object[] { "%" + foodName + "%" };
		
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
            	foodList.add(food);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            jdbcUtil.close();
        }
		return foodList;		// 아무것도 없으면 빈 리스트 반환
	}

	// 카테고리별 음식 검색 - 음식 카테고리 검색 기능 
	public List<FoodDTO> searchFoodsByCategory(String foodCategory) {
		List<FoodDTO> foodList = new ArrayList<FoodDTO>();
		String query = "SELECT food_id, food_name, food_category, food_calories , food_image FROM food "
				+ "WHERE food_category = ? ORDER BY food_name";
		Object[] param = new Object[] { foodCategory };	
		
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


	// 같은 이름의 음식이 이미 있는지 확인 (음식 추가 전 중복 검사용)
	public boolean existsFoodName(String foodName) {
		String sql = "SELECT COUNT(*) FROM food WHERE food_name = ?";
		Object[] param = new Object[] { foodName };
		jdbcUtil.setSqlAndParameters(sql, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return false;
	}

	// 음식 추가
	public FoodDTO insertFood(FoodDTO food) throws SQLException {
		String query = "INSERT INTO food (food_id, food_name, food_category, food_calories, food_image) "
				+ "VALUES (food_id_sequence.NEXTVAL, ?, ?, ?, ?)";
		Object[] param = new Object[] { food.getFoodName(), food.getFoodCategory(),
				food.getFoodCalories(), food.getFoodImage() };
		jdbcUtil.setSqlAndParameters(query, param);
		try {
			jdbcUtil.executeQuery();

			jdbcUtil.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			jdbcUtil.rollback();
			return null;
		} finally {
			jdbcUtil.close();
		}
		return food;
	}

	// 음식 정보 수정 
	public FoodDTO updateFood(FoodDTO food) throws SQLException {
		String query = "UPDATE food SET food_name = ?, food_category = ?, food_calories = ?,  food_image = ? "
				+ "WHERE food_id = ?";
		Object[] param = new Object[] { food.getFoodName(), food.getFoodCategory(),
				food.getFoodCalories(), food.getFoodId(), food.getFoodImage() };
		jdbcUtil.setSqlAndParameters(query, param);
		try {
			jdbcUtil.executeQuery();

			jdbcUtil.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			jdbcUtil.rollback();
			return null;
		} finally {
			jdbcUtil.close();
		}
		return food;
	}

	// 음식 삭제 - soft delete
	public int deleteFood(int foodId) throws SQLException {
		String query = "UPDATE food SET is_delete=true"
				+ "WHERE food_id = ?";
		Object[] param = new Object[] { foodId };
		try {
			jdbcUtil.executeQuery();

			jdbcUtil.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			jdbcUtil.rollback();
			return -1;
		} finally {
			jdbcUtil.close();
		}
		return foodId;
	}

	// DML(삽입/수정/삭제)의 공통 처리: 성공 시 commit, 실패 시 rollback 후 예외 전달
	private int executeWrite(String sql, Object[] param) throws SQLException {
		jdbcUtil.setSqlAndParameters(sql, param);
		try {
			int result = jdbcUtil.executeUpdate();
			jdbcUtil.commit();
			return result;
		} catch (SQLException ex) {
			jdbcUtil.rollback();
			throw ex;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			throw new SQLException(ex);
		} finally {
			jdbcUtil.close();
		}
	}
}
