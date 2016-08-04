package com.foodparadise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements ServletRequestAware {


   private HttpServletRequest httpServletRequest;

	
   public String execute() {
      String ret = ERROR;
      Connection conn = null;
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpSession        session = request.getSession(false);
      httpServletRequest.getSession(false).removeAttribute("username");
      httpServletRequest.getSession(false).removeAttribute("userid");
      //int i=0;
      ret = SUCCESS;
      
     
      return ret;
   }


@Override
public void setServletRequest(HttpServletRequest request) {
	// TODO Auto-generated method stub
	this.httpServletRequest = request;
}
}