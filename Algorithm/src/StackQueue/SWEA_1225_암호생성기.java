package StackQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
/*
 * SWEA 1225: 암호생성기
 * n개의 수를 처리하여 8자리 암호를 생성한다.
 * 1. 10개의 테스트만큼 반복한다.
 * 2. 8개의 숫자를 입력 받는다.
 * 3. 첫 번째 숫자를 1 감소한 후, 맨 뒤로 보내고 다음 첫 번째 수는 2, 맨 뒤로....5 감소, 맨 뒤로 옮기기를 반복한다.
 * 4. 숫자가 감소할 때 0보다 작아지는 경우 0으로 유지되며 프로그램이 종료된다. 8자리의 숫자 암호가 완성된다.
 * */
public class SWEA_1225_암호생성기 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static final int T=10;						// 전체 테스트 케이스 수
	static int N;								// 테스트 케이스 번호
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();					// 결과 문자열을 저장하기 위한 객체 생성
		
		for (int test_case=1;test_case<=T;test_case++) {	// 10개의 테스트 케이스 반복
			N=Integer.parseInt(br.readLine().trim());		// 테스트케이스 번호 입력
			
			Deque<Integer> que=new ArrayDeque<>();			// 8개의 숫자를 저장할 queue
			st=new StringTokenizer(br.readLine().trim());	// 8개 숫자를 입력 받기
			
			// queue에 숫자 추가
			for (int idx=0;idx<8;idx++) {
				que.offer(Integer.parseInt(st.nextToken()));
			}
			// 뺄 숫자 크기
			int operand=1;
			
			// 0 이 나올 때까지 반복
			while (true) {
				int target=que.poll();			// que에서 맨 앞자리 숫자를 뽑는다.
				int check=target-operand;		// operand만큼 감소시킨다.
				if (check<=0) {					// 계산한 값이 0보다 같거나 작은 경우
					check=0;					// 계산값을 0을 설정하고
					que.offer(check);			// que의 맨 뒤에 저장한다.
					break;						// 반복을 종료시킨다.
				}
				que.offer(check);				// 0 이 아닌 경우, que에 계산 값을 저장하고
				operand=(operand%5)+1;			// 뺄 값의 크기를 변경한다.
			}
			sb.append("#"+test_case+" ");		// 테스트케이스 별로 결과 문자열을 추가한다.
			for (int idx=0;idx<8;idx++) {
				sb.append(que.poll()+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);					// 결과를 출력한다.
	}
}
