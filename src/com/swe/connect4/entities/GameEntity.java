package com.swe.connect4.entities;

import com.google.appengine.labs.repackaged.org.json.JSONString;
import com.swe.connect4.models.Game;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
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


public class GameEntity {

	public GameEntity(String uname) {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public static JSONString saveGameData(Game game) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Transaction txn = datastore.beginTransaction();
		Query gaeQuery = new Query("availableGames");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults()); //3shan ygeb yshof el rows 2ad eh
		//3shan y3ml save fe el a5er 3la tool 
		//byst5dmha 3shan yzwd el size bta3 el table 
		System.out.println("Size = " + list.size());
			
		try {
		Entity gamedata = new Entity("availableGames", list.size() + 2); //low +1 sa3at bybdl row mkan row fa 2
        // el entity de bt2ra mn el games table da
		gamedata.setProperty("owner", game.getOwner());  //bta5od esm el column fe el database w el esm el tani el value eli httsyf fe el column da
		gamedata.setProperty("status", "avilable");
		datastore.put(gamedata);
		txn.commit();     
		}finally{
			if (txn.isActive()) {
		        txn.rollback();   
		    }
		}
		
		int gameId=0;
		txn = datastore.beginTransaction();  //3shan yrga3 ykra2 mn el awl w yft7 ell table w ykr2 mn el list kol entity
		gaeQuery = new Query("availableGames");
		pq = datastore.prepare(gaeQuery);
		    for (Entity entity : pq.asIterable()) {
		     	gameId = (int) entity.getKey().getId();  // hya eli btgeb el id mn el datastore
		}
		JSONObject object = new JSONObject(); //B3ml json object w bn7ot value feeh
		object.put("owner", game.getOwner());
		object.put("gameID", gameId);
		return (JSONString) object;	
	}

	
	public static JSONArray getAvilableGames() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		JSONArray requests = new JSONArray();

		Query gaeQuery = new Query("availableGames");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {  // bta5od kol row w tkr2o
			entity.getProperty("owner").toString();
			int gameId = (int) entity.getKey().getId(); 
				JSONObject object = new JSONObject();
				String userName = entity.getProperty("owner").toString();
				object.put("owner", userName);
				object.put("gameID", userName);
				requests.add(object);
		}
		return requests;
	}
	
	@SuppressWarnings("unchecked")       
	public static String joinedData(Game game) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		JSONObject requests = new JSONObject();
		Query gaeQuery = new Query("availableGames");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("owner").toString().equals(game.getOwner())
					&& entity.getProperty("status").toString()
							.equals("avilable")) {
				entity.setProperty("status","joined");
				entity.setProperty("player",game.getPlayer());
			}
		}
        requests.put("owner", game.getOwner());
        requests.put("player", game.getPlayer());
        requests.put("gameID", game.getgameID());
		return requests.toJSONString();
}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////