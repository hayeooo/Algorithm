package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import com.sun.jmx.remote.internal.ArrayQueue;

/*
 * 위상 정렬
 * 정렬 알고리즘의 일종, 순서가 정해져 있는 일련의 작업을 차례대로 수행해야 할 때
 * 사이클이 없는 방향 그래프의 모든 노드를 '방향성에 거스르지 않도록 순서대로 나열하는 것'
 * 
 * 두 학생의 키를 비교한 결과
 * A -> B: A가 B의 앞에 서야 한다는 의미
 * 
 * 진입 차수가 적은 것이 먼저 나와서 정렬한다.
 * 연결된 간선의 차수를 하나씩 줄이면서 노드를 꺼낸다.
 */
public class BOJ_2252_줄세우기_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N; 				// 학생 수
	static int M;				// 키를 비교한 횟수
	static List<Integer>[] graph;	// 학생들의 키 비교 결과를 저장하는 List 배열
	static int[] indegree;			// 각 학생들의 진입 차수 정보를 저장하는 배열
	
	static Deque<Integer> que;				// 위상정렬에 사용할 queue
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		st=new StringTokenizer(br.readLine().trim());
		
		// 학생 수와 키를 비교한 횟수를 입력 받는다.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// 그래프 크기 초기화
		graph=new LinkedList[N+1];
		indegree=new int[N+1];
		
		// 그래프 linkedList 객체 생성
		for (int idx=0;idx<N+1;idx++) {
			graph[idx]=new LinkedList<>();
		}
		
		for (int idx=0;idx<M;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			
			// from 학생이 to 학생의 앞에 서야한다는 의미이다.
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			// 그래프 정보 저장 및 진입 차수 1 증가
			graph[from].add(to);
			indegree[to]+=1;
		}
		
		que=new ArrayDeque<>();
		// 진입 차수가 0인 학생부터 큐에 넣는다.
		for (int idx=1;idx<=N;idx++) {
			if (indegree[idx]==0) {
				que.offer(idx);
			}
		}
		

		// 모든 학생을 처리할 때까지 반복한다.
		while (!que.isEmpty()) {
			int curStd=que.poll();
			sb.append(curStd+" ");
			
			// 현재 학생에 연결된 간선을 제거하면서
			// 진입차수를 1씩 감소시킨다.
			for (int nextStd:graph[curStd]) {
				indegree[nextStd]-=1;
				
				// 진입 차수가 0이 되었을 때
				// 큐에 삽입한다.
				if (indegree[nextStd]==0) {
					que.offer(nextStd);
				}
			}
		}
		// 결과를 출력한다.
		System.out.println(sb);
	}

}
