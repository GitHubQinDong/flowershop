package com.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharactorFilter implements Filter {
	String encoding;

	@Override
	public void destroy() {
		encoding=null;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse res = (HttpServletResponse) response;
		if(encoding!=null){
			req.setCharacterEncoding(encoding);
			res.setContentType("text/html;charset="+encoding);
			res.setCharacterEncoding(encoding);
		}
		chain.doFilter(new CharacterEncodingRequest(req), res);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		encoding=filterConfig.getInitParameter("encoding");
	}

}
class CharacterEncodingRequest extends HttpServletRequestWrapper {  
    private HttpServletRequest request = null;  
  
    public CharacterEncodingRequest(HttpServletRequest request) {  
        super(request);  
        this.request = request;  
    }  
  
    /** 
     * 对参数重新编码 
     */  
    @Override  
    public String getParameter(String name) {  
        String value = super.getParameter(name);  
         if(value == null)  
               return null;  
         String method = request.getMethod();  
        if ("get".equalsIgnoreCase(method)) {  
            try {  
                value = new String(value.getBytes("ISO8859-1"),  
                        request.getCharacterEncoding());  
            } catch (UnsupportedEncodingException e) {  
                e.printStackTrace();  
            }  
        }  
        return value;  
    }  
  
}  