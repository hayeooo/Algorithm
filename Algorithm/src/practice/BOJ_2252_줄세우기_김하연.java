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
 * ���� ����
 * ���� �˰����� ����, ������ ������ �ִ� �Ϸ��� �۾��� ���ʴ�� �����ؾ� �� ��
 * ����Ŭ�� ���� ���� �׷����� ��� ��带 '���⼺�� �Ž����� �ʵ��� ������� �����ϴ� ��'
 * 
 * �� �л��� Ű�� ���� ���
 * A -> B: A�� B�� �տ� ���� �Ѵٴ� �ǹ�
 * 
 * ���� ������ ���� ���� ���� ���ͼ� �����Ѵ�.
 * ����� ������ ������ �ϳ��� ���̸鼭 ��带 ������.
 */
public class BOJ_2252_�ټ����_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N; 				// �л� ��
	static int M;				// Ű�� ���� Ƚ��
	static List<Integer>[] graph;	// �л����� Ű �� ����� �����ϴ� List �迭
	static int[] indegree;			// �� �л����� ���� ���� ������ �����ϴ� �迭
	
	static Deque<Integer> que;				// �������Ŀ� ����� queue
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		st=new StringTokenizer(br.readLine().trim());
		
		// �л� ���� Ű�� ���� Ƚ���� �Է� �޴´�.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// �׷��� ũ�� �ʱ�ȭ
		graph=new LinkedList[N+1];
		indegree=new int[N+1];
		
		// �׷��� linkedList ��ü ����
		for (int idx=0;idx<N+1;idx++) {
			graph[idx]=new LinkedList<>();
		}
		
		for (int idx=0;idx<M;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			
			// from �л��� to �л��� �տ� �����Ѵٴ� �ǹ��̴�.
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			// �׷��� ���� ���� �� ���� ���� 1 ����
			graph[from].add(to);
			indegree[to]+=1;
		}
		
		que=new ArrayDeque<>();
		// ���� ������ 0�� �л����� ť�� �ִ´�.
		for (int idx=1;idx<=N;idx++) {
			if (indegree[idx]==0) {
				que.offer(idx);
			}
		}
		

		// ��� �л��� ó���� ������ �ݺ��Ѵ�.
		while (!que.isEmpty()) {
			int curStd=que.poll();
			sb.append(curStd+" ");
			
			// ���� �л��� ����� ������ �����ϸ鼭
			// ���������� 1�� ���ҽ�Ų��.
			for (int nextStd:graph[curStd]) {
				indegree[nextStd]-=1;
				
				// ���� ������ 0�� �Ǿ��� ��
				// ť�� �����Ѵ�.
				if (indegree[nextStd]==0) {
					que.offer(nextStd);
				}
			}
		}
		// ����� ����Ѵ�.
		System.out.println(sb);
	}

}
