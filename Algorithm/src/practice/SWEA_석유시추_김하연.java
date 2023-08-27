package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * M개에서 N개를 뽑아 석유를 추출한다.
 * 동시에 여러 개의 시추에서 석유를 뽑아야하므로 각 칸에 뽑을 수 있는 석유 양을 표시한다.
 * M개에서 N개의 조합을 구해야 한다.
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
public class SWEA_석유시추_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;					// 테스트케이스 개수
	static int S;					// 지도의 크기
	static int M;					// 실제 시추할 영역의 개수
	static int N;					// 실제 시추할 개수
	
	static int[][] map;				// 석유의 양을 담고 있는 지도
	static OilGetter[] getters;
	static int[] comb;
	
	static int[] dx= {-1,-1,0,1,1,1,0,-1};
	static int[] dy= {0,1,1,1,0,-1,-1,-1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스 개수를 입력 받는다.
		T=Integer.parseInt(br.readLine());
		
		for (int test_case=1;test_case<=T;test_case++) {
			// 지도의 크기를 입력받는다.
			S=Integer.parseInt(br.readLine().trim());
			map=new int[S][S];
			
			// 시추할 수 있는 영역의 개수, 실제 시추할 개수를 입력받는다.
			st=new StringTokenizer(br.readLine().trim());
			M=Integer.parseInt(st.nextToken());
			N=Integer.parseInt(st.nextToken());
			
			getters=new OilGetter[M];
			comb=new int[N];
			
			// 시추할 수 있는 영역의 가로, 세로, 추출할 수 있는 석유의 양, 시추 횟수를 입력받는다.
			for (int idx=0;idx<M;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				int amount=Integer.parseInt(st.nextToken());
				int cnt=Integer.parseInt(st.nextToken());
				
				getters[idx]=new OilGetter(x,y,amount,cnt);
			}
			// 매장된 석유의 정보를 입력받는다.
			for (int row=0;row<S;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<S;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			// 시추 가능한 영역 중, N개의 영역을 고른다.
			combinations(0,0);
		}
	}
	
	public static void combinations(int cnt,int startIdx) {
		if (cnt==N) {
			// 시추 작업을 진행한다.
			return;
		}
		for (int idx=startIdx;idx<M;idx++) {
			comb[cnt]=idx;
			combinations(cnt+1,idx+1);
		}
	}
	
	public static void getOil() {
		// 각 영역에서 뽑을 양을 정한다.
		int count=0;
		// 가장 큰 시추 횟수를 고른다.
		for (int idx=0;idx<N;idx++) {
			count=Math.max(count, getters[comb[idx]].cnt);
		}
		
		// 가장 큰 시추 횟수만큼 반복한다.
		for (int c=0;c<count;c++) {
			// 각 시추가 뽑을 수 있는 석유의 영역을 파악한다.
			// 팔방으로 확인하고, 해당 영역이 0인 경우 석유가 있을 때까지 같은 방향으로 계속 뻗어나간다.
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
					// 0이 아닌 석유 양을 찾을 때까지 반복
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
