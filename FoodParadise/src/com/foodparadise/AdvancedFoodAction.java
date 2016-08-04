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

public class AdvancedFoodAction extends ActionSupport implements ServletRequestAware {

   private String searchquery;
   
   private HttpServletRequest httpServletRequest;
   ArrayList<restaurants> allRestaurants=new ArrayList<restaurants>();
   ArrayList<restaurants> allCheckinRestaurants=new ArrayList<restaurants>();
   ArrayList<events> allEvents=new ArrayList<events>();
   ArrayList<users> allUsers=new ArrayList<users>();

	
   public String execute() {
      String ret = ERROR;
      Connection conn = null;
      HttpServletRequest request = ServletActionContext.getRequest();
      HttpSession        session = request.getSession(false);
      String useridtemp = (String) httpServletRequest.getSession(false).getAttribute("userid");

      //int i=0;
      
      try {
    	  String URL = "jdbc:mysql://localhost:3306/FOOD_PARADISE";
          Class.forName("com.mysql.jdbc.Driver");
          conn = DriverManager.getConnection(URL, "root", "password");
          
         String sql;
         PreparedStatement ps;
         ResultSet rs;

     	 sql = ("SELECT DISTINCT R.RESTAURANT_ID,R.RESTAURANT_NAME,R.RESTAURANT_ADDRESS,R.RESTAURANT_LAT,R.RESTAURANT_LON,R.RESTAURANT_RATING,R.RESTAURANT_RATING_NUM FROM FOOD_RESTAURANT R,FOOD_OWNS O WHERE O.FOOD_NAME LIKE '%"+searchquery+"%' AND R.RESTAURANT_ID = O.RESTAURANT_ID AND ROWNUM < 10");
    	
    	 System.out.println(sql);
         ps = conn.prepareStatement(sql);

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


}