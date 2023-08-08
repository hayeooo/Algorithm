package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N개의 과자봉지의 index로 2개의 과자 봉지를 뽑는 조합을 구한다.
 * 과자 두 봉지의 무게가 M 그램을 초과하지 않으면서 들고 다닐 수 있는 최대 무게 합을 구한다.
 * 
 * 1. 테스트케이스 수 입력 받기
 * 2. 과자 봉지의 개수(N)과 무게 합 제한(M) 입력 받기
 * 3. N개의 과자 봉지 무게 입력 받기
 * 4. 과자 봉지 수만큼 인덱스로 2개를 뽑는 조합 구하기
 * 5. 2개를 뽑았을 경우, 과자 무게의 합을 구하기
 * 	5-1. 무게 합 제한보다 클 때, 무시
 * 	5-2. 무게 합 제한보다 작을 때, 기존 값과 비교하여 가장 큰 값 대입
 * */
public class SWEA_9229_한빈이와SpotMart_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int TC;					// 테스트 케이스 수
	static int N;					// 과자 봉지 수
	static int M;					// 과자 제한 무게
	static final int COUNT=2;		// 들고 갈 수 있는 과자 봉지 수
	static int res;					// 최대 과자 봉지 합
	static int[] comb;				// 과자 조합을 담는 배열
	static int[] snacks;			// 과자 무게 정보를 담는 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스 수 입력
		TC=Integer.parseInt(br.readLine());
		
		for (int tc=1;tc<=TC;tc++) {
			res=-1;										// 모든 조합의 과자 무게 합이 과자 제한 무게를 넘는 경우 -1 출력
			st=new StringTokenizer(br.readLine());
			// N과 M 입력 받는다.
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			
			// 과자 봉지 수만큼 배열 크기를 초기화한다.
			snacks=new int[N];
			
			// 들고 갈 수 있는 과자 봉지 index를 저장하는 배열 크기를 초기화한다.
			comb=new int[COUNT];
			
			// 과자 봉지 무게를 입력 받는다.
			st=new StringTokenizer(br.readLine());
			for (int idx=0;idx<N;idx++) {
				snacks[idx]=Integer.parseInt(st.nextToken());
			}
			// 2개의 과자 봉지 조합을 고르고 최대 과자 봉지 무게를 구한다.
			combinations(0,0);
			
			// 결과를 출력한다.
			System.out.println("#"+tc+" "+res);
		}
		
	}
	
	public static void combinations(int cnt,int startIdx) {
		// 고른 과자 봉지 개수가 COUNT일 때
		if (cnt==COUNT) {
			// 고른 조합의 과자 봉지 무게를 더한다.
			int total=0;
			for (int idx=0;idx<COUNT;idx++) {
				total+=snacks[comb[idx]];
			}
			// 무게 제한을 넘지 않은 경우,
			// 기존값 대비 큰 무게를 결과값으로 넣는다.
			if (total<=M) {
				res=Math.max(total, res);
			}
			// 재귀를 종료한다.
			return;
		}
		// 과자 봉지 수 인덱스로 과자 조합을 선택한다.
		for (int snackIdx=startIdx;snackIdx<N;snackIdx++) {
			comb[cnt]=snackIdx;
			combinations(cnt+1,snackIdx+1);		// 중복 조합이 아니므로, 선택한 인덱스의 다음 인덱스부터 탐색하도록 한다.
		}
	}
}
