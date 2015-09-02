package com.swe.connect4.models;

public class Game {
	
	private int gameID;
	private String owner;
	private String player;

	
	public Game()
	{
		
	}
	
	public Game(String owner)
	{
		this.owner = owner;
	} 
	
	public Game(String player1 , String player2 , int ID)
	{
		this.owner = player1;
		this.gameID = ID;
		this.player = player2;
	}
	
	
	public String getOwner()
	{
		return owner;
		
	}
	
	public String getPlayer()
	{
		return player;
		
	}
	
	public int getgameID()
	{
		return gameID;
		
	}
}
