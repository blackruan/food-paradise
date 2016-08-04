package com.foodparadise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class FriendAction extends ActionSupport implements ServletRequestAware {

   private String friend;
   private HttpServletRequest httpServletRequest;

	
   public String execute() {
      String ret = ERROR;
      Connection conn = null;
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpSession        session = request.getSession(false);
      String useridtemp = (String) httpServletRequest.getSession(false).getAttribute("userid");
      String friendtemp = friend;
      
      //int i=0;
      
      try {
    	 String URL = "jdbc:mysql://localhost:3306/FOOD_PARADISE";
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(URL, "root", "password");
          
         String sql = "INSERT INTO FOOD_FRIEND (USER1_ID,USER2_ID) VALUES ";
         sql+=("('"+useridtemp+"','"+friendtemp+"')");
         PreparedStatement ps = conn.prepareStatement(sql);
         
         
         System.out.println(sql);
         if(ps.executeUpdate()!=0){
        	 ret = SUCCESS;
         }
         /*String sql = ("SELECT NICKNAME FROM shanghai.FOOD_USER WHERE USER_ID = '"+user+"' AND");
         sql+=(" USER_PASSWORD = '"+password+"' ");
         System.out.println(sql);
         PreparedStatement ps = conn.prepareStatement(sql);
         System.out.println(sql);
         System.out.println(password);
         ps.setString(1, user);
         ps.setString(2, password);
         System.out.println("weishenme");
         ResultSet rs = ps.executeQuery();
         System.out.println(sql);
         while (rs.next()) {
        	 //name = rs.getString(1);
        	 httpServletRequest.getSession(false).setAttribute("username", name);
        	 httpServletRequest.getSession(false).setAttribute("userid", user);
        	 //System.out.println(name);
        	 

            //name = rs.getString(1);
            ret = SUCCESS;
         }*/
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


@Override
public void setServletRequest(HttpServletRequest request) {
	// TODO Auto-generated method stub
	this.httpServletRequest = request;
}


public String getFriend() {
	return friend;
}


public void setFriend(String friend) {
	this.friend = friend;
}





}