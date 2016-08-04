package com.foodparadise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class SignupAction extends ActionSupport implements ServletRequestAware {

   private String user;
   private String password;
   private String name;
   private HttpServletRequest httpServletRequest;

   public String execute() {
      String ret = ERROR;
      Connection conn = null;
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpSession session = request.getSession(false);
      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date date = new Date();
      //int i=0;
      
      try {
    	  String URL = "jdbc:mysql://localhost:3306/FOOD_PARADISE";
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection(URL, "root", "password");
          
         //String sql = "INSERT INTO shanghai.FOOD_USER (USER_ID,USER_PASSWORD,NICKNAME,MEMBER_SINCE) VALUES ";
         //sql+=("('"+user+"','"+password+"','"+name+"',TO_DATE('"+dateFormat.format(date)+"','yyyy/mm/dd hh24:mi:ss'))");
         

          String sql = "INSERT INTO FOOD_USER (USER_ID,USER_PASSWORD,NICKNAME,MEMBER_SINCE) VALUES ";
          sql+=( "('" + user + "','" + password + "','" + name + "','" + dateFormat.format(date) + "')");
          PreparedStatement ps = conn.prepareStatement(sql);
         /*System.out.println(sql);
         System.out.println(password);
         ps.setString(1, user);
         ps.setString(2, password);
         System.out.println("weishenme");*/
         System.out.println(name);
         System.out.println(sql);
         
         //ResultSet rs = ps.executeQuery();
         //System.out.println(rs);
//         if(ps.executeUpdate()==0){
//        	 httpServletRequest.getSession(false).setAttribute("errorsignup", "error");
//        	 ret = ERROR;
//         }else{
//        	 httpServletRequest.getSession(false).setAttribute("username", name);
//        	 httpServletRequest.getSession(false).setAttribute("userid", user);
//        	 httpServletRequest.getSession(false).removeAttribute("errorsignup");
//        	 ret = SUCCESS;
//         }
         
         ps.executeUpdate();
         
    	 httpServletRequest.getSession(false).setAttribute("username", name);
    	 httpServletRequest.getSession(false).setAttribute("userid", user);
    	 httpServletRequest.getSession(false).removeAttribute("errorsignup");
    	 ret = SUCCESS;

      } catch (Exception e) {
     	 httpServletRequest.getSession(false).setAttribute("errorsignup", "error");
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

   public String getNickname() {
      return name;
   }

   public void setNickname(String name) {
      this.name = name;
   }

@Override
public void setServletRequest(HttpServletRequest request) {
	// TODO Auto-generated method stub
	this.httpServletRequest = request;
}
}