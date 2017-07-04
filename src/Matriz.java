import java.util.Arrays;
import java.util.Random;

public class Matriz {
	int s = 4;
	int[][] m = new int[s][s];

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public Matriz() {							//Genera una matriz en 0
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				m[i][j] = 0;
			}
		}
	}

	public Matriz(int[][] m) {					//Clona la matriz pasada como parametro
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				this.m[i][j]=m[i][j];
			}
		}
	}
	
	public Matriz(boolean ini){					//Genera una matriz inicializada para jugar(con un 2 en un lugar random)
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				m[i][j] = 0;
			}
		}
		this.agregar2();
	}
	
	public int getPos(int x,int y){
		return m[x][y];
	}
	
	public void setPos(int x,int y,int n){
		this.m[x][y]=n;
	}

	private boolean agregar2(){
		boolean ok = false;
		for(int[] a:this.m){
			for(int n:a){
				if(n==0){
					ok=true;
				}
			}
		}
		if(ok){
			Random r = new Random();
			int x =r.nextInt(4);
			int y =r.nextInt(4);
			while(this.m[x][y]!=0){
				x =r.nextInt(4);
				y =r.nextInt(4);
			}
			this.m[x][y]=2;
		}
		return ok;
	}
	
	public void imprimir() {
		for (int[] c : this.m) {
			System.out.println("-------------------------");
			for (int n : c) {
				if(n==0){
					System.out.print("|     ");
				}
				else{
					if(n<10){
						System.out.print("|  "+n+"  ");
					}
					else{
						if(n<100){
							System.out.print("|  "+n+" ");
						}
						else{
							if(n<1000){
								System.out.print("| "+n+" ");
							}
							else{
								System.out.print("| "+n);
							}
						}
					}
				}
			}
			System.out.println("|");
		}
		System.out.println("-------------------------");
	}

	public int[][] getM() {
		return m;
	}

	public void setM(int[][] m) {
		this.m = m;
	}
	
	private void rotarDerecha(){
		Matriz rot = new Matriz();
		for (int i = 0; i < this.s; ++i) {
	        for (int j = 0; j < this.s; ++j) {
	            rot.setPos(i, j, this.m[this.s - j - 1][i]);
	        }
	    }
		this.setM(rot.getM());
	}

	private void desplazarElemDerecha(int i, int f, boolean[] mod) {
		if(this.getPos(i, f)!=0){
			boolean ok = true;
			while(i<(s-1) && ok){
				if(this.m[i+1][f]==0){
					this.m[i+1][f]=this.m[i][f];
					this.m[i][f]=0;
				}
				else{
					if(this.m[i+1][f]==this.m[i][f]){
						if(mod[i+1]){
							this.m[i+1][f]*=2;
							this.m[i][f]=0;
							mod[i+1]=false;
							ok=false;
						}
					}
				}
				i++;
			}
		}
	}
	
	private void desplazarFilaDer(int f){
		boolean [] mod = new boolean[this.s];
		for(int i=0;i<this.s;i++){
			mod[i]=true;
		}
		for(int i=(this.getS()-1);i>=0;i--){
			desplazarElemDerecha(i,f,mod);
		}
	}
	
	private void moverDerecha(){
		for(int i=0;i<this.s;i++){
			this.desplazarFilaDer(i);
		}
	}
	
	private boolean mover(int dir){
		Matriz mAux = new Matriz(this.getM());
		for(int i=0;i<dir;i++){
			this.rotarDerecha();
		}
		this.moverDerecha();
		for(int i=0;i<(4-dir);i++){
			this.rotarDerecha();
		}
		return(!this.igual(mAux));
	}

	public boolean igual(Matriz m) {
		boolean ok = true;
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				if(this.getPos(i, j) != m.getPos(i, j)){
					ok = false;
				}
			}
		}
		return ok;
	}

	private boolean gameOver(){
		boolean gO = true;
		for(int i=0;i<this.s;i++){
			Matriz mAux = new Matriz(this.getM());
			mAux.mover(i);
			if(!this.igual(mAux)){
				gO=false;
			}
		}
		return gO;
	}
	
	public boolean jugar(char op){
		int cod=-1;
		switch(op){
			case 'w': cod=2;
			break;
			case 'a': cod=3;
			break;
			case 's': cod=0;
			break;
			case 'd': cod=1;
			break;
		}
		if(cod!=-1){
			if(mover(cod)){
				this.agregar2();
			}
			this.imprimir();
		}
		else{
			System.out.println("Entrada Invalida");
		}
		return !this.gameOver();
	}
	
	/*public boolean mover(char dir) {
		int[][] mAux = new int[s][s];
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				mAux[i][j]=this.m[i][j];
			}
		}
		switch (dir) {
		case 's':
			for (int i = 0; i < s; i++) {
				for (int j = s-1; j >= 0; j--) {
					desplazar(j, i, dir);
				}
			}
			break;
		case 'a':
			for (int i = 0; i < s; i++) {
				for (int j = 0; j < s; j++) {
					desplazar(i, j, dir);
				}
			}
			break;
		case 'w':
			for (int i = 0; i < s; i++) {
				for (int j = 0; j < s; j++) {
					desplazar(j, i, dir);
				}
			}
			break;
		case 'd':
			for (int i = 0; i < s; i++) {
				for (int j = s-1; j >= 0; j--) {
					desplazar(i, j, dir);
				}
			}
			break;
		}
		boolean movio = false;
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				if(m[i][j] != mAux[i][j]){
					movio = true;
				}
			}
		}
		if(movio){
			return true;
		}
		else {
			return false;
		}
	}

	private void desplazar(int X, int Y, char dir) {
		if (m[X][Y] != 0) {
			boolean ok;
			switch (dir) {
			case 's':
				ok = true;
				while (X < s-1 && ok) {
					if (m[X + 1][Y] == 0) {
						X +=1;
						m[X][Y] = m[X - 1][Y];
						m[X - 1][Y] = 0;
					} else {
						if (m[X + 1][Y] == m[X][Y]) {
							m[X + 1][Y] *= 2;
							m[X][Y] = 0;
						}
						ok = false;
					}
				}
				break;
			case 'a':
				ok = true;
				while (Y > 0 && ok) {
					if (m[X][Y - 1] == 0) {
						Y -=1;
						m[X][Y] = m[X][Y + 1];
						m[X][Y + 1] = 0;
					} else {
						if (m[X][Y - 1] == m[X][Y]) {
							m[X][Y - 1] *= 2;
							m[X][Y] = 0;
						}
						ok = false;
					}
				}
				break;
			case 'w':
				ok = true;
				while (X > 0 && ok) {
					if (m[X - 1][Y] == 0) {
						X -=1;
						m[X][Y] = m[X + 1][Y];
						m[X + 1][Y] = 0;
					} else {
						if (m[X - 1][Y] == m[X][Y]) {
							m[X - 1][Y] *= 2;
							m[X][Y] = 0;
						}
						ok = false;
					}
				}
				break;
			case 'd':
				ok = true;
				while (Y < s-1 && ok) {
					if (m[X][Y + 1] == 0) {
						Y +=1;
						m[X][Y] = m[X][Y - 1];
						m[X][Y - 1] = 0;
					} else {
						if (m[X][Y + 1] == m[X][Y]) {
							m[X][Y + 1] *= 2;
							m[X][Y] = 0;
						}
						ok = false;
					}
				}
				break;
			}
		}
	}*/
}
