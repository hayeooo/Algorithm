package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 캐슬 디펜스
 * 적의 위치를 리스트에 저장하는 방식으로 진행
 */
public class BOJ_17135_캐슬디펜스_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N, M;			// 격자판 행의 수, 열의 수
	static int D;				// 궁수의 공격 거리 제한
	
	static List<int[]> enemyLocList;	// 적의 위치를 저장하는 리스트
	static int[] comb;					// 궁수의 열 위치를 저장하는 배열
	static int removedCnt=0;				// 제거한 적의 수
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// N, M, D 입력 받는다.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		D=Integer.parseInt(st.nextToken());
		
		enemyLocList=new ArrayList<>();
		// 격자판 배열 정보를 입력받는다.
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<M;col++) {
				int value=Integer.parseInt(st.nextToken());
				// 격자판 정보가 적이라면 위치를 저장한다.
				if (value==1) {
					enemyLocList.add(new int[] {row,col});
				}
			}
		}
		
		comb=new int[3];
		combinations(0,0);
		System.out.println(removedCnt);
	}
	// 3명의 궁수 열 위치를 조합한다.
	public static void combinations(int cnt,int startIdx) {
		
		// 3개의 위치를 골랐다면
		if (cnt==3) {
			
			// 게임을 시작한다.
			startGame();
			return;
		}
		// 다음 열 위치를 고른다.
		for (int idx=startIdx;idx<M;idx++) {
			comb[cnt]=idx;
			combinations(cnt+1,idx+1);
		}
	}
	
	// 게임을 진행한다.
	public static void startGame() {
		// 현재 맵의 적 위치를 복사한다.
		List<int[]> tmpEnemy=new ArrayList<>(enemyLocList);
		int cnt=0;
		
		// 적이 모두 제거될 때까지 반복한다.
		while (!tmpEnemy.isEmpty()) {
			// 제거할 적의 위치를 담는 리스트
			List<int[]> toRemove=new ArrayList<>();
			
			// 조합한 궁수의 열의 위치로 가장 가까운 적을 선택한다.
			for (int attackerY:comb) {
				// 거리가 가장 짧은 적의 위치와 거리를 저장한다.
				int minX=N-1;
				int minY=M-1;
				int minDist=Integer.MAX_VALUE;
				boolean flag=false;
				
				// 현재 남아있는 적들 중에서 거리가 가장 짧은 적 고르기
				for (int[] enemyLoc:tmpEnemy) {
					
					// 거리를 구한다.
					// 궁수 행 위치는 N
					int dist=getDistance(N,attackerY,enemyLoc[0],enemyLoc[1]);
					
					// 거리가 D 이하
					if (dist>D) continue;
					
					// 최소 거리인 경우
					if (dist<minDist) {
						minX=enemyLoc[0];
						minY=enemyLoc[1];
						minDist=dist;
						flag=true;
					}
					
					// 최소 거리가 같은 경우
					else if(dist==minDist) {
						// 왼쪽에 있는 적을 선택한다.
						if (enemyLoc[1]<minY) {
							minX=enemyLoc[0];
							minY=enemyLoc[1];
							flag=true;
						}
					}
				}
				
				// 적이 선택된 경우, 제거할 대상에 넣는다.
				if (flag) {
					toRemove.add(new int[] {minX,minY});
				}
				
			}
			// 3 개의 적을 선택했다면, 적을 제거한다.
			for (int[] removed:toRemove) {
				for (int originIdx=tmpEnemy.size()-1;originIdx>=0;originIdx--) {
					if ((tmpEnemy.get(originIdx)[0]==removed[0]) && (tmpEnemy.get(originIdx)[1]==removed[1])) {
						tmpEnemy.remove(originIdx);
						cnt+=1;
					}
				}
			}
			
			// 남아있는 적의 위치를 아래로 한 칸씩 이동시킨다.
			for (int originIdx=tmpEnemy.size()-1;originIdx>=0;originIdx--) {
				// 아래로 한 칸 이동한다.
				tmpEnemy.set(originIdx, new int[] {tmpEnemy.get(originIdx)[0]+1,tmpEnemy.get(originIdx)[1]});
				// 성 밖으로 나간 경우 제거한다.
				if (tmpEnemy.get(originIdx)[0]>=N) {
					tmpEnemy.remove(originIdx);
				}
			}
			
		}
		removedCnt=Math.max(cnt, removedCnt);
		
	}
	
	// 두 위치 사이에 거리를 구한다.
	public static int getDistance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2)+Math.abs(c1-c2);
	}

}
