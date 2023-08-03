package practice;
/*
 * 조합(중복X)
 * N: 치킨 집 개수, M: M 개 고르기/ index 값으로 조합 구하기
 * 뽑힌 조합 {치킨집 1, 치킨집2, 치킨집3}이 있다면 각 도시는 가장 작은 치킨 집 거리를 저장한다.
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 1.  치킨 집 개수(chicken) 구하기+치킨 집 위치 배열에 넣기
 * 2. 0~chicken-1만큼의 인덱스로 조합 계산
 * 3. 각 조합에 들어있는 치킨 집과집과의 거리 구하기
 * */
public class ChickenDelivery {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;
	static int M;
	static int[][] map;						// 도시 정보
	static List<int[]> chickenLoc;				// 치킨집 위치
	static List<int[]> houseLoc;				// 집 위치
	static List<int[]> chickenComb;						// 치킨집 조합, 인덱스
	static int[] comb;
	static int result=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		
		chickenLoc=new ArrayList<int[]>();
		houseLoc=new ArrayList<int[]>();
		chickenComb=new ArrayList<int[]>();
		
		// 입력
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		
		// 집, 치킨집 위치 정보 저장
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine());
			for (int col=0;col<N;col++) {
				int info=Integer.parseInt(st.nextToken());
				if (info==1) {						// 집인 경우
					houseLoc.add(new int[] {row,col});
				}else if(info==2) {					// 치킨집인 경우
					chickenLoc.add(new int[] {row,col});
					continue;
				}
				map[row][col]=info;
			}
		}
		
		// 치킨집 조합 완성하기
		// combinations 메소드 수행 후, M개의 치킨집 조합이 chickenComb에 저장됨.
		comb=new int[M];
		combinations(0,0);
		
		// 치킨 거리 구하기 완전탐색
		// chickenComb 각 조합에 대해서
		// 집과의 거리 구하기
		int[] tmp=new int[houseLoc.size()];
		for (int combIdx=0;combIdx<chickenComb.size();combIdx++) {
			Arrays.fill(tmp, Integer.MAX_VALUE);
			for (int idx=0;idx<M;idx++) {
				int chickenIdx=chickenComb.get(combIdx)[idx];
				int chickenRow=chickenLoc.get(chickenIdx)[0];
				int chickenCol=chickenLoc.get(chickenIdx)[1];
				
				for (int houseIdx=0;houseIdx<houseLoc.size();houseIdx++) {
					// 집과 치킨집 사이 절대값 구하기
					// 이미 저장되어 있다면(다른 치킨 집과 거리) 비교를 통해 더 작은 치킨 거리 넣기
				}
			}
			// 합계와 result 비교 -> 더 작은 값 넣기
		}
		
	}
	// 치킨집 조합 구하기
	public static void combinations(int cnt, int start) {
		if (cnt==M) {
			chickenComb.add(comb);
			return;
		}
		for (int idx=start;idx<chickenLoc.size();idx++) {
			comb[cnt]=idx;
			combinations(cnt+1,idx+1);
		}
	}
}
