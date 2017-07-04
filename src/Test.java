import java.util.Scanner;

public class Test {
	
	public static void randomTest(){
		Matriz m = new Matriz(true);
		m.imprimir();
		Player p = new Player();
		char op = p.randomMove();
		while(m.jugar(op)){
			op = p.randomMove();
		}
		System.out.println("Game Over");
	}

	public static void main(String[] args) {
		Matriz m = new Matriz(true);
		m.imprimir();
		Scanner in = new Scanner(System.in);
		String cod = in.nextLine();
		char op = cod.charAt(0);
		while(m.jugar(op)){
			cod = in.nextLine();
			op = cod.charAt(0);
		}
		System.out.println("Game Over");
	}

}
