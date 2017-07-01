import java.util.Random;

public class matriz {
	int s = 4;
	int[][] m = new int[s][s];

	public matriz() {
		for (int i = 0; i < s; i++) {
			for (int j = 0; j < s; j++) {
				m[i][j] = 0;
			}
		}
	}

	public matriz(int[][] m) {
		this.m = m;
	}

	public boolean agregar2(){
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
			for (int n : c) {
				if(n<10){
					System.out.print("  "+n+"  ");
				}
				else{
					if(n<100){
						System.out.print("  "+n+" ");
					}
					else{
						if(n<1000){
							System.out.print(" "+n+" ");
						}
						else{
							System.out.print(" "+n);
						}
					}
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public boolean mover(char dir) {
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
	}
}
