package dto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String userId;
    private String password;
    private String userName;
    private List<FoodDTO> like;
    private List<FoodDTO> hate;
    private String role;
    private List<GroupDTO> favoriteGroup;
    
    public List<FoodDTO> getLike() {
        return like;
    }
    public void setLike(List<FoodDTO> like) {
        this.like = like;
    }
    public List<FoodDTO> getHate() {
        return hate;
    }
    public void setHate(List<FoodDTO> hate) {
        this.hate = hate;
    }
    public List<GroupDTO> getFavoriteGroup() {
        return favoriteGroup;
    }
    public void setFavoriteGroup(List<GroupDTO> favoriteGroup) {
        this.favoriteGroup = favoriteGroup;
    }
    
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
    
}
