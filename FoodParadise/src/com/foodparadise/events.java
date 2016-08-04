package com.foodparadise;

public class events {
	private String event_id;
	private String event_name;
	private String restaurant_id;
	private String restaurant_name;
	private String event_timestamp;
	private String event_cost;
	private String event_description;
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(String restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getEvent_timestamp() {
		return event_timestamp;
	}
	public void setEvent_timestamp(String event_timestamp) {
		this.event_timestamp = event_timestamp;
	}
	public String getEvent_cost() {
		return event_cost;
	}
	public void setEvent_cost(String event_cost) {
		this.event_cost = event_cost;
	}
	public String getEvent_description() {
		return event_description;
	}
	public void setEvent_description(String event_description) {
		this.event_description = event_description;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

}
