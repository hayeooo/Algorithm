package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * SWEA 5215: 햄버거 다이어트 부분집합
 * 
 * 1. 테스트케이스 수를 입력 받는다.
 * 2. 테스트케이스 별로 재료의 수와 제한 칼로리를 입력 받는다.
 * 3. 각 재료의 맛 점수와 칼로리를 입력 받는다.
 * 4. 재료를 1개부터 N개의 조합을 생성하고 각 조합의 칼로리 합과 맛 점수를 구한다.
 * 5. 칼로리 합이 제한 칼로리를 넘지 않는다면 가장 높은 맛 점수를 저장한다.
 * *
 */
public class SWEA_5215_햄버거다이어트_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;						// 테스트케이스 수
	static int N;						// 재료의 수
	static int L;						// 제한 칼로리
	
	static int[] taste;					// 맛 점수 대한 정보
	static int[] calories;				// 칼로리에 대한 정보
	static int[] comb;					// 햄버거 재료 조합
	static boolean[] isSelected;		// 각 재료 선택 여부를 저장하는 배열
	
	static int maxScore;
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 입력
		T=Integer.parseInt(br.readLine().trim());
		
		// 테스트케이스 별로 반복한다.
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			// 재료의 수, 제한 칼로리를 입력 받는다.
			N=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			
			// 맛 점수와, 칼로리에 대한 정보를 저장한다.
			taste=new int[N];
			calories=new int[N];
			for (int idx=0;idx<N;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				taste[idx]=Integer.parseInt(st.nextToken());
				calories[idx]=Integer.parseInt(st.nextToken());
			}
			// 맛에 대한 최고 점수를 초기화한다.
			maxScore=0;
			isSelected=new boolean[N];
			
			// 1개부터 N개까지 햄버거 재료를 뽑아
			// 해당 재료로 햄버거를 만들 시, 점수와 칼로리의 총합을 구한다.
			// 제한 칼로리를 넘지 않는 맛의 점수 중 최고점을 저장한다.
			getSubSet(0,0,0);
			
			// 결과를 출력한다.
			System.out.println("#"+test_case+" "+maxScore);
			
		}
	}
	// 재료의 부분 집합을 구하여
	// 제한 칼로리를 넘지 않는 가장 높은 맛 점수를 저장한다.
	public static void getSubSet(int cnt,int score,int caloriesSum) {	
		// N개의 재료를 모두 확인한 후,
		// 점수를 구한다.
		if (cnt==N) {
			if (caloriesSum<=L) {
				maxScore=Math.max(score, maxScore);
			}
			return;
		}
		// N 개의 재료를 넣을지 말지 결정한다.
		isSelected[cnt]=true;
		getSubSet(cnt+1,score+taste[cnt],caloriesSum+calories[cnt]);
		
		isSelected[cnt]=false;
		getSubSet(cnt+1,score,caloriesSum);
		
	}
	

}
