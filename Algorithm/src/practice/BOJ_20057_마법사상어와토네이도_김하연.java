package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20057_�������������̵�_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;
	static int[][] map;
	
	static int[] dx= {0,1,0,-1};	// ��, ��, ��, ��
	static int[] dy= {-1,0,1,0};
	static int[] dist;
	static int cx;
	static int cy;
	static int nx;
	static int ny;
	static int alphaX;
	static int alphaY;
	
	static int spreadSand;			// �迭 ������ ����� ��
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine().trim());
		map=new int[N][N];
		
		// ���� ������ �迭�� �Է��Ѵ�.
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<N;col++) {
				map[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		
		// ����̵��� ȸ���ϸ鼭 �� ���⸶�� �̵��� �� �ִ� �Ÿ�
		dist=new int[2*N-1];
		int num=1;
		for (int idx=0;idx<dist.length-2;idx+=2) {
			dist[idx]=num;
			dist[idx+1]=num;
			dist[idx+2]=num;
			num+=1;
		}
		// �ʱ�ȭ�� ���� ����
		int cd=0;
		cx=N/2;
		cy=N/2;
		System.out.println(Arrays.toString(dist));
		for (int distIdx=0;distIdx<dist.length;distIdx++) {
			// �� ���⸶�� �̵��� �� �ִ� �Ÿ���ŭ �����δ�.
			for (int progress=0;progress<dist[distIdx];progress++) {
				nx=cx+dx[cd];				// y�� ��ġ
				ny=cy+dy[cd];
				alphaX=cx+2*dx[cd];			// alpha ��ġ
				alphaY=cy+2*dy[cd];
				
				// �𷡸� �̵���Ų��.
				moveSand(cd);
				
				// �� ĭ �̵��Ѵ�.
				cx=nx;
				cy=ny;
			}
			// �̵��� �� �ִ� �Ÿ���ŭ �������ٸ�
			// ������ ��ȯ�Ѵ�.
			cd=(cd+1)%4;
		}
		// ����̵��� �̵��ϴ� ���⿡ ���� ���� ���� �����ȴ�.
		System.out.println(spreadSand);
	}
	
	// �𷡸� �̵���Ų��. (y ��ġ�� �߽�����)
	// �̵��� ���� ���� Ȯ��
	public static void moveSand(int cd) {
		// y ��ġ�� �ִ� ��� �𷡰� ������ alpha�� �����ִ� ĭ���� �̵��Ѵ�.
		int totalSand=map[nx][ny];
		int tmp=0;
		// �̵��� �� ������ Ž���Ѵ�.
		int switchDir=0;
		if (cd==0 || cd==2) {
			switchDir=1;
		}
		// ��,�� �Ǵ� ��,��
		for (int dirIdx=0+switchDir;dirIdx<4;dirIdx+=2) {
			// x �� ĭ ��,�Ʒ� �Ǵ� ��,�� (0.01)
			if (cx+dx[dirIdx]>=0 && cx+dx[dirIdx]<N && cy+dy[dirIdx]>=0 && cy+dy[dirIdx]<N) {
				map[cx+dx[dirIdx]][cy+dy[dirIdx]]=(int)((totalSand*1)/100);
			}
			else {
				spreadSand+=(int)((totalSand*1)/100);
			}
			tmp+=(int)((totalSand*1)/100);
			
			// y �� ĭ ��, �Ʒ� �Ǵ� ��, �� (0.07)
			if (nx+dx[dirIdx]>=0 && nx+dx[dirIdx]<N && ny+dy[dirIdx]>=0 && ny+dy[dirIdx]<N) {
				map[nx+dx[dirIdx]][ny+dy[dirIdx]]=(int)((totalSand*7)/100);
			}
			else {
				spreadSand+=(int)((totalSand*7)/100);
			}
			tmp+=(int)((totalSand*7)/100);
			
			// y �� ĭ ��, �Ʒ� �Ǵ� ��, �� (0.02)
			if (nx+2*dx[dirIdx]>=0 && nx+2*dx[dirIdx]<N && ny+2*dy[dirIdx]>=0 && ny+2*dy[dirIdx]<N) {
				map[nx+2*dx[dirIdx]][ny+2*dy[dirIdx]]=(int)((totalSand*2)/100);
			}
			else {
				spreadSand+=(int)((totalSand*2)/100);
			}
			tmp+=(int)((totalSand*2)/100);
			
			// alpha �� ĭ ��, �Ʒ� �Ǵ� ��, �� (0.1)
			if (alphaX+dx[dirIdx]>=0 && alphaX+dx[dirIdx]<N && alphaY+dy[dirIdx]>=0 && alphaY+dy[dirIdx]<N) {
				map[alphaX+dx[dirIdx]][alphaY+dy[dirIdx]]=(int)((totalSand*10)/100);
			}
			else {
				spreadSand+=(int)((totalSand*10)/100);
			}
			tmp+=(int)((totalSand*10)/100);
			
		}
		// alpha �� ĭ ��(���� ����) - (0.05)
		if (alphaX+dx[cd]>=0 && alphaX+dx[cd]<N && alphaY+dy[cd]>=0 && alphaY+dy[cd]<N) {
			map[alphaX+dx[cd]][alphaY+dy[cd]]=(int)((totalSand*5)/100);
		}
		else {
			spreadSand+=(int)((totalSand*5)/100);
		}
		tmp+=(int)((totalSand*5)/100);
		
		// ================���⼭���� �ٽ�===================
		// alpha�� ���� �𷡾� ����, alpha ���� Ȯ��
		if (alphaX>=0 && alphaX<N && alphaY>=0 && alphaY<N) {
			map[alphaX][alphaY]+=(totalSand-tmp);
		}
		else {
			spreadSand+=(totalSand-tmp);
		}
		// y ��ġ���� ����� �� ����
		map[nx][ny]-=tmp;
	}
}
