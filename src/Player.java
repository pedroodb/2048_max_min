import java.util.Random;

public class Player {
	
	char[] moves = new char[]{'s','d','w','a'};
	
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
	
	public char scoreMove(Matriz m){
		int max = 0;
		int maxMove = -1;
		for (int i=0;i<m.getS();i++){
			Matriz mAux = new Matriz(m.getM());
			mAux.mover(i);
			mAux.agregar2();
			int score = scoreMoveMinMax(m,mAux,5);
			if(!mAux.igual(m)){
				if(score>=max){
					max=score;
					maxMove=i;
				}
			}
		}
		return moves[maxMove];
	}
	
	private int scoreMoveMinMax(Matriz mAnt, Matriz mNue, int numRec) {
		Matriz mAux = new Matriz(mNue.getM());
		for(int i = 0; i<mAnt.getS(); i++){
			for(int j = 0; j<mAnt.getS(); j++){
				if(mAnt.getPos(j, i)!=0){
					delete(mAux,mAnt.getPos(j, i));
				}
			}
		}
		int puntaje = 0;
		for(int i = 0; i<mAux.getS(); i++){
			for(int j = 0; j<mAux.getS(); j++){
				if(mAux.getPos(j, i)!=0){
					puntaje+=mAux.getPos(j, i);
				}
			}
		}
		puntaje*=(numRec); 
		int max = 0;
		if(numRec>=0){
			for (int i=0;i<mNue.getS();i++){
				mAux = new Matriz(mNue.getM());
				mAux.mover(i);
				mAux.agregar2();
				int score = scoreMoveMinMax(mNue,mAux,(numRec-1));
				if(score>=max){
					max=score;
				}
			}
		}
		puntaje+=max;
		return puntaje;
	}
	
	private boolean delete(Matriz m, int n){
		boolean ok = false;
		for(int i = 0; i<m.getS(); i++){
			for(int j = 0; j<m.getS(); j++){
				if(!ok){
					if(m.getPos(j, i)==n){
						m.setPos(j, i, 0);
						ok = true;
					}
				}
			}
		}
		return ok;
	}

	public char randomMove(){
		Random r = new Random();
		return moves[r.nextInt(4)];
	}
}
