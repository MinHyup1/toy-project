package com.kh.toy.common.exception;

import com.kh.toy.common.code.ErrorCode;

//예외처리 강제되지 않는 UnCheckedException
public class DataAccessException extends HandlableException{

	private static final long serialVersionUID = -3729967439819469530L;
	
	public DataAccessException(Exception e) {
		super(ErrorCode.DATABASE_ACCESS_ERROR,e);
	}

}	
