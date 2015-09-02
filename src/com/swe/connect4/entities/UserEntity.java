package com.swe.connect4.entities;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.labs.repackaged.org.json.JSONString;
import com.swe.connect4.models.User;

public class UserEntity {

	public static boolean checkUserData (String UserName , String password){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("name").toString().equals(UserName)
					&& entity.getProperty("password").toString().equals(password)) {
				
		User.getInstance(entity.getProperty(
						"name").toString(), entity.getProperty("password").toString()
						, entity.getProperty("gender").toString(), entity.getProperty("email")
						.toString());
				return true;
			}
		}
		return false;
	}
	
}
