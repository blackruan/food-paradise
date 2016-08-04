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

public class SearchAction extends ActionSupport implements ServletRequestAware {

   private String searchquery;
   private boolean checkeve;
   private boolean checkres;
   private boolean checkuser;
   private HttpServletRequest httpServletRequest;
   ArrayList<restaurants> allRestaurants=new ArrayList<restaurants>();
   ArrayList<events> allEvents=new ArrayList<events>();
   ArrayList<users> allUsers=new ArrayList<users>();

	
   public String execute() {
      String ret = ERROR;
      Connection conn = null;
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpSession        session = request.getSession(false);
      System.out.println(checkeve);
      System.out.println(checkres);
      //int i=0;
      
      try {
    	  String URL = "jdbc:mysql://localhost:3306/FOOD_PARADISE";
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection(URL, "root", "password");
          
         String sql;
         PreparedStatement ps;
         ResultSet rs;
         if(checkres){
        	 //sql = ("SELECT * FROM shanghai.FOOD_RESTAURANT WHERE RESTAURANT_NAME LIKE '%"+searchquery+"%' AND ROWNUM < 10");
        	 sql = ("SELECT * FROM FOOD_RESTAURANT WHERE RESTAURANT_NAME LIKE '%" 
        			 + searchquery + "%' LIMIT 10");
        	 ps = conn.prepareStatement(sql);
             System.out.println(sql);
             /*System.out.println(sql);
             System.out.println(password);
             ps.setString(1, user);
             ps.setString(2, password);
             System.out.println("weishenme");*/
             rs = ps.executeQuery();
             //System.out.println(sql);
             while (rs.next()) {
            	 restaurants restaurant = new restaurants();
            	 restaurant.setRestaurant_id(rs.getString(1));
            	 restaurant.setRestaurant_name(rs.getString(2));
            	 restaurant.setRestaurant_address(rs.getString(3));
            	 restaurant.setRestaurant_lat(rs.getString(4));
            	 restaurant.setRestaurant_lon(rs.getString(5));
            	 restaurant.setRestaurant_rating(rs.getString(6));
            	 restaurant.setRestaurant_rating_num(rs.getString(7));
            	 allRestaurants.add(restaurant);
             }
         }
         if(checkeve){
        	 //sql = ("SELECT * FROM shanghai.FOOD_EVENT,shanghai.FOOD_RESTAURANT WHERE EVENT_NAME LIKE '%"+searchquery+"%' AND shanghai.FOOD_RESTAURANT.RESTAURANT_ID = shanghai.FOOD_EVENT.RESTAURANT_ID AND ROWNUM < 10");
        	 sql = ("SELECT * FROM FOOD_EVENT,FOOD_RESTAURANT WHERE EVENT_NAME LIKE '%" +
        			 searchquery + "%' AND FOOD_RESTAURANT.RESTAURANT_ID = FOOD_EVENT.RESTAURANT_ID "
        			 		+ "LIMIT 10");
        	 System.out.println(sql);
             ps = conn.prepareStatement(sql);

             rs = ps.executeQuery();
             //System.out.println(sql);
             while (rs.next()) {
            	 events event = new events();
            	 event.setEvent_id(rs.getString(1));
            	 event.setEvent_name(rs.getString(2));
            	 event.setRestaurant_id(rs.getString(3));
            	 event.setEvent_timestamp(rs.getString(4));
            	 event.setEvent_cost(rs.getString(5));
            	 event.setEvent_description(rs.getString(6));
            	 event.setRestaurant_name(rs.getString(8));
            	 allEvents.add(event);
             }
         }
         if(checkuser){
        	 //sql = ("SELECT USER_ID,NICKNAME,AVERAGE_RATINGS,MEMBER_SINCE FROM shanghai.FOOD_USER WHERE NICKNAME LIKE '%"+searchquery+"%' AND ROWNUM < 10");
        	 sql = ("SELECT USER_ID,NICKNAME,AVERAGE_RATINGS,MEMBER_SINCE FROM "
         	 		+ "FOOD_USER WHERE NICKNAME LIKE '%" + searchquery + "%' LIMIT 10");
        	 System.out.println(sql);
             ps = conn.prepareStatement(sql);

             rs = ps.executeQuery();
             //System.out.println(sql);
             while (rs.next()) {
            	 users user = new users();
            	 user.setUser_id(rs.getString(1));
            	 user.setUser_nickname(rs.getString(2));
            	 user.setAverage_ratings(rs.getString(3));
            	 user.setMember_since(rs.getString(4));
            	 allUsers.add(user);
             }
         }
         
         /*sql = ("SELECT * FROM shanghai.FOOD_EVENT WHERE EVENT_NAME LIKE '%"+searchquery+"'%");
         ps = conn.prepareStatement(sql);
         System.out.println(sql);
         System.out.println(password);
         ps.setString(1, user);
         ps.setString(2, password);
         System.out.println("weishenme");
         rs = ps.executeQuery();
         //System.out.println(sql);
         while (rs.next()) {
        	 events event = new events();
        	 event.setEvent_id(rs.getString(1));
        	 event.setEvent_name(rs.getString(2));
        	 event.setRestaurant_id(rs.getString(3));
        	 event.setEvent_timestamp(rs.getString(4));
        	 event.setEvent_cost(rs.getString(5));
        	 event.setEvent_description(rs.getString(6));
        	 allEvents.add(event);
         }*/
         ret = SUCCESS;
         
         
      } catch (Exception e) {
    	  System.out.println(e);
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

   public String getSearchquery() {
      return searchquery;
   }

   public void setSearchquery(String searchquery) {
      this.searchquery = searchquery;
   }
   public ArrayList<restaurants> getAllRestaurants() {  
	    return allRestaurants;  
	}  
	public void setAllRestaurants(ArrayList<restaurants> allRestaurants) {  
	    this.allRestaurants = allRestaurants;  
	}
	public ArrayList<events> getAllEvents() {  
	    return allEvents;  
	}  
	public void setAllEvents(ArrayList<events> allEvents) {  
	    this.allEvents = allEvents;  
	}
	public ArrayList<users> getAllUsers() {  
	    return allUsers;  
	}  
	public void setAllUsers(ArrayList<users> allUsers) {  
	    this.allUsers = allUsers;  
	}



@Override
public void setServletRequest(HttpServletRequest request) {
	// TODO Auto-generated method stub
	this.httpServletRequest = request;
}

public boolean isCheckeve() {
	return checkeve;
}

public void setCheckeve(boolean checkeve) {
	this.checkeve = checkeve;
}

public boolean isCheckres() {
	return checkres;
}

public void setCheckres(boolean checkres) {
	this.checkres = checkres;
}

public boolean isCheckuser() {
	return checkuser;
}

public void setCheckuser(boolean checkuser) {
	this.checkuser = checkuser;
}
}