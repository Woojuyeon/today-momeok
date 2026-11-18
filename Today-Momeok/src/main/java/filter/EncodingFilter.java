package filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.annotation.WebInitParam;

/**
/* ServletRequest 객체의 인코딩 방식을 설정하는 Filter 클래스		   
 */
//@WebFilter(filterName="Encoding Filter", urlPatterns="/*",
//	initParams=@WebInitParam(name="encoding", value="utf-8")) 	// 한글 처리를 위해 utf-8 사용 
public class EncodingFilter implements Filter {
	private String encoding = null;
	
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
		this.encoding = filterConfig.getInitParameter("encoding");		// "utf-8"
	}
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		if (request.getCharacterEncoding() == null) {
			if (encoding != null) {
				request.setCharacterEncoding(encoding);		// "utf-8"
			}
		}
		chain.doFilter(request, response);
	}
	
    @Override
    public void destroy() {
		this.encoding = null;
	}
}