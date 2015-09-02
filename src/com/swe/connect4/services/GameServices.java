package com.swe.connect4.services;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.appengine.labs.repackaged.org.json.JSONString;
import com.swe.connect4.entities.GameEntity;
import com.swe.connect4.models.Game;
import com.swe.connect4.models.User;

@Path("/game")
@Produces(MediaType.TEXT_PLAIN)

public class GameServices {
	@POST
	@Path("/CreateNewGame")
	public String createNewGame(@FormParam("uname") String uname) {
		Game game = new Game(User.getActiveUser().getName()); 
		JSONString gameData = GameEntity.saveGameData(game);
		return gameData.toString();
	}
	
	@POST
	@Path("/ShowAvilableGames")
	public String showAvilableGames() {
		JSONArray gameData = GameEntity.getAvilableGames() ; //de et3mlt fe el database
		return gameData.toJSONString();
	}

	@POST
	@Path("/JoinAgame") // "" eli bdeha el value f el front end
	public String joinAgame(@FormParam("gameID") int ID ,@FormParam("playerName") String player
			,@FormParam("ownerName") String owner) {
		Game game = new Game(owner,player,ID);
		String gameData = GameEntity.joinedData(game);
		return gameData;
	}
	
}
