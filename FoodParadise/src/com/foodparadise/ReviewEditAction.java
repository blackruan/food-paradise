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

public class ReviewEditAction extends ActionSupport implements ServletRequestAware {

   private String ratings;
   private String comments;
   private String restaurant_id;
   private HttpServletRequest httpServletRequest;

	
   public String execute() {
      String ret = ERROR;
      Connection conn = null;
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpSession        session = request.getSession(false);
      String useridtemp = (String) httpServletRequest.getSession(false).getAttribute("userid");
      String restauranttemp = restaurant_id;
      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date date = new Date();
      double singlerating=0;
      double totalrating=0;
      double totalnum=0;
      
      //int i=0;
      
      try {
    	  String URL = "jdbc:mysql://localhost:3306/FOOD_PARADISE";
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection(URL, "root", "password");
         
         //String sql = ("SELECT * FROM shanghai.FOOD_RESTAURANT WHERE RESTAURANT_ID = '"+restauranttemp+"'");

          String sql = ("SELECT * FROM FOOD_RESTAURANT WHERE RESTAURANT_ID = '" + 
         		 restauranttemp + "'");
          PreparedStatement ps = conn.prepareStatement(sql);
         System.out.println(sql);
         ResultSet rs = ps.executeQuery();
         if(rs.next()){
        	 if(rs.getString(7)==null){
        		 totalrating = 0;
            	 totalnum = 0;
        	 }else{
        		 totalrating = Double.parseDouble(rs.getString(6));
        		 totalnum = Double.parseDouble(rs.getString(7));
        	 }
        	 
        	 
         }
         
         //sql = ("SELECT * FROM shanghai.FOOD_REVIEWS WHERE RESTAURANT_ID = '"+restauranttemp+"' AND USER_ID = '"+useridtemp+"'");
         sql = ("SELECT * FROM FOOD_REVIEWS WHERE RESTAURANT_ID = '" + 
        		 restauranttemp + "' AND USER_ID = '" +  useridtemp + "'");
         ps = conn.prepareStatement(sql);
         System.out.println(sql);
         rs = ps.executeQuery();
         
         if(rs.next()) {
        	 singlerating = Double.parseDouble(rs.getString(3));
        	 //sql = ("UPDATE shanghai.FOOD_REVIEWS SET RATINGS = "+ratings+" WHERE RESTAURANT_ID = '"+restauranttemp+"' AND USER_ID = '"+useridtemp+"'");
        	 sql = ("UPDATE FOOD_REVIEWS SET RATINGS = " + ratings + 
        			 " WHERE RESTAURANT_ID = '" + restauranttemp + 
        			 "' AND USER_ID = '" + useridtemp + "'");
        	 ps = conn.prepareStatement(sql);
             System.out.println(sql);
             ps.executeUpdate();
             
             totalrating = (totalrating*totalnum+Double.parseDouble(ratings)-singlerating)/totalnum;
             //sql = ("UPDATE shanghai.FOOD_RESTAURANT SET RESTAURANT_RATING = '"+totalrating+"' WHERE RESTAURANT_ID = '"+restauranttemp+"'");
             sql = ("UPDATE FOOD_RESTAURANT SET RESTAURANT_RATING = '" + totalrating 
            		 + "' WHERE RESTAURANT_ID = '" + restauranttemp + "'");
             ps = conn.prepareStatement(sql);
             System.out.println(sql);
             ps.executeUpdate();
        	 
         }else{
        	 //sql = ("INSERT INTO shanghai.FOOD_REVIEWS (USER_ID,RESTAURANT_ID,RATINGS,REVIEW_DATE,COMMENTS) VALUES ");
        	 //sql += ("('"+useridtemp+"','"+restauranttemp+"','"+ratings+"',TO_DATE('"+dateFormat.format(date)+"','yyyy/mm/dd hh24:mi:ss'),'"+comments+"')");
        	 sql = ("INSERT INTO FOOD_REVIEWS (USER_ID,RESTAURANT_ID,RATINGS,REVIEW_DATE,COMMENTS) VALUES ");
        	 sql += ("('" + useridtemp + "','" + restauranttemp + "','" + ratings 
        			 + "','" + dateFormat.format(date) + "','" + comments + "')");
        	 ps = conn.prepareStatement(sql);
             System.out.println(sql);
             ps.executeUpdate();
             
             totalrating = (totalrating*totalnum+Double.parseDouble(ratings))/(totalnum+1);
             totalnum +=1;
             //sql = ("UPDATE shanghai.FOOD_RESTAURANT SET RESTAURANT_RATING = '"+totalrating+"', RESTAURANT_RATING_NUM = '"+totalnum+"' WHERE RESTAURANT_ID = '"+restauranttemp+"'");
             sql = ("UPDATE FOOD_RESTAURANT SET RESTAURANT_RATING = '" + totalrating 
            		 + "', RESTAURANT_RATING_NUM = '" + totalnum + 
            		 "' WHERE RESTAURANT_ID = '" + restauranttemp + "'");
             ps = conn.prepareStatement(sql);
             System.out.println(sql);
             ps.executeUpdate();
         }
         ret = SUCCESS;
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


public String getRatings() {
	return ratings;
}


public void setRatings(String ratings) {
	this.ratings = ratings;
}


public String getComments() {
	return comments;
}


public void setComments(String comments) {
	this.comments = comments;
}


public String getRestaurant_id() {
	return restaurant_id;
}


public void setRestaurant_id(String restaurant_id) {
	this.restaurant_id = restaurant_id;
}
}