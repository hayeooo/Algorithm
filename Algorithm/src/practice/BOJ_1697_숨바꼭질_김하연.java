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
 * K��ġ�� ���� ���� �����ϴ� �ð��� �ּ� �ð����� �̿��Ѵ�.
 * 
 */
public class BOJ_1697_���ٲ���_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;				// �������� ���� ��ġ
	static int K;				// ������ ��ġ
	static int[] time;			// �湮�� ĭ�� �ּ� �ð��� �����Ѵ�.
	
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// �������� ��ġ�� �Է� �޴´�.
		N=Integer.parseInt(st.nextToken());
		
		// ������ ��ġ�� �Է� �޴´�.
		K=Integer.parseInt(st.nextToken());
		
		// ��ġ�� �� �ִ� ���̸� �ʱ�ȭ�Ѵ�. (0<=N<=100,000)
		time=new int[100001];
		
		// �湮 ���ο� �ּ� �ð��� �Բ� �����Ѵ�.
		// -1�̸� ���� �湮���� ���� ��ġ
		Arrays.fill(time, -1);
		
		// ���� ��ġ�� ã�� �ּ� �ð��� ���Ѵ�.
		find(N);
		
		// ����� ����Ѵ�.
		System.out.println(time[K]);
	}
	
	// ���� ��ġ ã�� �ּ� �ð� ���ϱ�
	public static void find(int x) {
		
		Deque<Integer> que=new ArrayDeque<>();
		
		// ���� ��ġ�� ť�� �ִ´�.
		que.offer(x);
		// ���� ��ġ �湮 ǥ�ø� �Ѵ�.
		time[x]=0;
		
		// ť�� ������� ���� ���� �ݺ��Ѵ�.
		while (!que.isEmpty()) {
			int cur=que.poll();
			
			// ť���� ���� ����
			// ������ ��ġ�� ��� bfs�� �ߴ��Ѵ�.
			if (cur==K) {
				break;
			}
			// x-1�� ���� �ȿ� ����
			// �湮���� ���� ��ġ�� ���
			if (cur-1>=0 && cur-1<=100000 && time[cur-1]==-1) {
				// 1�� ������Ų ��
				time[cur-1]=time[cur]+1;
				// ť�� �ִ´�.
				que.offer(cur-1);
			}
			
			// x+1�� ���� �ȿ� ����
			// �湮���� ���� ��ġ�� ���
			if (cur+1>=0 && cur+1<=100000 && time[cur+1]==-1) {
				// 1�� ������Ų ��
				time[cur+1]=time[cur]+1;
				// ť�� �ִ´�.
				que.offer(cur+1);
			}
			
			// 2*x�� ���� �ȿ� ����
			// �湮���� ���� ��ġ�� ���
			if (2*cur>=0 && 2*cur<=100000 && time[2*cur]==-1) {
				// 1�� ������Ų ��
				time[2*cur]=time[cur]+1;
				// ť�� �ִ´�.
				que.offer(2*cur);
			}
		}
		
	}

}
