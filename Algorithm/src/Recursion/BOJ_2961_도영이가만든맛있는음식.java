package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 2961: 도영이가 만든 맛있는 음식
 * 
 * 1. 재료의 개수 N을 입력 받는다.
 * 2. 각 재료의 신맛과 쓴맛을 배열에 저장한다.
 * 3. 가지고 있는 재료의 부분 집합을 구하고 신맛과 쓴맛의 차이를 계산한다.
 * 4. 모든 경우를 탐색하고 신맛과 쓴맛의 차이가 가장 작은 요리의 차이를 반환한다.
 * */
public class BOJ_2961_도영이가만든맛있는음식 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;								// 재료의 개수
	static int[] sour;							// 신 맛의 배열
	static int[] bitter;						// 쓴 맛의 배열
	static List<boolean[]> comb;				// 재료의 조합
	static boolean[] isSelected;				// 각 재료가 선택되었는지 유무를 배열로 저장
	static int diff=Integer.MAX_VALUE;			// 신맛과 쓴맛의 차이가 가장 작은 값
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 재료의 개수 입력
		N=Integer.parseInt(br.readLine());
		
		// 신 맛과 쓴 맛의 값을 따로 저장
		sour=new int[N];
		bitter=new int[N];
		
		// 재료의 개수만큼 신 맛과 쓴 맛을 저장
		for (int idx=0;idx<N;idx++) {
			st=new StringTokenizer(br.readLine());
			sour[idx]=Integer.parseInt(st.nextToken());
			bitter[idx]=Integer.parseInt(st.nextToken());
		}
		// 선택된 재료 정보를 담을 배열 크기 초기화
		isSelected=new boolean[N];
		
		// 모든 재료의 조합을 담을 comb 객체 생성
		comb=new ArrayList<boolean[]>();
		
		// 재료의 조합을 구함 -> comb에 결과 저장
		getSubSet(0,0);
		
		// 모든 조합에 대한 신 맛, 쓴 맛의 차이를 구해 차이가 가장 작은 결과 저장
		getScore();
		
		// 결과 출력
		System.out.println(diff);
	}
	// 재료의 부분 집합을 구함
	public static void getSubSet(int cnt,int chosen) {		// cnt: 현재까지 고려된 재료의 개수, chosen: 실제로 선택된 재료의 개수
		if (cnt==N) {										// 모든 재료가 고려되었을 때
			if (chosen>0) {									// 공집합을 제외하고
				comb.add(Arrays.copyOf(isSelected, N));		// 선택된 재료 정보를 저장
			}
			return;
		}
		
		isSelected[cnt]=true;								// 현재 재료를 선택
		getSubSet(cnt+1,chosen+1);							// 다음 재료를 고려
		isSelected[cnt]=false;								// 현재 재료를 선택하지 않음
		getSubSet(cnt+1,chosen);							// 선택하지 않은 채로 다음 재료 고려
	}
	// 부분집합의 점수 계산
	public static void getScore() {
		for (int idx=0;idx<comb.size();idx++) {				// 모든 조합에 대해서
			boolean[] cuisine_combi=comb.get(idx);			// 하나의 조합 가져오기
			int sourTotal=1;
			int bitterTotal=0;
			for (int cuisineIdx=0;cuisineIdx<N;cuisineIdx++) {	// 각 재료 별로
				if (cuisine_combi[cuisineIdx]) {				// 선택된 재료라면
					sourTotal*=sour[cuisineIdx];				// 점수 계산에 반영
					bitterTotal+=bitter[cuisineIdx];
				}
			}
			// 차이가 가장 작은 것으로 업데이트
			diff=Math.min(diff, Math.abs(sourTotal-bitterTotal));
		}
	}

}
