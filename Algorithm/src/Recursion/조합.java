package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * 조합, 중복 X
 * 순서는 의미가 없기 때문에 이미 조합한 숫자는 생략
 * 결과에 넣은 숫자 값 다음 범위에 있는 숫자들이 조합 대상이다.
 * */
public class 조합 {
	
	static BufferedReader br;
	
	static int N;			// 1..N까지 숫자 범위
	static int R;			// 뽑을 숫자의 개수
	static int[] res;		// 결과를 담을 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		N=Integer.parseInt(br.readLine().trim());
		R=Integer.parseInt(br.readLine().trim());
		
		// 결과를 담을 배열 크기 초기화
		res=new int[R];
		
		// 조합 메소드 실행
		combinations(0,1);
	}
	
	public static void combinations(int cnt, int start) {	// cnt: 담은 숫자의 개수, start: 넣을 숫자의 시작 범위
		if (cnt==R) {										// R 만큼 숫자를 담았을 경우 결과 출력 및 재귀 종료
			System.out.println(Arrays.toString(res));
			return;
		}
		for (int num=start;num<=N;num++) {					// 시작 범위부터 끝까지 숫자를 조합
			res[cnt]=num;
			combinations(cnt+1,num+1);						// 다음 자리 수, 조합 찾기(중복 제거)
		}
	}

}
