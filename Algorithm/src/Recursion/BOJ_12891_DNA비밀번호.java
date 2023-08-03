package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 12891 : DNA 비밀번호
 * 1. 임의로 만든 문자열 길이 S와 사용할 부분문자열 길이 P 입력 받기
 * 2. 첫 번째 문자부터 부분문자열 길이까지 등장한 {'A','C','G','T} 개수 세기
 * 3. 부분 문자열 시작점과 끝점을 옮기며, 기존 시작점에 등장한 문자는 하나 빼고, 끝점에 등장한 문자 개수는 하나 더하는 식으로
 * 	  전체 문자열 끝까지 진행
 * 	3-1. 시작점과 끝점을 옮길 때마다 민호가 만들 수 있는 비밀번호인지 점검한다.
 * */

public class BOJ_12891_DNA비밀번호 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int S;						// 임의로 만든 DNA 문자열 길이
	static int P;						// 비밀번호로 사용할 부분문자열 길이
	static char[] totalStr;				// 임의로 만든 DNA 문자열
	static int[] count;					// 부분문자열에 포함되어야 할 {'A','C','G','T}의 최소 개수
	static int[] tmp=new int[4];		// 현 부분문자열에 포함된 문자의 개수를 담은 배열
	static final short A=0;				// 부분 문자열에 포함되어야 할 A의 개수 index
	static final short C=1;
	static final short G=2;
	static final short T=3;
	
	static int kind;					// 비밀번호의 종류의 수
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// DNA 문자열 길이 S와 부분문자열 길이 P 입력
		S=Integer.parseInt(st.nextToken());
		P=Integer.parseInt(st.nextToken());
		
		// 전체 문자열 입력
		totalStr=br.readLine().trim().toCharArray();
		
		st=new StringTokenizer(br.readLine());
		
		// 부분문자열에 포함되어야 할 {'A','C','G','T}의 최소 개수를 담은 배열
		count=new int[4];
		// 각 문자의 최소 개수 입력
		for (int idx=0;idx<4;idx++) {
			count[idx]=Integer.parseInt(st.nextToken());
		}
		
		// 첫 문자에서부터 P 길이 안에 속한 문자의 개수 구하기
		for (int idx=0;idx<P;idx++) {
			setCount(idx,1);
		}
		// 비밀번호가 되는지 확인
		isValid();
		
		// 부분문자열 시작점과 끝점을 하나씩 옮겨가며
		// 부분문자열에 포함된 문자 개수 갱신
		int startIdx=0;
		int endIdx=P-1;
		while (true) {
			setCount(startIdx,-1);	// 다음 시작점으로 바뀌기 전, 기존 시작점에 위치한 문자 개수 하나 뺀다.
			startIdx+=1;			// 다음 시작점으로 옮긴다.
			endIdx+=1;				// 다음 끝점으로 옮긴다.
			if (endIdx>=S) {		// 끝점이 전체 문자열의 범위를 넘어선 경우, 반복을 중단한다.
				break;
			}
			setCount(endIdx,1);		// 범위를 벗어나지 않은 경우, 끝점에 위치한 문자의 개수를 하나 추가한다.
			isValid();				// 비밀번호가 되는지 확인한다.
		}
		// 결과 출력
		System.out.println(kind);	
	}
	// idx 위치에 있는 문자의 개수 업데이트하는 함수
	public static void setCount(int idx,int sign) {	// sign: -1이면 개수 하나 빼기, 1이면 개수 하나 더하기
		switch(totalStr[idx]) {						// 각 문자에 해당하는 개수 업데이트
		case 'A':
			tmp[A]+=1*sign;
			break;
		case 'C':
			tmp[C]+=1*sign;
			break;
		case 'G':
			tmp[G]+=1*sign;
			break;
		case 'T':
			tmp[T]+=1*sign;
			break;
		}
	}
	// 비밀번호가 될 수 있는지 확인하는 메소드
	public static void isValid() {				
		boolean valid=true;					// 일단 된다고 가정
		for (int idx=0;idx<4;idx++) {
			if (tmp[idx]<count[idx] ) {		// 속해야하는 최소 문자보다 작다면 비밀번호를 만들 수 없음
				valid=false;				// 반복문 중단
				break;
			}
		}									// 반복문을 중단하지 않고 끝까지 돌았다면 비밀번호를 만들 수 있다.
		if (valid) {
			kind+=1;						// 비밀번호 종류 하나 더한다.
		}
	}

}
