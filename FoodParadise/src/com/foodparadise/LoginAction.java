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

public class LoginAction extends ActionSupport implements ServletRequestAware {

   private String user;
   private String password;
   private String name;
   private HttpServletRequest httpServletRequest;
	
   public String execute() {
      String ret = ERROR;
      Connection conn = null;
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpSession        session = request.getSession(false);
      //int i=0;
      
      try {
    	 String URL = "jdbc:mysql://localhost:3306/FOOD_PARADISE";
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(URL, "root", "password");
          
         String sql = ("SELECT NICKNAME FROM FOOD_USER WHERE USER_ID = '"+user+"' AND");
         sql+=(" USER_PASSWORD = '"+password+"' ");
         System.out.println(sql);
         PreparedStatement ps = conn.prepareStatement(sql);
         /*System.out.println(sql);
         System.out.println(password);
         ps.setString(1, user);
         ps.setString(2, password);
         System.out.println("weishenme");*/
         ResultSet rs = ps.executeQuery();
         System.out.println(sql);
         while (rs.next()) {
        	 name = rs.getString(1);
        	 httpServletRequest.getSession(false).setAttribute("username", name);
        	 httpServletRequest.getSession(false).setAttribute("userid", user);
        	 //System.out.println(name);
        	 

            //name = rs.getString(1);
            ret = SUCCESS;
         }
      } catch (Exception e) {
         ret = ERROR;
      } finally {
         if (conn != null) {
            try {
               conn.close();
            } catch (Exception e) {
            }
         }
      }
      return ret;
   }

   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

@Override
public void setServletRequest(HttpServletRequest request) {
	// TODO Auto-generated method stub
	this.httpServletRequest = request;
}
}