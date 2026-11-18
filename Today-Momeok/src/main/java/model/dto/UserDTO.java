package dto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {
    private String userId;
    private String password;
    private String userName;
    private String role;
    private int Age;
    private String gender;
    private byte[] image;
    
    public String getGender() {
		return gender;
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

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

    

    
    
}
