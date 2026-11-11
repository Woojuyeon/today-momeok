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
            int groupId = 0;
            if (rs.next()) {
                groupId = rs.getInt("group_id");
            }
            
            query = new StringBuffer();
            query.append("INSERT INTO groups ");
            query.append("(group_id, do_use_food_history, filter_days) ");
            query.append("VALUES (?, ?, ?)");
            
            Object[] param = new Object[] {groupId, group.getFilterFoodLog() ? 1 : 0, group.getFilterDays()};
            
            jdbcUtil.setSqlAndParameters(query.toString(), param);
            jdbcUtil.executeUpdate();
            
            query = new StringBuffer();
            query.append("INSERT INTO group_member ");
            query.append("(groups_group_id, is_favorite_group, users_user_id) ");
            query.append("VALUES (?, 0, ?)");
            
            if (group.getMembers() != null) {
                for (int i = 0; i < group.getMembers().size(); i++) {
                    param = new Object[] {groupId, group.getMembers().get(i).getUserId()};
                    
                    jdbcUtil.setSqlAndParameters(query.toString(), param);
                    jdbcUtil.executeUpdate();
                }
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
		query.append("WHERE group_id = ? AND is_deleted = 0");
		
		Object[] param = new Object[] {groupId};
		
		jdbcUtil.setSqlAndParameters(query.toString(), param);
		
		try {
            ResultSet rs = null;
            rs = jdbcUtil.executeQuery();
            
            if (rs.next()) {
                GroupDTO group = new GroupDTO();
                group.setGroupId(rs.getInt("group_id"));
                group.setFilterFoodLog(rs.getInt("do_use_food_history") == 1);  
            	group.setFilterDays(rs.getInt("filter_days"));
            	return group;
            }
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
		query.append("SELECT g.group_id, g.do_use_food_history, g.filter_days ");
        query.append("FROM groups g JOIN group_member gm ON g.group_id = gm.groups_group_id ");
        query.append("WHERE gm.users_user_id = ? AND g.is_deleted = 0");
		
		Object[] param = new Object[] {userId};
		
		jdbcUtil.setSqlAndParameters(query.toString(), param);
		
		try {
			GroupDTO group;
            ResultSet rs = null;
            rs = jdbcUtil.executeQuery();
            
            if (rs.next()) {
            	group = new GroupDTO();
            	group.setGroupId(rs.getInt("group_id"));
            	group.setFilterFoodLog(rs.getInt("do_use_food_history") == 1);
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
		query.append("SELECT u.user_id, u.user_name");
		query.append("FROM users u JOIN group_member gm ON u.user_id = gm.users_user_id");
		query.append("WHERE gm.groups_group_id = ?");
		
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
		query.append("INSERT INTO group_member");
		query.append("(groups_group_id, is_favorite_group, users_user_id)"
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
	
	//그룹 즐겨찾기 설정/해제
	// favorite: true -> is_favorite_group = 1, false -> 0
	//즐겨찾기 삭제할 때 사용하려고 이름 바꿨음
	public boolean updateFavoriteGroup(GroupDTO group, String userId, boolean favorite) {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE group_member SET is_favorite_group = ?");
		query.append("WHERE groups_group_id = ? AND users_user_id = ?");
		Object[] param = new Object[] { favorite ? 1 : 0, group.getGroupId(), userId };
		
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
	
	// 그룹 즐겨찾기 삭제 - is_favorite_group = 0
	public boolean deleteFavoriteGroup(GroupDTO group, String userId) {
	    return updateFavoriteGroup(group, userId, false);
	}
}
