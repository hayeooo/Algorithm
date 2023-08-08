package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * ArrayList를 사용한 구현 문제
 * 1. 벨트가 각 칸위에 있는 로봇과 함께 회전
 * 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동, 이동할 수 없다면 가만히 있는다.
 * 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
 * 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료
 * 
 * 로봇을 올리는 위치에 올리거나 로봇이 어떤 칸으로 이동했을 때 칸의 내구도가 0인지 확인
 * 
 * */
public class BOJ_20055_컨베이어벨트위의로봇 {
	static BufferedReader br;
	static StringTokenizer st;
	static int N;					// A1...AN개의 칸
	static int K;					// 0이 K개 이상이라면 과정 종료
	static List<int[]> conveyer;	// 컨베이어 벨트
	static int stage=0;				// 단계
	static int zeroCount=0;
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine());
		
		// N, K 입력
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		// 컨베이어벨트 초기화
		st=new StringTokenizer(br.readLine());
		conveyer=new ArrayList<int[]>();
		for (int idx=0;idx<2*N;idx++) {
			int durability=Integer.parseInt(st.nextToken());
			if (durability==0) {
				zeroCount+=1;
			}
			conveyer.add(new int[] {0,durability});	// 로봇이 있으면 1, 없으면 0
		}
		
		// 0의 개수가 K미만일 때
		while (zeroCount<K) {
			stage+=1;					// 현재 단계를 1 추가
			
			// 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전
			// 마지막 칸은 맨 첫번째 칸으로 옮긴다.
			int[] moved=conveyer.remove(2*N-1);
			conveyer.add(0,moved);
			
			// 회전하였을 때, N-1 위치에 로봇이 있을 경우
			// 로봇을 내린다.
			conveyer.get(N-1)[0]=0;
			
			// 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 이동
			for (int idx=N-2;idx>=0;idx--) {		// N-2 위치부터 0번째까지 순서대로 로봇을 오른쪽으로 옮긴다.
				int[] cur=conveyer.get(idx);		// 현재 칸의 내구성과 로봇 유무
				if (cur[0]==1) {					// 해당 칸에 로봇이 있다면
					int[] next=conveyer.get(idx+1);	// 다음 칸으로 옮길 칸 정보 가져온다.
					if (next[0]==0 && next[1]>=1) {	// 다음 칸에 로봇이 없고, 내구성이 1 이상인경우
						cur[0]=0;					// 로봇을 옮긴다.
						next[0]=1;
						next[1]-=1;					// 내구성을 1 줄였을 때, 0이 나온다면
						if (next[1]==0) zeroCount+=1;	// 0의 개수를 추가한다.
						if (idx+1==N-1) next[0]=0;		// 옮긴 위치가 로봇을 내리는 위치라면 로봇을 내린다.
					}
				}
			}
			// 로봇을 올리는 경우
			int[] start=conveyer.get(0);
			// 올리는 위치에 칸의 내구도가 0이 아니라면 로봇 올림
			if (start[1]>0) {
				start[0]=1;
				start[1]-=1;
				// 올렸을 때, 내구도가 0이라면 0의 개수 추가
				if (start[1]==0) {
					zeroCount+=1;
				}
			}
		}
		// 0의 개수가 충족되었다면, 진행한 단계 수 출력
		System.out.println(stage);
	}
}
