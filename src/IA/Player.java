package IA;

import game.Matriz;

public abstract class Player {
	
	char[] moves = new char[]{'s','d','w','a'};
	
	public abstract char move(Matriz m);
}
