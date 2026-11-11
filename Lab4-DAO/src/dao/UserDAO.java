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
        
        //
        String query = "INSERT INTO USERS "
                + "(userId, password, name) "
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
        
        String query = "SELECT userId, password "
                + "FROM USERS "
                + "WHERE userId = ? AND password = ?";
        
        try {
            jdbcUtil.setSqlAndParameters(query, new Object[] {userId, password});
            
            ResultSet rs = null;
            rs = jdbcUtil.executeQuery();
            
            if (rs.next()) {
                UserDTO user = new UserDTO();

                user.setUserId(rs.getString("USERID"));
                user.setPassword(rs.getString("PASSWORD"));

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
                + "SET userName = ?, password = ? "
                + "WHERE userId = ?";
        
        try {
            jdbcUtil.setSqlAndParameters(query, new Object[] {user.getUserName(), user.getPassword(), userId});

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            jdbcUtil.close();
        }
        
        return user;
    }

}
