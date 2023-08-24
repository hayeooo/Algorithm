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
 * 10���� �׽�Ʈ ���̽�
 * ù ��° �ٿ� �Է� �޴� �������� ���̿� �������� �־�����.
 * ���� �ٿ� �Է� �޴� �����ʹ� {from, to, from ,to}�� �����̴�.
 * ������ {from,to} ���� ���� �� �ݺ��Ǵ� ��찡 �ִ�.
 * 
 * ��󿬶����� ������ �����ϴ� ����� ���� ������ �־��� ��
 * ���� ���߿� ������ �ް� �Ǵ� ��� �� ��ȣ�� ���� ū ����� ���� ����Ѵ�.
 * 
 */
public class SWEA_1238_Contact_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int T=10;					// �׽�Ʈ ���̽� ��
	static int L;							// ������ ����
	static int S;							// ������
	
	static List<Integer>[] graph;
	static int[] visited;
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			
			// ������ ���̿� �������� �Է¹޴´�.
			L=Integer.parseInt(st.nextToken());
			S=Integer.parseInt(st.nextToken());
			
			// ������ ������ �׷��� ũ�⸦ �ʱ�ȭ�Ѵ�.
			graph=new ArrayList[101];
			
			for (int idx=0;idx<=100;idx++) {
				graph[idx]=new ArrayList<Integer>();
			}
			
			// ���� �������� ������ �Է¹ް� �׷����� �����Ѵ�.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<L/2;idx++) {
				int from=Integer.parseInt(st.nextToken());
				int to=Integer.parseInt(st.nextToken());
				
				graph[from].add(to);
			}
			// ��ȣ�� ���� ������ �����ϴ� �迭 ũ�⸦ �ʱ�ȭ�Ѵ�.
			visited=new int[101];
			// ��� ������ ������.
			bfs(S);
			// ������ ���� ��, ���� ���߿� ���� ���� ū ��ȣ�� ����Ѵ�.
			int maxValue=0;
			int maxIdx=0;
			for (int num=1;num<101;num++) {
				if (visited[num]==0) continue;
				// �ִ��� ���ٸ� ū ������ num���� ������Ʈ
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
			
			// ���� ��ȣ���� �̵��� �� �ִ� ��ȣ���� Ž��
			for (int nextNum:graph[curNum]) {
				// �̹� ������ ���� ��ȣ��� ����
				if (visited[nextNum]>0) continue;
				que.offer(nextNum);
				visited[nextNum]=visited[curNum]+1;
			}
		}
	}

}
