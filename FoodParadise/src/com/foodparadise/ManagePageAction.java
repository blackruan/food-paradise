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

public class ManagePageAction extends ActionSupport implements ServletRequestAware {

   private String searchquery;
   private boolean checkeve;
   private boolean checkres;
   private boolean checkuser;
   private HttpServletRequest httpServletRequest;
   ArrayList<restaurants> allRestaurants=new ArrayList<restaurants>();
   ArrayList<restaurants> allCheckinRestaurants=new ArrayList<restaurants>();
   ArrayList<events> allEvents=new ArrayList<events>();
   ArrayList<users> allUsers=new ArrayList<users>();

	
   public String execute() {
      String ret = ERROR;
      Connection conn = null;
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpSession session = request.getSession(false);
      String useridtemp = (String) httpServletRequest.getSession(false).getAttribute("userid");
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
         
    	 sql = ("SELECT * FROM FOOD_RESTAURANT,FOOD_REVIEWS WHERE FOOD_RESTAURANT.RESTAURANT_ID = FOOD_REVIEWS.RESTAURANT_ID AND FOOD_REVIEWS.USER_ID = '"+useridtemp+"'");
    	 System.out.println(sql);
    	 ps = conn.prepareStatement(sql);
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
         
         
         sql = ("SELECT * FROM FOOD_RESTAURANT R,(SELECT FOOD_CHECKIN.USER_ID AS USER_ID,FOOD_CHECKIN.RESTAURANT_ID AS RESTAURANT_ID, COUNT(*) AS CHECKNUM FROM FOOD_CHECKIN WHERE FOOD_CHECKIN.USER_ID = '"+useridtemp+"' GROUP BY FOOD_CHECKIN.USER_ID, FOOD_CHECKIN.RESTAURANT_ID) C WHERE R.RESTAURANT_ID = C.RESTAURANT_ID");
         System.out.println(sql);
         ps = conn.prepareStatement(sql);
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
        	 restaurant.setRestaurant_checkin_num(rs.getString(10));
        	 allCheckinRestaurants.add(restaurant);
         }
         

    	 sql = ("SELECT * FROM FOOD_EVENT,FOOD_RESTAURANT,FOOD_ATTENDS WHERE FOOD_RESTAURANT.RESTAURANT_ID = FOOD_EVENT.RESTAURANT_ID AND FOOD_EVENT.EVENT_ID = FOOD_ATTENDS.EVENT_ID AND FOOD_ATTENDS.USER_ID = '"+useridtemp+"'");
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
    

    	 sql = ("SELECT U.USER_ID,U.NICKNAME,U.AVERAGE_RATINGS,U.MEMBER_SINCE FROM FOOD_USER U,((SELECT USER1_ID AS USER_ID FROM FOOD_FRIEND WHERE FOOD_FRIEND.USER2_ID = '"+useridtemp+"') UNION (SELECT USER2_ID AS USER_ID FROM FOOD_FRIEND WHERE FOOD_FRIEND.USER1_ID = '"+useridtemp+"')) F WHERE F.USER_ID = U.USER_ID");
    	
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
	
	public ArrayList<restaurants> getAllCheckinRestaurants() {  
	    return allCheckinRestaurants;  
	}  
	public void setAllCheckinRestaurants(ArrayList<restaurants> allCheckinRestaurants) {  
	    this.allCheckinRestaurants = allCheckinRestaurants;  
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