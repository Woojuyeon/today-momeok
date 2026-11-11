package dao;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;
import dto.FoodDTO;

/**
 * 음식(food 테이블) 정보를 관리하는 DAO
 *  - 조회 메소드: 오류 발생 시 예외를 출력하고 null을 반환 (강의 예제와 동일)
 *  - 삽입/수정/삭제 메소드: 실패 시 rollback 후 SQLException을 던져서
 *    Manager가 오류 유형(예: 음식명 중복 ORA-00001)을 구분할 수 있게 함
 *  - 이미지(BLOB)는 용량이 크므로 목록 조회에서는 읽지 않고, 상세 조회(getFoodById)에서만 읽음
 */
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

	// ResultSet의 현재 행을 FoodDTO로 변환
	private FoodDTO toFoodDTO(ResultSet rs, boolean withImage) throws SQLException {
		FoodDTO food = new FoodDTO();
		food.setFoodId(rs.getInt("FOOD_ID"));
		food.setFoodName(rs.getString("FOOD_NAME"));
		food.setFoodCategory(rs.getString("FOOD_CATEGORY"));
		food.setFoodCalories(rs.getInt("FOOD_CALORIES"));
		if (withImage) {
			food.setFoodImage(rs.getBytes("FOOD_IMAGE"));
		}
		return food;
	}

	// 목록을 반환하는 조회의 공통 처리
	private List<FoodDTO> queryFoodList(String sql, Object[] param) {
		jdbcUtil.setSqlAndParameters(sql, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			List<FoodDTO> list = new ArrayList<FoodDTO>();
			while (rs.next()) {
				list.add(toFoodDTO(rs, false));
			}
			return list;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/** 전체 음식 목록 조회 (음식명 순) */
	public List<FoodDTO> getFoodList() {
		String sql = LIST_QUERY + "ORDER BY food_name";
		return queryFoodList(sql, null);
	}

	/** 음식명 검색 (부분 일치) - 음식 검색 기능 */
	public List<FoodDTO> searchFoodsByName(String foodName) {
		String sql = LIST_QUERY + "WHERE food_name LIKE ? ORDER BY food_name";
		Object[] param = new Object[] { "%" + foodName + "%" };
		return queryFoodList(sql, param);
	}

	/** 카테고리별 음식 검색 - 음식 카테고리 검색 기능 */
	public List<FoodDTO> searchFoodsByCategory(String foodCategory) {
		String sql = LIST_QUERY + "WHERE food_category = ? ORDER BY food_name";
		Object[] param = new Object[] { foodCategory };
		return queryFoodList(sql, param);
	}

	/** 음식 번호로 상세 정보 조회 (이미지 포함), 없으면 null - 음식 상세 정보 조회 기능 */
	public FoodDTO getFoodById(int foodId) {
		String sql = DETAIL_QUERY + "WHERE food_id = ?";
		Object[] param = new Object[] { foodId };
		jdbcUtil.setSqlAndParameters(sql, param);
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if (rs.next()) {
				return toFoodDTO(rs, true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return null;
	}

	/** 같은 이름의 음식이 이미 있는지 확인 (음식 추가 전 중복 검사용) */
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

	/** 음식 추가 - food_id는 food_id_sequence로 생성. 반환값: 삽입된 행 수 */
	public int insertFood(FoodDTO food) throws SQLException {
		String sql = "INSERT INTO food (food_id, food_name, food_category, food_calories, food_image) "
				+ "VALUES (food_id_sequence.NEXTVAL, ?, ?, ?, ?)";
		Object[] param = new Object[] { food.getFoodName(), food.getFoodCategory(),
				food.getFoodCalories(), food.getFoodImage() };
		return executeWrite(sql, param);
	}

	/** 음식 정보 수정 (음식명, 카테고리, 칼로리). 반환값: 수정된 행 수 */
	public int updateFood(FoodDTO food) throws SQLException {
		String sql = "UPDATE food SET food_name = ?, food_category = ?, food_calories = ? "
				+ "WHERE food_id = ?";
		Object[] param = new Object[] { food.getFoodName(), food.getFoodCategory(),
				food.getFoodCalories(), food.getFoodId() };
		return executeWrite(sql, param);
	}

	/** 음식 이미지만 수정 */
	public int updateFoodImage(int foodId, byte[] foodImage) throws SQLException {
		String sql = "UPDATE food SET food_image = ? WHERE food_id = ?";
		Object[] param = new Object[] { foodImage, foodId };
		return executeWrite(sql, param);
	}

	/**
	 * 음식 삭제. 반환값: 삭제된 행 수
	 * FK의 ON DELETE 설정에 따라 like_food, hate_food는 함께 삭제되고
	 * food_log는 유지(food_food_id만 NULL)된다.
	 */
	public int deleteFood(int foodId) throws SQLException {
		String sql = "DELETE FROM food WHERE food_id = ?";
		Object[] param = new Object[] { foodId };
		return executeWrite(sql, param);
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
