package IA;

import game.Matriz;

public class CornerPlayer extends Player{
	
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
			this.agregar2enPeor(mNue);
		}
		int puntaje = (mNue.puntaje());
		if(numRec!=0){							//Modificador de puntaje: que tanto mas valen las jugadas mas ceranas a la inicial con respecto a las siguientes llamadas recursivas
			puntaje*=(numRec); 
		}
		int esquinada = this.esquinada(mNue);
		if(esquinada!=0){						//Mod de puntaje: que tanto vale que el mayor esta en una esquina
			puntaje*=(esquinada+1);
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
	
	/*private int puntaje(Matriz mAnt, Matriz mNue){
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
	}*/
	
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
						int move = bestScoreMove(mAux,0);		//??
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
	
	public boolean agregar2enPeor(Matriz m){
		boolean ok = false;
		for(int[] a:m.getM()){
			for(int n:a){
				if(n==0){
					ok=true;
				}
			}
		}
		if(ok){
			int min = Integer.MAX_VALUE;
			int posX = 0,posY = 0;
			for(int i=0;i<m.getS();i++){
				for(int j=0;j<m.getS();j++){
					if(m.getPos(j, i)==0){
						Matriz mAux = new Matriz(m.getM());
						mAux.setPos(j, i, 2);
						int max = 0;
						for(int k=0;k<4;k++){
							Matriz mAuxSig = new Matriz(mAux.getM());
							if(mAuxSig.mover(k)){
								int puntAct=mAuxSig.puntaje();
								int nivelEsq=this.esquinada(mAuxSig);
								if(nivelEsq!=0){
									puntAct*=nivelEsq;
								}
								else{
									puntAct/=2;
								}
								if(puntAct>=max){
									max=puntAct;
								}
							}
						}
						if(max<=min){
							min=max;
							posX=j;
							posY=i;
						}
					}
				}
			}
			m.setPos(posX, posY, 2);
		}
		return ok;
	}
	
	private int esquinada(Matriz m){
		int cant = 0;
		int[][] array = this.getMatrizAsArray(m);
		boolean ok = true;
		int tam = (m.getS()*m.getS());
		boolean primer = true;
		while(ok){
			int posMax = 0;
			int numMax = 0;
			int posAnt = 0;
			for(int i=0;i<tam;i++){
				if(array[i][0]>numMax){
					numMax = array[i][0];
					posMax = i;
				}
			}
			if(numMax == 0){
				ok = false;
			}
			else{
				array[posMax][0]=0;
				if(primer){
					if(array[posMax][1]==0){
						if((array[posMax][2]==0)||(array[posMax][2]==(m.getS()-1))){
							cant++;
						}
					}
					else{
						if(array[posMax][1]==(m.getS()-1)){
							if((array[posMax][2]==0)||(array[posMax][2]==(m.getS()-1))){
								cant++;
							}
						}
						else{
							ok = false;
						}
					}
				}
				else{
					if(array[posMax][1]==array[posAnt][1]){
						if((array[posMax][2]==(array[posAnt][2]+1))||(array[posMax][2]==(array[posAnt][2]-1))){
							cant++;
						}
					}
					else{
						if(array[posMax][2]==array[posAnt][2]){
							if((array[posMax][1]==(array[posAnt][1]+1))||(array[posMax][1]==(array[posAnt][1]-1))){
								cant++;
							}
						}
						else{
							ok = false;
						}
					}
				}
				posAnt = posMax;
			}
		}
		return cant;
	}
	
	private int getPosMayor(Matriz m) {
		int i = 0, max = 0;
		int pos = 0;
		for(int [] row : m.getM()){
			for(int n : row){
				if(n>=max){
					max=n;
					pos = i;
				}
				i++;
			}
		}
		return pos;
	}

	private int [][] getMatrizAsArray(Matriz m){
		int tam = (m.getS()*m.getS());
		int[][] mayores = new int[tam][3];
		for(int i=0;i<tam;i++){
			mayores[i][0]=0;
		}
		int k = 0;
		for(int i=0; i<m.getS(); i++){
			for(int j=0; j<m.getS(); j++){
				mayores[k][0]=m.getPos(j, i);
				mayores[k][1]=j;
				mayores[k][2]=i;
				k++;
			}
		}
		return mayores;
	}

}
