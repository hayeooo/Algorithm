package practice;
/*
 * BOJ 치킨 배달
 * 조합(중복X)을 이용하여 배치할 수 있는 치킨집 조합 고르기
 * 1. N: 치킨 집 개수, M: 최대 치킨 집 개수 입력 받기
 * 2. 도시 정보를 입력 받으면서 집 위치, 치킨 집 위치를 배열에 저장한다.
 * 3. 치킨 집 위치를 담은 배열에서 인덱스를 기준으로 조합을 만든다.
 * 4. 전체 치킨 집 조합 길이만큼 반복하며
 *  4-1. 하나의 치킨 집 조합과
 *  4-2. 모든 집의 치킨거리를 계산하고
 *  4-3. 각각의 집은 가장 작은 치킨 거리를 저장한다.
 *  4-4. 하나의 조합이 끝나면 각각의 집의 최소 치킨 거리를 더한다.
 *  4-5. 모든 조합을 반복하며 가장 작은 치킨 거리합을 결과로 출력한다.
 *  
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
	
	static int N;							// N*N 크기를 가진 도시 정보
	static int M;							// 치킨집의 최대 개수 M
	
	static List<int[]> chickenLoc;				// 치킨집 위치
	static List<int[]> houseLoc;				// 집 위치
	static List<int[]> chickenComb;						// 치킨집 조합, 인덱스
	static int[] comb;
	static int result=Integer.MAX_VALUE;
	static int minDist;
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		
		chickenLoc=new ArrayList<int[]>();				// 치킨집 위치를 저장할 공간
		houseLoc=new ArrayList<int[]>();				// 집 위치를 저장할 공간
		chickenComb=new ArrayList<int[]>();				// 치킨집 조합을 저장할 공간
		
		// 입력
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		
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
			}
		}
		
		// 치킨집 조합 완성하기
		// combinations 메소드 수행 후, M개의 치킨집 조합이 chickenComb에 저장됨.
		comb=new int[M];
		combinations(0,0);
		
		// 치킨 거리 구하기 완전탐색
		// chickenComb 각 조합에 대해서
		// 집과의 거리 구하기
		for (int combIdx=0;combIdx<chickenComb.size();combIdx++) {				// 여러 개의 치킨집 combination 중
			// 저장되어 있는 집 (한 개)와 combination 내 치킨 집 중 가장 짧은 거리 저장
			
			int[] pickedChickens=chickenComb.get(combIdx);						// 하나의 치킨집 조합을 고른다.
			int tmpSum=0;						
			for (int houseIdx=0;houseIdx<houseLoc.size();houseIdx++) {			// 모든 집 중
				int houseRow=houseLoc.get(houseIdx)[0];							//하나의 집과
				int houseCol=houseLoc.get(houseIdx)[1];
				int minDist=Integer.MAX_VALUE;
				
				
				for (int chickenIdx=0;chickenIdx<M;chickenIdx++) {				// 선택된 치킨집 조합 안에 들어있는 치킨집과 집의 모든 치킨거리를 구한다.
					int chickenRow=chickenLoc.get(pickedChickens[chickenIdx])[0];
					int chickenCol=chickenLoc.get(pickedChickens[chickenIdx])[1];
					
					int diff=Math.abs(houseRow-chickenRow)+Math.abs(houseCol-chickenCol);	
					minDist=Math.min(minDist, diff);							// 가장 짧은 치킨 거리를 저장한다.
				}
				// 결과적으로 minDist에는 치킨 조합 안에서 가장 가까운 치킨집과의 거리를 저장되어 있다.
				tmpSum+=minDist;
			}
			// 각각의 집 별로 최단 치킨 경로가 저장되어있고, 다른 치킨집 조합 치킨 거리와 비교하여 가장 짧은 치킨 거리로 업데이트 한다.
			result=Math.min(result, tmpSum);
		}
		
		// 결과 출력
		System.out.println(result);
	}
	// 치킨집 조합 구하기(chickenLoc의 index 기준)
	public static void combinations(int cnt, int start) {	// cnt: 현재까지 선택한 숫자의 개수, start: 선택할 숫자의 시작 범위
		if (cnt==M) {
			int[] tmp=Arrays.copyOf(comb, M);
			chickenComb.add(tmp);
			return;
		}
		for (int idx=start;idx<chickenLoc.size();idx++) {
			comb[cnt]=idx;
			combinations(cnt+1,idx+1);
		}
	}
}
