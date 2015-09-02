package com.swe.connect4.models;

import com.swe.connect4.entities.UserEntity;

public class User {
	
	private  String name , password ,gender , email ;
	private static User user;
	
	private User()
	{
		
	}
	
	private User(String name, String password , String gender ,String email)
	{
		this.name=name;
		this.password=password;
		this.gender=gender;
		this.email=email;
	}

	public static User getInstance(String name , String password , String gender , String email)
	{
		if (user == null)
		{ 	
		   user = new User(name,password,gender,email);
		   return user;
		}
		else
			return user;
		}
	public String getName()
	{
		return name;
	}
	
	public String getGender()
	{
		return gender;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public static User getActiveUser()
	{
		if (user!= null)
			return user;
		return null;
	}
}
