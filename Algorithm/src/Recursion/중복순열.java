package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * 중복순열 구현
 * 1. nPr의 n과 r 입력 받기
 * 2. 1부터 n까지 순회하며 r 개수만큼 중복하여 숫자 나열
 * 3. 결과 배열을 출력
 * */
public class 중복순열 {
	static BufferedReader br;
	static int N;					// nPr의 n
	static int R;					// nPr의 r
	static int[] res;				// 결과를 담을 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		N=Integer.parseInt(br.readLine().trim());			
		R=Integer.parseInt(br.readLine().trim());
		
		// 입력값에 따른 결과 배열 크기 초기화
		res=new int[R];
		
		// 중복 순열 구하기
		dupPerm(0);
	}
	
	public static void dupPerm(int cnt) {
		// R자리 숫자를 모두 조합시켰을 경우
		// 결과 출력 및 재귀 종료
		if (cnt==R) {
			System.out.println(Arrays.toString(res));
			return;
		}
		// 1부터 N까지 순회하며, 숫자를 결과 배열에 저장
		// 중복을 허용하기 때문에 중복 체크를 하지 않는다.
		for (int number=1;number<=N;number++) {
			res[cnt]=number;
			dupPerm(cnt+1);
		}
	}
}
