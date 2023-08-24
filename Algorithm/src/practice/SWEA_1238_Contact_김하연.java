package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
/*
 * SWEA 1238: Contact
 * 
 * 10개의 테스트 케이스
 * 첫 번째 줄에 입력 받는 데이터의 길이와 시작점이 주어진다.
 * 다음 줄에 입력 받는 데이터는 {from, to, from ,to}의 순서이다.
 * 동일한 {from,to} 쌍이 여러 번 반복되는 경우가 있다.
 * 
 * 비상연락망과 연락을 시작하는 당번에 대한 정보가 주어질 때
 * 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람을 구해 출력한다.
 * 
 */
public class SWEA_1238_Contact_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int T=10;					// 테스트 케이스 수
	static int L;							// 데이터 길이
	static int S;							// 시작점
	
	static List<Integer>[] graph;
	static int[] visited;
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			
			// 데이터 길이와 시작점을 입력받는다.
			L=Integer.parseInt(st.nextToken());
			S=Integer.parseInt(st.nextToken());
			
			// 연락을 저장할 그래프 크기를 초기화한다.
			graph=new ArrayList[101];
			
			for (int idx=0;idx<=100;idx++) {
				graph[idx]=new ArrayList<Integer>();
			}
			
			// 연락 시작점과 끝점을 입력받고 그래프에 저장한다.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<L/2;idx++) {
				int from=Integer.parseInt(st.nextToken());
				int to=Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
			}
			// 번호의 연락 유무를 저장하는 배열 크기를 초기화한다.
			visited=new int[101];
			// 모든 연락을 돌린다.
			bfs(S);
			// 연락을 돌린 후, 가장 나중에 연락 받은 큰 번호를 출력한다.
			int maxValue=0;
			int maxIdx=0;
			for (int num=1;num<101;num++) {
				if (visited[num]==0) continue;
				// 최댓값이 같다면 큰 숫자인 num으로 업데이트
				if (visited[num]==maxValue) {
					maxIdx=num;
				}
				else if (visited[num]>maxValue) {
					maxValue=visited[num];
					maxIdx=num;
				}
			}
			sb.append("#"+test_case+" "+maxIdx+"\n");
		}
		System.out.print(sb);
	}
	
	public static void bfs(int start) {
		Deque<Integer> que=new ArrayDeque<>();
		que.offer(start);
		visited[start]=1;
		
		while(!que.isEmpty()) {
			int curNum=que.poll();
			
			// 현재 번호에서 이동할 수 있는 번호들을 탐색
			for (int nextNum:graph[curNum]) {
				// 이미 연락을 받은 번호라면 무시
				if (visited[nextNum]>0) continue;
				que.offer(nextNum);
				visited[nextNum]=visited[curNum]+1;
			}
		}
	}

}
