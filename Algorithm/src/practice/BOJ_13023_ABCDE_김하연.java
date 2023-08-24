package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 13023: ABCDE
 * a-b-c-d-e�� ģ�����谡 �ִ��� Ȯ���ϴ� ����
 * ģ�� ���谡 4���� Ȯ��
 * ģ�����踦 ��� Ž���غ���.
 */
public class BOJ_13023_ABCDE_���Ͽ� {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;					 // ����� ��
	static int M;					// ģ�� ������ ��
	static boolean[] visited;		// Ž�� ���θ� ǥ���ϴ� �迭
	static boolean flag;			// ģ�� ���谡 4�� �� �� �ִ��� ���θ� �����ϴ� ����
	static List<Integer>[] graph;	// ģ�� ���踦 �����ϴ� ����Ʈ
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		st=new StringTokenizer(br.readLine().trim());
		
		// N, M�� �Է¹޴´�.
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		
		// ģ�� ���踦 ������ ����Ʈ �迭
		graph=new ArrayList[N];
		
		for (int idx=0;idx<N;idx++) {
			graph[idx]=new ArrayList<>();
		}
		
		// M���� �ٿ��� ģ�� ���踦 �Է� ������ ������ �����Ѵ�.
		for (int idx=0;idx<M;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			
			int friend1=Integer.parseInt(st.nextToken());
			int friend2=Integer.parseInt(st.nextToken());
			
			graph[friend1].add(friend2);
			graph[friend2].add(friend1);
		}
		// �� ����� ģ�� ���踦 Ž���Ѵ�.
		for (int person=0;person<N;person++) {
			visited=new boolean[N];
			dfs(person,0);
			// �̹� ģ�� ���谡 4�� ���谡 �����Ѵٸ� �ݺ��� �����Ѵ�.
			if (flag) break;
		}
		System.out.println(flag?1:0);
	}
	// ���� ����� ���� ���� �Ű������� �޴´�.
	public static void dfs(int p,int cnt) {
		if (cnt==4) {
			flag=true;
			return;
		}
		// ���� Ž���ϴ� ��� �湮 ó��
		visited[p]=true;
		// ���� �湮�� ���·� ���� ģ�� ���� Ž��
		for (int nextP:graph[p]) {
			if (visited[nextP]) continue;
			dfs(nextP,cnt+1);
		}
		// ����� �ٸ� ģ�� ���赵 Ž���غ���.
		visited[p]=false;	// ��Ʈ��ŷ
		
	}

}
