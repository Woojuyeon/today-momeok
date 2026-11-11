package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.JDBCUtil;

import dto.UserDTO;

public class UserDAO {
    //회원가입
    public UserDTO createUser(UserDTO user) { // userId, password, name
        JDBCUtil jdbcUtil = new JDBCUtil();
        
        String query = "INSERT INTO USERS "
                + "(user_id, user_pw, user_name) "
                + "VALUES (?, ?, ?)";
        
        try {
            jdbcUtil.setSqlAndParameters(query, new Object[] {user.getUserId(), user.getPassword(), user.getUserName()});
        }catch(Exception e) {
            e.printStackTrace();
           
        }finally {
            jdbcUtil.close();
        }
        
        return user;
    }
    
    //로그인
    public UserDTO login(String userId, String password) {
        JDBCUtil jdbcUtil = new JDBCUtil();
        
        String query = "SELECT user_id, user_pw "
                + "FROM USERS "
                + "WHERE user_id = ? AND user_pw = ?";
        
        try {
            jdbcUtil.setSqlAndParameters(query, new Object[] {userId, password});
            
            ResultSet rs = null;
            rs = jdbcUtil.executeQuery();
            
            if (rs.next()) {
                UserDTO user = new UserDTO();

                user.setUserId(rs.getString("user_id"));
                user.setPassword(rs.getString("user_pw"));

                return user;
            }
            
        }catch(Exception e) {
            e.printStackTrace();
           
        }finally {
            jdbcUtil.close();
        }
        
        return null;
    }
    
    public UserDTO updateUser(String userId, UserDTO user) {
        JDBCUtil jdbcUtil = new JDBCUtil();
        
        String query = "UPDATE USERS "
                + "SET user_name = ?, user_pw = ? "
                + "WHERE user_id = ?";
        
        try {
            jdbcUtil.setSqlAndParameters(query, new Object[] {user.getUserName(), user.getPassword(), userId});
            jdbcUtil.executeUpdate();
            
            jdbcUtil.commit();//커밋

        } catch (Exception e) {
            e.printStackTrace();
            jdbcUtil.rollback();//롤백

        } finally {
            jdbcUtil.close();
        }
        
        return user;
    }
    
    public UserDTO findById(String userId) {
    	UserDTO user = new UserDTO();		// 이거 null로 해야 하는 거 아닌감
    	JDBCUtil jdbcUtil = new JDBCUtil();
    	
    	String query = "SELECT user_id, user_pw, user_name, user_role, user_age, user_gender, user_image "
                + "FROM USERS "
                + "WHERE user_id = ?";
    	
    	try {
    		jdbcUtil.setSqlAndParameters(query, new Object[] {userId});
    		jdbcUtil.executeQuery();
    		
    		
    		ResultSet rs = null;
            rs = jdbcUtil.executeQuery();
            
            if (rs.next()) {
            	user.setUserId(rs.getString("user_id"));
            	user.setPassword(rs.getString("user_pw"));
            	user.setUserName(rs.getString("user_name"));
            	user.setRole(rs.getString("user_role"));
            	user.setGender(rs.getString("user_gender"));
            	user.setImage(rs.getBytes("user_image"));
            	return user;
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            jdbcUtil.close();
        }
    	return user;
    }
    
    

}
