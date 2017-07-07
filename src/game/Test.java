package game;
import java.util.Scanner;

import IA.Player;
import IA.ScorePlayer;
import IA.CornerPlayer;
import IA.RandomPlayer;

public class Test {
	
	public static void AItest(Player p){
		Matriz m = new Matriz(true);
		m.imprimir();
		char op = p.move(m);
		while(m.jugar(op)){
			op = p.move(m);
			m.imprimir();
		}
		System.out.println("Game Over");
	}
	
	public static void uPlay(){
		Matriz m = new Matriz(true);
		m.imprimir();
		Scanner in = new Scanner(System.in);
		String cod = in.nextLine();
		char op = cod.charAt(0);
		while(m.jugar(op)){
			m.imprimir();
			cod = in.nextLine();
			op = cod.charAt(0);
		}
		System.out.println("Game Over");
	}
	
	public static void uPlayEvil(){
		Matriz m = new Matriz(true);
		m.imprimir();
		Scanner in = new Scanner(System.in);
		String cod = in.nextLine();
		char op = cod.charAt(0);
		while(m.jugarEvil(op)){
			m.imprimir();
			cod = in.nextLine();
			op = cod.charAt(0);
		}
		System.out.println("Game Over");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("1) Play");
		System.out.println("2) AutoPlay");
		int op = in.nextInt();
		switch(op){
		case 1:
			System.out.println(" ");
			System.out.println("1) 2048");
			System.out.println("2) Evil 2048");
			op = in.nextInt();
			switch(op){
			case 1:
				uPlay();
				break;
			case 2:
				uPlayEvil();
				break;
			}
			break;
		case 2:
			System.out.println(" ");
			System.out.println("1) Random");
			System.out.println("2) Score-Based");
			System.out.println("3) Corner-Based");
			op= in.nextInt();
			Player p = null;
			switch(op){
			case 1:
				p = new RandomPlayer();
				break;
			case 2:
				p = new ScorePlayer();
				break;
			case 3:
				p = new CornerPlayer();
			}
			AItest(p);
			break;
		}
	}

}
