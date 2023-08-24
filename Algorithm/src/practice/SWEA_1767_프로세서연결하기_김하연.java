package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 프로세서가 위치한 곳에서 이동 가능한 방향을 모두 고려해서 가장 짧은 전선의 길이 합
 * 최대한 많은 core가 연결되어야 한다.
 * 우선순위 첫 번째는 core의 수, 두 번째로 짧은 전선의 길이 합이다.
 * 
 * core의 위치를 리스트에 담는다.
 * 각 core가 4 방향으로 탐색했을 때의 전선 길이 합과, 전선을 연결하지 않았을 경우를 고려한다.
 * 
 */
public class SWEA_1767_프로세서연결하기_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static final boolean ON=true;
	static final boolean OFF=true;
	
	static int T;					// 테스트케이스 개수
	static int N;					// N*N 배열
	static int C;					// core의 전체 개수
	static int[][] map;				// 멕시노스의 상태를 저장하는 배열
	static List<int[]> coreLocList;		// 코어 위치를 담은 리스트
	
	static int[] dx= {-1,0,1,0};
	static int[] dy= {0,1,0,-1};
	
	static boolean[][] visited;		// 전선으로 연결된 칸인지 저장하는 배열
	static int maxConnected;		// core의 최대 연결 개수
	static int minLength;			// 최소 전선 길이의 합
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 수 입력 받는다.
		T=Integer.parseInt(br.readLine());
		
		// 테스트케이스 수만큼 반복한다.
		for (int test_case=1;test_case<=T;test_case++) {
			// 배열의 크기 입력받는다.
			N=Integer.parseInt(br.readLine());
			map=new int[N][N];
			
			// 격자 정보를 입력 받으면서 core의 위치를 저장한다.
			coreLocList=new ArrayList<int[]>();
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine());
				for (int col=0;col<N;col++) {
					map[row][col]=Integer.parseInt(st.nextToken());
					// 현재 칸이 core인 경우
					if (map[row][col]==1) {
						coreLocList.add(new int[] {row,col});
					}
				}
			}
			// 코어의 총 개수
			C=coreLocList.size();
			System.out.println(coreLocList.size());
			// 각 코어마다 연결할 수 있는 모든 경우의 수를 고려한다.
			maxConnected=0;
			minLength=Integer.MAX_VALUE;
			visited=new boolean[N][N];
			dfs(0,0,0);
			
			// 코어를 최대로 연결하였을 때, 전선 길이의 합의 최소를 출력한다.
			System.out.println("#"+test_case+" "+minLength);
		}

	}
	// coreIdx: 현재 연결하고 있는 코어 인덱스, OnCore: 연결된 코어 수, length: 전선 길이의 합
	public static void dfs(int coreIdx,int OnCore,int length) {
		
		// 모든 코어를 확인했을 경우
		// 1. 연결된 코어의 수 최댓값, 전선의 길이 최솟값
		if (coreIdx==C) {
			if (maxConnected<OnCore) {
				maxConnected=OnCore;
				minLength=length;
			}
			// 연결된 코어의 수가 같을 때는, 전선 길이의 최솟값
			else if(maxConnected==OnCore) {
				minLength=Math.min(length, minLength);
			}
			
			return;
		}
		int curX=coreLocList.get(coreIdx)[0];
		int curY=coreLocList.get(coreIdx)[1];
		
		// 전원에 연결되어 있는 경우
		if (curX==0 || curX==N-1 || curY==0 || curY==N-1) {
			dfs(coreIdx+1,OnCore+1,length);
			return;
		}
		
		// 4방향 탐색
		for (int d=0;d<4;d++) {
			int nextX=curX+dx[d];
			int nextY=curY+dy[d];
			
			// 이 방향으로 연결할 수 있는지 확인한다.
			boolean possible=isPossible(nextX,nextY,dx[d],dy[d]);
			
			if (!possible) continue;
			
			// 연결할 수 있다면, 전선을 연결한다.
			int newLength=connect(nextX,nextY,dx[d],dy[d]);
			// 이 방향으로 연결된 상태로 다음 core 진행
			dfs(coreIdx+1,OnCore+1,length+newLength);
			
			// 다른 방향 탐색을 위해 연결선 원상복귀
			unconnect(nextX,nextY,dx[d],dy[d]);
			
		}
		
		// 현재 코어를 전선에 연결하지 않고 다음 코어 연결 탐색
		dfs(coreIdx+1,OnCore,length);
	}
	
	public static boolean isPossible(int x,int y,int dx,int dy) {
		
		int cx=x;
		int cy=y;
		boolean possible=true;
		
		while (true) {
			//System.out.println("possible");
			// 배열을 벗어나는 경우
			if (cx<0 || cx>=N || cy<0 || cy>=N) break;
			
			// 이미 전선이 연결된 곳인 경우
			// 연결할 수 없다.
			if (visited[cx][cy]) {
				possible=false;
				break;
			}
			
			// core가 가로막고 있는 경우
			// 연결할 수 없다.
			if (map[cx][cy]==1) {
				possible=false;
				break;
			}
			cx+=dx;
			cy+=dy;
		}
		
		return possible;
	}
	// 연결할 수 있다는 전제 하에 전원에 전선을 연결하고 전선의 길이를 반환한다.
	public static int connect(int x, int y, int dx, int dy) {
		
		int cx=x;
		int cy=y;
		int length=0;
		while (true) {
			//System.out.println("connect");
			// 배열을 벗어나는 경우
			if (cx<0 ||cx>=N || cy<0 || cy>=N) break;
			
			// core가 가로막고 있는 경우
			if (map[cx][cy]==1) {
				break;
			}
			
			// 현재 칸을 전선으로 채운다.
			visited[cx][cy]=ON;
			// 전선의 길이를 1 증가시킨다.
			length+=1;
			
			// 다음 칸으로 이동
			cx+=dx;
			cy+=dy;
		}
		return length;
	}
	
	// 연결한 전선을 원상 복귀시킬 때 사용한다.
	// 어차피 겹칠 수 없으므로 false로 바꾸면 된다.
	public static void unconnect(int x,int y,int dx,int dy) {
		
		int cx=x;
		int cy=y;
		
		while(true) {
			//System.out.println("unconnect");
			// 배열을 벗어나는 경우
			if (cx<0 || cx>=N || cy<0 || cy>=N) {
				break;
			}
			
			// core가 가로막고 있는 경우
			if (map[cx][cy]==1) {
				break;
			}
			
			// 현재 칸을 원래대로 바꾼다.(연결하지 않은 상태로)
			visited[cx][cy]=OFF;
			
			// 다음 칸으로 이동
			cx+=dx;
			cy+=dy;
			
		}
	}
	
	
	
	
	
}
