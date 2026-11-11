package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.GroupDTO;
import dto.UserDTO;
import util.JDBCUtil;

public class GroupDAO {
	private JDBCUtil jdbcUtil = null;
	
	public GroupDAO() {
		jdbcUtil = new JDBCUtil();// JDBCUtil 객체 생성
	}
	
	//그룹 즐겨찾기 생성
	public GroupDTO createGroup(GroupDTO group, String userId) {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT group_id_sequence.NEXTVAL ");
		query.append("AS group_id FROM dual");
		
		jdbcUtil.setSqlAndParameters(query.toString(), null);
		
		
		try {
            ResultSet rs = null;
            rs = jdbcUtil.executeQuery();
            String groupId = null;
            if (rs.next()) {
            	groupId = rs.getString("group_id");
            }
            
            query = new StringBuffer();
            query.append("INSERT INTO groups ");
            query.append("(group_id, filter_food_log, filter_days) ");
            query.append("VALUES (?, ?, ?)");
            
            Object[] param = new Object[] {groupId, group.getFilterFoodLog(), group.getFilterDays()};
            
            jdbcUtil.setSqlAndParameters(query.toString(), param);
            jdbcUtil.executeUpdate();
            
            query = new StringBuffer();
            query.append("INSERT INTO group_member ");
            query.append("(groups_group_id, is_favorite_group, users_user_id) ");
            query.append("VALUES (?, 0, ?)");
            
            for (int i = 0; i < group.getMembers().size(); i++) {
            	param = new Object[] {groupId, group.getMembers().get(i)};
                
                jdbcUtil.setSqlAndParameters(query.toString(), param);
                jdbcUtil.executeUpdate();
            }
                      
                        
            jdbcUtil.commit();
                      
            return group;
            
        }catch(Exception e) {
            e.printStackTrace();
            jdbcUtil.rollback();
           
        }finally {
            jdbcUtil.close();
        }
		
		return group;
	}
	
	//그룹 조회
	public GroupDTO findGroupById(int groupId) {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT group_id, filter_food_log, filter_days ");
		query.append("FROM groups ");
		query.append("WHERE group_id = ?");
		
		Object[] param = new Object[] {groupId};
		
		jdbcUtil.setSqlAndParameters(query.toString(), param);
		
		try {
			GroupDTO group = new GroupDTO();
            ResultSet rs = null;
            rs = jdbcUtil.executeQuery();
            
            if (rs.next()) {
            	group.setGroupId(rs.getInt("group_id"));
            	group.setFilterFoodLog(rs.getBoolean("filter_food_log"));
            	group.setFilterDays(rs.getInt("filter_days"));
            }
            
            return group;
        }catch(Exception e) {
            e.printStackTrace();
            jdbcUtil.rollback();
           
        }finally {
            jdbcUtil.close();
        }
		return null;
	}
	// id로 그룹찾기
	public List<GroupDTO> findgroupsById(String userId) {
		List<GroupDTO> groupList = new ArrayList<GroupDTO>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT groups.group_id, filter_food_log, filter_days ");
		query.append("FROM groups JOIN group_members ON groups.group_id=group_members.group_id");
		query.append("WHERE user_id = ?");
		
		Object[] param = new Object[] {userId};
		
		jdbcUtil.setSqlAndParameters(query.toString(), param);
		
		try {
			GroupDTO group;
            ResultSet rs = null;
            rs = jdbcUtil.executeQuery();
            
            if (rs.next()) {
            	group = new GroupDTO();
            	group.setGroupId(rs.getInt("group_id"));
            	group.setFilterFoodLog(rs.getBoolean("filter_food_log"));
            	group.setFilterDays(rs.getInt("filter_days"));
            	groupList.add(group);
            }
            
            return groupList;
        }catch(Exception e) {
            e.printStackTrace();
            jdbcUtil.rollback();
           
        }finally {
            jdbcUtil.close();
        }
		return groupList;	// 빈 리스트 반환
	}
	
	// 멤버 리스트
	public List<UserDTO> getAllMembers(int groupId) {
		List<UserDTO> userList = new ArrayList<UserDTO>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT users.user_id, user_name ");
		query.append("FROM users JOIN group_members ON users.user_id=group_members.user_id");
		query.append("WHERE users.user_id = ?");
		
		Object[] param = new Object[] {groupId};
		
		jdbcUtil.setSqlAndParameters(query.toString(), param);
		
		try {
			UserDTO user;
            ResultSet rs = null;
            rs = jdbcUtil.executeQuery();
            
            if (rs.next()) {
            	user = new UserDTO();
            	user.setUserId(rs.getString("user_id"));
                user.setUserName(rs.getString("user_name"));
                userList.add(user);
            }
            
            return userList;
        }catch(Exception e) {
            e.printStackTrace();
            jdbcUtil.rollback();
           
        }finally {
            jdbcUtil.close();
        }
		return userList;
	}
	
	// 멤버 추가
	public GroupDTO addMember(GroupDTO group, String userId) {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO group_member ");
		query.append("(groups_group_id, is_favorite_group, users_user_id "
				+ "VALUES (?, 0, ?)");
		
		Object[] param = new Object[] {group.getGroupId(), userId};
		
		jdbcUtil.setSqlAndParameters(query.toString(), param);
		try {
            jdbcUtil.executeUpdate();
            
            jdbcUtil.commit();
            
            return group;
        }catch(Exception e) {
            e.printStackTrace();
            jdbcUtil.rollback();
           
        }finally {
            jdbcUtil.close();
        }
		return null;
	}
	
	public boolean deleteMember(GroupDTO group, String userId) {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM group_member ");
		query.append("WHERE groups_group_id=? AND users_user_id");
		
		
		Object[] param = new Object[] {group.getGroupId(), userId};
		
		jdbcUtil.setSqlAndParameters(query.toString(), param);
		try {
            jdbcUtil.executeUpdate();
            
            jdbcUtil.commit();
            
            return true;
        }catch(Exception e) {
            e.printStackTrace();
            jdbcUtil.rollback();
           
        }finally {
            jdbcUtil.close();
        }
		return false;
	}
	
	// 그룹 즐겨찾기 삭제
	public deleteFavoriteGroup(GroupDTO group, String userId) {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE FROM group_member ");
		query.append("SET  ");
		query.append("WHERE groups_group_id=? AND users_user_id=?");
	}
}
