package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * BOJ 3040: 백설공주와 일곱난쟁이
 * 
 * 9명의 난쟁이 모자에 쓰여있는 숫자 중에 7명 모자의 숫자 합이 100이 되는 모자 숫자를 출력한다.
 * 
 * */
public class BOJ_3040_백설공주와일곱난쟁이_김하연 {
	static BufferedReader br;
	static int[] arr;				// 9명의 난쟁이 모자 숫자 정보를 저장하는 배열
	static int[] comb;				// 선택한 난쟁이 모자 인덱스 조합을 저장하는 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 난쟁이 모자 숫자 정보를 입력 받는다.
		arr=new int[9];	
		for (int idx=0;idx<9;idx++) {
			arr[idx]=Integer.parseInt(br.readLine());
		}
		
		// 난쟁이 모자의 조합을 저장하는 배열 크기 초기화
		comb=new int[7];
		
		// 7개의 모자 숫자 조합을 고른 후,
		// 합이 100인 경우 7개의 모자 숫자를 출력한다.
		combinations(0,0,0);
	}
	// 모자 조합을 구하고 합이 100인 경우 7개의 모자 숫자를 출력한다.
	public static void combinations(int cnt, int startIdx,int sum) {
		if (cnt==7) {					// 7개의 조합이 완성된 경우
			if (sum==100) {				// 합이 100일 때, 모자 숫자들을 출력한다.
				for (int combIdx=0;combIdx<7;combIdx++) {
					System.out.println(arr[comb[combIdx]]);
				}
			}
			return;
		}
		// 모자 숫자 조합을 고른다.
		for (int idx=startIdx;idx<9;idx++) {
			// 선택한 모자 인덱스를 저장한다.
			comb[cnt]=idx;
			// 선택된 숫자 개수, 선택할 다음 모자 인덱스 시작 범위와, 현재까지 모자숫자 합을 매개변수로 전달한다.
			combinations(cnt+1,idx+1,sum+arr[idx]);
		}
	}

}
