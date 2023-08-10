package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 4012: 요리사
 * N 개의 식재료를 N/2개씩 나누어 두 개의 요리를 한다.
 * 두 음식의 맛이 최소가 되도록 재료를 배분한다.
 * 재료의 맛은 식재료 간의 시너지의 합으로 결정된다.
 * 
 * 1. 테스트케이스 개수를 입력 받는다.
 * 2. 식재료의 수를 입력 받는다.
 * 3. N/2개의 조합을 구한다.
 * 4. 각 식재료의 시너지를 구하고 두 가지 음식의 시너지를 뺀다.
 * 5. 음식의 차이가 가장 적은 값을 저장한다.
 * */
public class SWEA_4012_요리사_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;							// 테스트케이스 개수
	static int N;							// 식재료 개수
	static int[][] s;						// 식재료 간의 시너지 정보를 담은 배열
	static int[] A;							// A 음식 조합 정보
	static int[] B;							// B 음식 조합 정보
	static boolean[] selected;				// A 음식에 들어간 식재료 선택 여부를 담은 배열
	static int diff;						// 음식 맛의 차이 최소값이 저장되는 변수
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스 수를 입력 받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		// 테스트케이스 별로
		for (int test_case=1;test_case<=T;test_case++) {
			// 식재료의 수 입력 받기
			N=Integer.parseInt(br.readLine().trim());
			
			// 시너지 정보를 저장할 배열을 선언한다.
			s=new int[N][N];
			
			// 시너지 정보를 입력 받는다.
			for (int row=0;row<N;row++) {
				st=new StringTokenizer(br.readLine().trim());
				for (int col=0;col<N;col++) {
					s[row][col]=Integer.parseInt(st.nextToken());
				}
			}
			// A음식과 B음식의 크기를 초기화한다.
			A=new int[N/2];
			B=new int[N/2];
			// 음식 맛 차이를 최대로 지정한다.
			diff=Integer.MAX_VALUE;
			// 식재료의 조합을 선택하고, 각 조합에서 시너지 합을 구한다.
			// 각 음식의 시너지 합을 뺀 값의 최솟값을 저장한다.
			combinations(0,0);
			
			// 테스트케이스 번호와 음식 맛 차이의 최솟값을 결과 문자열에 저장한다.
			sb.append("#"+test_case+" "+diff+"\n");
		}
		// 결과를 출력한다.
		System.out.println(sb);
		
	}
	// A 음식의 식재료 조합을 고른다.
	// A 음식에 사용되지 않고 남은 식재료는 B음식에 사용된다고 가정한다.
	// 각 음식 식재료의 시너지 합을 구한다.
	// 시너지 합의 차이의 최솟값을 저장한다.
	public static void combinations(int cnt,int startIdx) {
		// N/2 크기만큼 식재료를 선택했을 경우
		if (cnt==(int)(N/2)) {
			
			selected=new boolean[N];
			// A음식에 들어간 식재료는 true로 설정한다.
			for (int idx=0;idx<N/2;idx++) {
				selected[A[idx]]=true;
			}
			// true가 아닌 식재료는 B음식에 들어가는 식재료로 저장한다.
			int bIdx=0;
			for (int idx=0;idx<N;idx++) {
				if (selected[idx]) continue;
				B[bIdx++]=idx;
			}
			// 시너지를 구한다.
			int synergyA=getSynergy(A);
			int synergyB=getSynergy(B);
			
			// 시너지 차이의 최소값을 저장한다.
			diff=Math.min(diff, Math.abs(synergyA-synergyB));
			return;
		}
		// 다음 위치에 넣을 식재료를 지정한다.
		for (int idx=startIdx;idx<N;idx++) {
			A[cnt]=idx;
			combinations(cnt+1,idx+1);
		}
	}
	// 각 음식의 시너지 합을 리턴한다.
	public static int getSynergy(int[] arr) {
		int total=0;
		// 모든 식재료의 Sij와 Sji 합을 구한다.
		for (int i=0;i<arr.length-1;i++) {
			for (int j=i+1;j<arr.length;j++) {
				total+=s[arr[i]][arr[j]];
				total+=s[arr[j]][arr[i]];
			}
		}
		return total;
	}
	
	
	
}

