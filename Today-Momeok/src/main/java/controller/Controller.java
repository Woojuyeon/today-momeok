package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Controller {
	// request를 처리한 후 이동할 JSP의 경로 또는 redirection 주소를 반환
    public String execute(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception;
}
