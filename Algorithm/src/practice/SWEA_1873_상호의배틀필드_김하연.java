package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * SWEA 1873: ��ȣ�� ��Ʋ�ʵ�
 * ������� ������ ������� �Է¿� ���� ���������� �̷���� ���� �ʿ��� �پ��� ������ �Ѵ�.
 * ����ڰ� ���� �� �ִ� ������ U, D, L, R, S�̴�.
 * ���� �� ������ ������ �̵��Ϸ��� �Ѵٸ� �̵����� �ʴ´�.
 * ��ź�� ���� �ε����� ��ź�� �Ҹ��ϰ�, ������ ������� ���̶�� �ı��Ǿ� ������ �ȴ�.
 * �ʱ� ���� ���� ���¿� ����ڰ� ���� �Է��� ������� �־��� ��, ��� �Է��� ó���ϰ� ���� ���� ���� ���°� ��� �Ǵ��� ���Ѵ�.
 */
public class SWEA_1873_��ȣ�ǹ�Ʋ�ʵ�_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// �׽�Ʈ���̽� ��
	static int H,W;					// ���� ���� ���� H, �ʺ� W
	static char[][] map;			// H*Wũ���� ������
	static int N;					// ����ڰ� ���� �Է��� ����
	static String cmd;				// ���̰� N�� ���ڿ�
	
	static int[] dx= {-1,1,0,0};	// up, down, left, right
	static int[] dy= {0,0,-1,1};
	
	static Map<Character,Integer> dirHm=new HashMap<>();
	
	static int carX,carY;			// ������ ���� ��ġ	
	static int cd;					// ������ ���� ���� (up:0, down:1, left:2, right:3)
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		dirHm.put('U', 0);
		dirHm.put('D', 1);
		dirHm.put('L', 2);
		dirHm.put('R', 3);
		
		sb=new StringBuilder();
		
		// �׽�Ʈ ���̽� ���� �Է� �޴´�.
		T=Integer.parseInt(br.readLine().trim());
		
		for (int test_case=1;test_case<=T;test_case++) {
			
			// ���� ���� H, �ʺ� W�� �Է� �޴´�.
			st=new StringTokenizer(br.readLine().trim());
			H=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			
			// ���� ���� ũ�⸦ �ʱ�ȭ�Ѵ�.
			map=new char[H][W];
			
			// H���� ������ �ٿ� ���̰� W�� ���ڿ��� �Է� �޴´�.
			for (int row=0;row<H;row++) {
				String line=br.readLine().trim();
				for (int col=0;col<W;col++) {
					map[row][col]=line.charAt(col);
					// �ʱ� �ʿ��� ������ �߰��� ���
					if (map[row][col]=='^') {			// up
						carX=row;
						carY=col;
						cd=0;
						map[row][col]='.';
					}
					else if (map[row][col]=='v') {		// down
						carX=row;
						carY=col;
						cd=1;
						map[row][col]='.';
					}
					else if (map[row][col]=='<') {		// left
						carX=row;
						carY=col;
						cd=2;
						map[row][col]='.';
					}
					else if (map[row][col]=='>') {		// right
						carX=row;
						carY=col;
						cd=3;
						map[row][col]='.';
					}
				}
			}
			// ����ڰ� ���� �Է��� ������ ��Ÿ���� ���� N�� �Է� �޴´�.
			N=Integer.parseInt(br.readLine().trim());
			
			// N�� ���ڿ� ���ʴ�� �Է��� ó���Ѵ�.
			cmd=br.readLine().trim();
			for (int idx=0;idx<N;idx++) {
				
				// �Է��� S���, ��ź�� ���.
				if (cmd.charAt(idx)=='S') {
					shoot();
				}
				// �� �� �Է��� ������ �����δ�.
				else {
					moveCar(cmd.charAt(idx));
				}
			}
			// ������ ��ġ�� ���´�.
			if (cd==0) {
				map[carX][carY]='^';
			}
			else if(cd==1) {
				map[carX][carY]='v';
			}
			else if (cd==2) {
				map[carX][carY]='<';
			}
			else if (cd==3) {
				map[carX][carY]='>';
			}
			
			// �׽�Ʈ���̽��� ��� ���ڿ��� �ִ´�.
			sb.append("#"+test_case+" ");
			// ���� �� ������ ����Ѵ�.
			for (int row=0;row<H;row++) {
				for (int col=0;col<W;col++) {
					sb.append(map[row][col]);
				}
				sb.append("\n");
			}
			
		}
		System.out.print(sb);
	}
	// ��ź�� �߻��Ѵ�.
	// ������ ������� �� �Ǵ� ��ö�� ������� ���� �浹�ϰų� ���� �� ������ ���� ������ �����Ѵ�.
	public static void shoot() {
		int nextX=carX+dx[cd];
		int nextY=carY+dy[cd];
		
		while (true) {
			// ��ź �߻� ��ġ�� �迭 ������ �Ѿ�� ���
			if (nextX<0 || nextX>=H || nextY<0 || nextY>=W) {
				break;
			}
			// ������ ������� ���� �浹�ϴ� ���
			if (map[nextX][nextY]=='*') {
				// ������ ������ �����.
				map[nextX][nextY]='.';
				break;
			}
			// ��ö�� ������� ���� �浹�ϴ� ���
			if (map[nextX][nextY]=='#') {
				// �ƹ��ϵ� �Ͼ�� �ʰ� �����Ѵ�.
				break;
			}
			// �� ���� ���
			// ��ź�� ��ġ�� �ٲ۴�.
			nextX+=dx[cd];
			nextY+=dy[cd];
		}
	}
	// ������ �ٶ󺸴� ������ �ٲٰ�, �̵� ������ ĭ�� ������� �� ĭ���� �̵��Ѵ�.
	public static void moveCar(char d) {
		// ������ �ٶ󺸴� ������ �ٲ۴�.
		cd=dirHm.get(d);
		
		int nextX=carX+dx[cd];
		int nextY=carY+dy[cd];
		
		// ���� �̵� ��ġ�� �迭�� ������ ����� ���
		if (nextX<0 || nextX>=H || nextY<0 || nextY>=W) {
			return;
		}
		// �������, �� ĭ �̵��Ѵ�.
		if (map[nextX][nextY]=='.') {
			carX=nextX;
			carY=nextY;
		}
	}

}
