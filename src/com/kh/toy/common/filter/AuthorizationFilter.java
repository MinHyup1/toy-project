package com.kh.toy.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.code.MemberGrade;
import com.kh.toy.common.exception.HandlableException;
import com.kh.toy.member.model.dto.Member;

/**
 * Servlet Filter implementation class AuthorizationFilter
 */
//@WebFilter("/AuthorizationFilter") web.xml에 Filter 등록
public class AuthorizationFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AuthorizationFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String[] uriArr = httpRequest.getRequestURI().split("/");

		if (uriArr.length != 0) {

			String redirectUrl = null;

			switch (uriArr[1]) {
			case "member":
				memberAuthorize(httpRequest,httpResponse , uriArr);
				break;
			case "admin":
				adminAuthorize(httpRequest,httpResponse , uriArr);
				break;
			case "board":
				boardAuthorize(httpRequest,httpResponse , uriArr);
				break;
			case "upload":
				boardAuthorize(httpRequest,httpResponse , uriArr);
				break;
			default:
				
				break;
			}

			if (redirectUrl != null) {
				httpResponse.sendRedirect(redirectUrl);
				return;
			}
		}

		chain.doFilter(request, response);
	}

	private void boardAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		HttpSession session = httpRequest.getSession();
		Member member = (Member)session.getAttribute("authentication");
		
		switch (uriArr[2]) {
		case "board-form":
			if(member == null) {
				throw new HandlableException(ErrorCode.UNAUTORIZED_PAGE_ERROR);
			}
			break;
		default:
			break;
		}
		
	}

	private void adminAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
		HttpSession session = httpRequest.getSession();
		Member member = (Member)session.getAttribute("authentication");
		
		//비회원과, 사용자 회원인지를 판단.
		if(member == null || MemberGrade.valueOf(member.getGrade()).ROLE.equals("user")) {
			throw new HandlableException(ErrorCode.UNAUTORIZED_PAGE_ERROR);
		}
		
		//슈퍼관리자인지 판단해 슈퍼관리자라면 모든 admin페이지에 접근할 수 있다.
		if(MemberGrade.valueOf(member.getGrade()).DESC.equals("super")) {
			return;
		}
		
		switch (uriArr[2]) {
		case "member":
			if(!MemberGrade.valueOf(member.getGrade()).DESC.equals("member")) {
				throw new HandlableException(ErrorCode.UNAUTORIZED_PAGE_ERROR);
			}
			break;
		case "board":
			if(!MemberGrade.valueOf(member.getGrade()).DESC.equals("board")) {
				throw new HandlableException(ErrorCode.UNAUTORIZED_PAGE_ERROR);
			}

		default:
			break;
		}
		
	}

	private void memberAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
		HttpSession session = httpRequest.getSession();
		
		switch (uriArr[2]) {
		
		case "join-impl":
			String serverToKen = (String)session.getAttribute("persistToken");
			String clientToken = httpRequest.getParameter("persistToken");
			
			
			if(serverToKen == null || !serverToKen.equals(clientToken) ) {
				throw new HandlableException(ErrorCode.AUTENTICATION_FAILED_ERROR);
			}
			break;
		case "mypage":
			if(session.getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT.setURL("member/login-form"));
			}
			break;
	
		default:
			break;
		}


		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
