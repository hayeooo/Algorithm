package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 부분집합
 * N개의 숫자를 입력 받아 0~N개의 조합을 출력
 * 모든 숫자는 2가지 경우를 가진다.
 * 	1. 해당 숫자가 포함된 경우
 * 	2. 해당 숫자가 포함되지 않은 경우
 * 따라서, 부분집합은 2^N개가 나온다.
 * */
public class 부분집합 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;							// 숫자의 개수
	static int R;							// 부분집합 최대 크기
	static int[] numbers;					// 숫자를 저장하는 배열
	static boolean[] isSelected;				// 숫자를 사용했는지 여부를 담는 배열
	
	public static void main(String[] args) throws IOException {
		
		// 입력
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		
		// 숫자를 저장할 배열 크기 초기화
		numbers=new int[N];
		// 숫자가 선택되었는지 여부를 판단하기 위한 배열 크기 초기화
		isSelected=new boolean[N];
		
		// 배열 입력 받음
		st=new StringTokenizer(br.readLine());
		for (int idx=0;idx<N;idx++) {
			numbers[idx]=Integer.parseInt(st.nextToken());
		}
		// 부분집합 메소드 호출
		getSubset(0);
	}
	
	public static void getSubset(int cnt) {	// cnt: 고려한 숫자의 개수
		if (cnt==N) {						// 모든 숫자를 고려하였다면
			for (int idx=0;idx<N;idx++) {	// 선택된 숫자들을 출력
				if (isSelected[idx]) {
					System.out.print(numbers[idx]+" ");
				}
			}
			System.out.println();
			return;
		}
		isSelected[cnt]=true;				// 해당 숫자를 넣었을 때,
		getSubset(cnt+1);					// 부분집합 구하기
		
		isSelected[cnt]=false;				// 해당 숫자를 넣지 않았을 때,
		getSubset(cnt+1);					// 부분집합 구하기
	}

}
