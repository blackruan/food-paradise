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

public class ReviewAction extends ActionSupport implements ServletRequestAware {

   private String review;
   private HttpServletRequest httpServletRequest;
   ArrayList<reviews> singleReviews=new ArrayList<reviews>();

	
   public String execute() {
      String ret = ERROR;
      Connection conn = null;
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpSession        session = request.getSession(false);
      String useridtemp = (String) httpServletRequest.getSession(false).getAttribute("userid");
      String reviewtemp = review;
      String review_id = null;
      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Date date = new Date();
      
      //int i=0;
      
      try {
    	 String URL = "jdbc:mysql://localhost:3306/FOOD_PARADISE";
         Class.forName("com.mysql.jdbc.Driver");
         conn = DriverManager.getConnection(URL, "root", "password");
          
         //String sql = ("SELECT * FROM FOOD_REVIEWS,FOOD_RESTAURANT WHERE FOOD_REVIEWS.USER_ID = '"+useridtemp+"' AND shanghai.FOOD_REVIEWS.RESTAURANT_ID = shanghai.FOOD_RESTAURANT.RESTAURANT_ID AND shanghai.FOOD_REVIEWS.RESTAURANT_ID = '"+reviewtemp+"'");
         String sql = ("SELECT * FROM FOOD_REVIEWS,FOOD_RESTAURANT "
          		+ "WHERE FOOD_REVIEWS.USER_ID = '" + useridtemp + 
          		"' AND FOOD_REVIEWS.RESTAURANT_ID = FOOD_RESTAURANT.RESTAURANT_ID "
          		+ "AND FOOD_REVIEWS.RESTAURANT_ID = '" + reviewtemp + "'");
         PreparedStatement ps = conn.prepareStatement(sql);
         System.out.println(sql);
         ResultSet rs = ps.executeQuery();
         
         if(rs.next()) {
        	 reviews singleReview = new reviews();
        	 singleReview.setUser_id(rs.getString(1)); 
        	 singleReview.setRestaurant_id(rs.getString(2));
        	 singleReview.setRatings(rs.getString(3));
        	 singleReview.setDate(rs.getString(4));
        	 singleReview.setComments(rs.getString(5));
        	 singleReview.setRestaurant_name(rs.getString(7));
        	 singleReviews.add(singleReview);
         }else{
        	 reviews singleReview = new reviews();
        	//sql = ("SELECT * FROM shanghai.FOOD_RESTAURANT WHERE shanghai.FOOD_RESTAURANT.RESTAURANT_ID = '"+reviewtemp+"'");
        	 sql = ("SELECT * FROM FOOD_RESTAURANT WHERE "
         			+ "FOOD_RESTAURANT.RESTAURANT_ID = '" + reviewtemp + "'");
        	 ps = conn.prepareStatement(sql);
             System.out.println(sql);
             rs = ps.executeQuery();
             if(rs.next()) {
            	 singleReview.setRestaurant_name(rs.getString(2));
             }
             
        	 singleReview.setUser_id(useridtemp); 
        	 singleReview.setRestaurant_id(reviewtemp);
        	 singleReviews.add(singleReview);
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

public ArrayList<reviews> getSingleReviews() {
	return singleReviews;
}


public void setSingleReviews(ArrayList<reviews> singleReviews) {
	this.singleReviews = singleReviews;
}

public String getReview() {
	return review;
}


public void setReview(String review) {
	this.review = review;
}
}