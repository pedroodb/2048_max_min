import java.util.Random;

public class Player {
	
	/*private int puntuar(Matriz m){
		int[][] orden = new int[16][3];
		int k=0;
		for(int i=0;i<m.getS();i++){
			for(int j=0;j<m.getS();j++){
				if(m.getPos(j, i)!=0){
					orden[k][0]=m.getPos(j, i);
					orden[k][1]=j;
					orden[k][2]=i;
				}
			}
		}
	}*/
	
	public char randomMove(){
		Random r = new Random();
		int move = r.nextInt(4);
		char op='a';
		switch(move){
		case 0: op='a';
		break;
		case 1: op='w';
		break;
		case 2: op='d';
		break;
		case 3: op='s';
		break;
		}
		return op;
	}
}
