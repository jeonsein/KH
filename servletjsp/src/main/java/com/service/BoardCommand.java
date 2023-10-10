package com.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardCommand {

	public void execute(HttpServletRequest req, HttpServletResponse res) 
			throws NamingException;
	
} // end interface
