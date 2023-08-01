package Recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 1244: 스위치 켜고 끄기
 * 학생들은 자신의 성별과 받은 수에 따라 스위치를 조작한다.
 * 1. 남학생인 경우(반복문)
 * 	- 본인이 받은 수의 배수일 경우, 스위치의 상태를 바꾼다.
 * 2. 여학생일 경우(재귀)
 *  - 본인 번호와 같은 번호의 스위치를 중심으로 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아 스위치의 상태를 모두 바꾼다.
 * */
public class BOJ_1244_스위치켜고끄기 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;						// 스위치의 개수
	static int[] switches;				// 스위치의 상태를 받아놓은 배열

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 스위치 개수 입력 및 스위치를 담는 배열 크기 초기화
		N = Integer.parseInt(br.readLine().trim());
		switches = new int[N + 1];
		
		// 공백 기준으로 String 분해
		st = new StringTokenizer(br.readLine().trim(), " ");
		
		// switch 상태 배열에 넣기
		for (int idx = 1; idx <= N; idx++) {
			switches[idx] = Integer.parseInt(st.nextToken());
		}
		
		// 학생 수 입력
		int std_num = Integer.parseInt(br.readLine().trim());
		
		// 학생 수만큼 반복
		for (int idx = 0; idx < std_num; idx++) {
			st = new StringTokenizer(br.readLine().trim(), " ");
			
			// 성별과 학생이 받은 스위치 수 입력
			int gender = Integer.parseInt(st.nextToken());
			int switch_num = Integer.parseInt(st.nextToken());
			
			// 남학생일 경우
			if (gender == 1) {
				boys(switch_num);
				
			} 
			// 여학생일 경우
			else if (gender == 2) {
				switches[switch_num]=Math.abs(switches[switch_num]-1);
				girls(switch_num-1,switch_num+1);
			}
		}
		// 배열 값 출력
		for (int idx=1;idx<=N;idx++) {
			System.out.print(switches[idx]+" ");
			if (idx%20==0) System.out.println();
		}
	}
	// 남학생일 경우
	// 반복문으로 스위치 수만큼 더하여(배수) 스위치한다.
	public static void boys(int switch_num) {
		for (int num=switch_num;num<N+1;num+=switch_num) {
			switches[num]=Math.abs(switches[num]-1);
		}
	}
	// 여학생일 경우
	// 좌측, 우측 스위치 상태를 비교하여 같으면 연쇄적으로 스위치하고
	// 아니라면 재귀를 종료한다.
	// 그 외에도, 배열의 범위를 넘어갈 경우, 재귀를 종료한다.
	public static void girls(int left, int right) {
		if (left<=0 || right>=N+1) return;
		if (switches[left]!=switches[right]) return;
		switches[left]=Math.abs(switches[left]-1);
		switches[right]=Math.abs(switches[right]-1);
		girls(left-1,right+1);
	}

}
