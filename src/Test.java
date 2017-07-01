import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		matriz m = new matriz();
		m.agregar2();
		m.imprimir();
		Scanner in = new Scanner(System.in);
		String as = in.nextLine();
		char a = as.charAt(0);
		boolean ok = true;
		boolean movio;
		while(ok){
			movio = m.mover(a);
			if(movio){
				ok=m.agregar2();
			}
			m.imprimir();
			as = in.nextLine();
			a = as.charAt(0);
		}
	}

}
