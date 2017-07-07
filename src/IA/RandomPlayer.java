package IA;

import java.util.Random;

import game.Matriz;

public class RandomPlayer extends Player{
	
	public char move(Matriz m){
		Random r = new Random();
		return super.moves[r.nextInt(4)];
	}
}