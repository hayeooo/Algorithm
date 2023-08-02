package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * 중복 조합
 * 순서는 의미가 없지만, 중복된 숫자를 사용할 수 있다.
 * 이미 조합한 숫자 포함(중복된 숫자)하여 다음 범위의 숫자를 조합한다.
 * */
public class 중복조합 {
	static BufferedReader br;
	static int N;					// 1..N 범위의 숫자 사용
	static int R;					// R 길이의 조합
	static int[] res;				// 결과를 담을 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		N=Integer.parseInt(br.readLine());
		R=Integer.parseInt(br.readLine());
		
		// 결과를 담을 배열 크기 초기화
		res=new int[R];
		
		// 중복 조합 메소드 수행
		dupComb(0,1);
	}
	
	public static void dupComb(int cnt,int start) {			// cnt: 현재까지 담은 숫자의 개수, start: 결과를 담을 숫자의 시작 범위
		if (cnt==R) {										// R 길이를 만족할 경우, 결과 출력 및 재귀 종료
			System.out.println(Arrays.toString(res));
			return;
		}
		for (int num=start;num<=N;num++) {					// 시작 범위부터 조합할 숫자 결과 배열에 넣기
			res[cnt]=num;
			dupComb(cnt+1,num);								// 현 숫자(중복 포함)부터 다음 자리 숫자를 찾는다.
		}
	}

}
