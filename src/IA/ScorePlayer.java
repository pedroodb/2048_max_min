package IA;

import game.Matriz;

public class ScorePlayer extends Player{
	
	public char move(Matriz m){
		return moves[bestScoreMove(m,5)];
	}
	
	private int bestScoreMove(Matriz m, int cantRec){
		int max = 0;
		int maxMove = -1;
		for (int i=0;i<m.getS();i++){
			Matriz mAux = new Matriz(m.getM());
			mAux.mover(i);
			int score = scoreMoveMinMax(new Matriz(m.getM()),new Matriz(mAux.getM()),cantRec);
			if(!mAux.igual(m)){
				if(score>=max){
					max=score;
					maxMove=i;
				}
			}
		}
		return maxMove;
	}
	
	private int scoreMoveMinMax(Matriz mAnt, Matriz mNue, int numRec) {
		if(numRec>0){
			this.agregar2enMasProbable(mNue);
		}
		int puntaje = this.puntaje(mAnt, mNue);
		if(numRec!=0){									//Modificador de puntaje: que tanto mas valen las jugadas mas ceranas a la inicial con respecto a las siguientes llamadas recursivas
			puntaje*=(numRec); 
		}
		int max = 0;
		if(numRec>0){
			for (int i=0;i<mNue.getS();i++){
				Matriz mAux = new Matriz(mNue.getM());
				mAux.mover(i);
				int score = scoreMoveMinMax(mNue,mAux,(numRec-1));
				if(score>=max){
					max=score;
				}
			}
		}
		puntaje+=max;
		return puntaje;
	}
	
	private int puntaje(Matriz mAnt, Matriz mNue){
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

	private boolean agregar2enMasProbable(Matriz m){
		boolean ok = false;
		for(int[] a:m.getM()){
			for(int n:a){
				if(n==0){
					ok=true;
				}
			}
		}
		if(ok){
			int [] cantMoves = new int[4];
			for(int i=0;i<4;i++){
				cantMoves[i]=0;
			}
			int max = 0;
			int posX = 0,posY = 0;
			for(int i=0;i<m.getS();i++){
				for(int j=0;j<m.getS();j++){
					if(m.getPos(j, i)==0){
						Matriz mAux = new Matriz(m.getM());
						mAux.setPos(j, i, 2);
						int move = bestScoreMove(m,0);
						cantMoves[move]++;
						if(cantMoves[move]>=max){
							max = cantMoves[move];
							posX = j;
							posY = i;							
						}
					}
				}
			}
			m.setPos(posX, posY, 2);
		}
		return ok;
	}
}
