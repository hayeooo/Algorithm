package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * dfs-> stackoverflow
 * : bfs
 * K위치에 가장 먼저 도착하는 시간이 최소 시간임을 이용한다.
 * 
 */
public class BOJ_1697_숨바꼭질_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;				// 수빈이의 현재 위치
	static int K;				// 동생의 위치
	static int[] time;			// 방문한 칸에 최소 시간을 저장한다.
	
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// 수빈이의 위치를 입력 받는다.
		N=Integer.parseInt(st.nextToken());
		
		// 동생의 위치를 입력 받는다.
		K=Integer.parseInt(st.nextToken());
		
		// 위치할 수 있는 길이를 초기화한다. (0<=N<=100,000)
		time=new int[100001];
		
		// 방문 여부와 최소 시간을 함께 저장한다.
		// -1이면 아직 방문하지 않은 위치
		Arrays.fill(time, -1);
		
		// 동생 위치를 찾는 최소 시간을 구한다.
		find(N);
		
		// 결과를 출력한다.
		System.out.println(time[K]);
	}
	
	// 동생 위치 찾는 최소 시간 구하기
	public static void find(int x) {
		
		Deque<Integer> que=new ArrayDeque<>();
		
		// 현재 위치를 큐에 넣는다.
		que.offer(x);
		// 현재 위치 방문 표시를 한다.
		time[x]=0;
		
		// 큐가 비어있지 않을 동안 반복한다.
		while (!que.isEmpty()) {
			int cur=que.poll();
			
			// 큐에서 나온 값이
			// 동생의 위치인 경우 bfs를 중단한다.
			if (cur==K) {
				break;
			}
			// x-1이 범위 안에 들어가고
			// 방문하지 않은 위치인 경우
			if (cur-1>=0 && cur-1<=100000 && time[cur-1]==-1) {
				// 1초 증가시킨 후
				time[cur-1]=time[cur]+1;
				// 큐에 넣는다.
				que.offer(cur-1);
			}
			
			// x+1이 범위 안에 들어가고
			// 방문하지 않은 위치인 경우
			if (cur+1>=0 && cur+1<=100000 && time[cur+1]==-1) {
				// 1초 증가시킨 후
				time[cur+1]=time[cur]+1;
				// 큐에 넣는다.
				que.offer(cur+1);
			}
			
			// 2*x가 범위 안에 들어가고
			// 방문하지 않은 위치인 경우
			if (2*cur>=0 && 2*cur<=100000 && time[2*cur]==-1) {
				// 1초 증가시킨 후
				time[2*cur]=time[cur]+1;
				// 큐에 넣는다.
				que.offer(2*cur);
			}
		}
		
	}

}
