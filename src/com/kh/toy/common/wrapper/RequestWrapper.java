package com.kh.toy.common.wrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class RequestWrapper extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	
	public RequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}
	
	@Override
	public String getRequestURI() {
		return request.getRequestURI().replaceAll("/a_servlet/","");
	}
	
	@Override
	public RequestDispatcher getRequestDispatcher(String path) {
		return request.getRequestDispatcher("/WEB-INF/views"+ path +".jsp");
	}
	public RequestDispatcher getRequestDispatcher(String path,String prefix, String suffix) {
		return request.getRequestDispatcher(prefix + path + suffix);
	}
	
	
	public String[] getRequestURIArray() {
		String uri = this.getRequestURI();
		String[] uriArr = uri.split("/");
		return uriArr;
	}
}
