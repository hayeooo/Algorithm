package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * M������ N���� �̾� ������ �����Ѵ�.
 * ���ÿ� ���� ���� ���߿��� ������ �̾ƾ��ϹǷ� �� ĭ�� ���� �� �ִ� ���� ���� ǥ���Ѵ�.
 * M������ N���� ������ ���ؾ� �Ѵ�.
 */
class OilGetter{
	int x;
	int y;
	int amount;
	int cnt;
	
	public OilGetter(int x, int y, int amount, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.amount = amount;
		this.cnt = cnt;
	}
	
}
public class SWEA_��������_���Ͽ� {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;					// �׽�Ʈ���̽� ����
	static int S;					// ������ ũ��
	static int M;					// ���� ������ ������ ����
	static int N;					// ���� ������ ����
	
	static int[][] map;				// ������ ���� ��� �ִ� ����
	static OilGetter[] getters;
	static int[] comb;
	
	static int[] dx= {-1,-1,0,1,1,1,0,-1};
	static int[] dy= {0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// �׽�Ʈ ���̽� ������ �Է� �޴´�.
		T=Integer.parseInt(br.readLine());
		
		for (int test_case=1;test_case<=T;test_case++) {
			// ������ ũ�⸦ �Է¹޴´�.
			S=Integer.parseInt(br.readLine().trim());
			map=new int[S][S];
			
			// ������ �� �ִ� ������ ����, ���� ������ ������ �Է¹޴´�.
			st=new StringTokenizer(br.readLine().trim());
			M=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(st.nextToken());
			
			getters=new OilGetter[M];
			comb=new int[N];
			
			// ������ �� �ִ� ������ ����, ����, ������ �� �ִ� ������ ��, ���� Ƚ���� �Է¹޴´�.
			for (int idx=0;idx<M;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int amount=Integer.parseInt(st.nextToken());
				int cnt=Integer.parseInt(st.nextToken());
				
				getters[idx]=new OilGetter(x,y,amount,cnt);
			}
			// ����� ������ ������ �Է¹޴´�.
			for (int row=0;row<S;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<S;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			// ���� ������ ���� ��, N���� ������ ����.
			combinations(0,0);
		}
	}
	
	public static void combinations(int cnt,int startIdx) {
		if (cnt==N) {
			// ���� �۾��� �����Ѵ�.
			return;
		}
		for (int idx=startIdx;idx<M;idx++) {
			comb[cnt]=idx;
			combinations(cnt+1,idx+1);
		}
	}
	
	public static void getOil() {
		// �� �������� ���� ���� ���Ѵ�.
		int count=0;
		// ���� ū ���� Ƚ���� ����.
		for (int idx=0;idx<N;idx++) {
			count=Math.max(count, getters[comb[idx]].cnt);
		}
		
		// ���� ū ���� Ƚ����ŭ �ݺ��Ѵ�.
		for (int c=0;c<count;c++) {
			// �� ���߰� ���� �� �ִ� ������ ������ �ľ��Ѵ�.
			// �ȹ����� Ȯ���ϰ�, �ش� ������ 0�� ��� ������ ���� ������ ���� �������� ��� �������.
			int[][] amountMap=new int[S][S];
			
			for (int sichu=0; sichu<N;sichu++) {
				OilGetter curSichu=getters[comb[sichu]];
				for (int d=0;d<8;d++) {
					int nextX=curSichu.x+dx[d];
					int nextY=curSichu.y+dy[d];
					
					if (nextX<0 || nextX>=S || nextY<0 || nextY>=S) {
						break;
					}
					
					if (map[nextX][nextY]!=0) {
						amountMap[nextX][nextY]+=curSichu.amount;
						continue;
					}
					// 0�� �ƴ� ���� ���� ã�� ������ �ݺ�
					while (true) {
						nextX+=dx[d];
						nextY+=dy[d];
						
						if (nextX<0 || nextX>=S || nextY<0 || nextY>=S) {
							break;
						}
						
						if (map[nextX][nextY]!=0) {
							amountMap[nextX][nextY]+=curSichu.amount;
						}
					}
				}
			}
		}
	}

}
