package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * BOJ 17471: �Ը������
 * 1. bfs�� �׷����� ������ ���Ѵ�.
 * 	1-1. 1���� �׷����� ���Դٸ� �� ���� �׷����� �ɰ��� �Ѵ�.(�κ������� Ȱ���Ѵ�.)
 * 	1-2. 2���� �׷����� ���Դٸ� �� ���� ���̰� �ּڰ�
 * 	1-3. 3���� �׷����� ���Դٸ� �� ���� ���ű��� ���� �� ����.(-1)
 * 
 */
public class BOJ_17471_�Ը��Ǵ���_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	
	static int N;						// ������ ����
	static int[] population;				// �α� ���� �����ϴ� �迭
	static List<Integer>[] adjList;		// �� ������ ������ ������ �����ϴ� ����Ʈ
	
	static boolean[] isRegionA;			// A ������ �� ����
	static boolean[] visited;			// bfs, dfs�� ����� �湮 ǥ�� �迭
	static int minDiff=Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// ������ ���� �Է¹޴´�.
		N=Integer.parseInt(br.readLine().trim());
		population=new int[N+1];
		adjList=new ArrayList[N+1];
		
		// �α� ���� �Է¹޴´�.
		st=new StringTokenizer(br.readLine().trim());
		for (int region=1;region<=N;region++) {
			population[region]=Integer.parseInt(st.nextToken());
		}
		
		// �� ������ ���� ���� ������ �����Ѵ�.
		for (int idx=0;idx<=N;idx++) {
			adjList[idx]=new ArrayList<>();
		}
		for (int region=1;region<=N;region++) {
			st=new StringTokenizer(br.readLine().trim());
			int adjCnt=Integer.parseInt(st.nextToken());
			
			// ������ ������ �ִ� ��� ��������Ʈ�� �����Ѵ�.
			for (int idx=0;idx<adjCnt;idx++) {
				adjList[region].add(Integer.parseInt(st.nextToken()));
			}
		}
		// �� ���ű��� ���� �� ���� ���
		// �Է� ���� ������ �������� �׷����� ������ 3�� �̻��� �� �� ���ű��� ���� �� ����.
		int graphCnt=0;
		visited=new boolean[N+1];
		for (int region=1;region<=N;region++) {
			if (visited[region]) continue;
			bfs(region);
			graphCnt+=1;
		}
		// �׷����� 3���� ���, �� ���� ���ű��� ���� �� ����.
		if (graphCnt>=3) {
			System.out.println(-1);
		}
		// �׷����� 2���� ���, ������ ���ű��� �����ϴ� ��츸 �����Ѵ�.
		else if (graphCnt==2) {
			visited=new boolean[N+1];
			bfs(1);
			int regionA=0;
			int regionB=0;
			for (int region=1;region<=N;region++) {
				if (visited[region]) regionA+=population[region];
				else {
					regionB+=population[region];
				}
			}
			System.out.println(Math.abs(regionA-regionB));
		}
		// �׷����� 1���� ���, 2���� �׷����� �ɰ����Ѵ�.
		else {
			// ������ �κ������� ���Ѵ�.(nC1, nC2, nC3..)
			visited=new boolean[N+1];
			isRegionA=new boolean[N+1];
			getSubset(1,0);
			System.out.println(minDiff);
		}
	}
	
	// �׷����� ����Ǿ��ִ��� Ȯ��
	public static void bfs(int start) {
		Deque<Integer> que=new ArrayDeque<>();
		que.offer(start);
		visited[start]=true;
		
		while (!que.isEmpty()) {
			int curRegion=que.poll();
			
			for (int adjRegion:adjList[curRegion]) {
				if (visited[adjRegion])continue;
				que.offer(adjRegion);
				visited[adjRegion]=true;
			}
		}
	}
	// ���� ���� �� �������� ���� ����Ǿ� �ִ��� �˻�
	public static boolean isConnect(int start) {
		Deque<Integer> que=new ArrayDeque<>();
		que.offer(start);
		visited[start]=true;
		
		while (!que.isEmpty()) {
			int curRegion=que.poll();
			
			// ������ ����
			for (int adjRegion:adjList[curRegion]) {
				// �̹� �湮�� ��� ����
				if (visited[adjRegion]) continue;
				// �ٸ� ������ ��� ����
				if (isRegionA[start]!=isRegionA[curRegion]) continue;
				que.offer(adjRegion);
				visited[adjRegion]=true;
			}
		}
		// ������ ��� ������ Ž���� ��
		// start�� ���� ���� �� �湮���� ���� ������ �ִٸ� return false
		boolean isValid=true;
		for (int region=1;region<=N;region++) {
			// ���� ������ ���
			// �湮�� �ȵ� ���̶�� ������ �� ����.
			if (isRegionA[region]==isRegionA[start] && !visited[region]) {
				isValid=false;
				break;
			}
		}
		return isValid;
	}
	
	// �κ����� ���ϱ�
	public static void getSubset(int cnt, int chosen) {
		if (cnt==N+1) {
			if (chosen==0 || chosen==N) return;
			// ������ A�� B���� ���Ἲ üũ
			boolean connect=true;
			visited=new boolean[N+1];
			for (int region=1;region<=N;region++) {
				// A ������ ���
				if (isRegionA[region]) {
					connect=isConnect(region);
					break;
				}
			}
			// A������ ���� ����Ǿ��ٸ�
			// B���� ���Ἲ �˻�
			if (connect) {
				visited=new boolean[N+1];
				for (int region=1;region<=N;region++) {
					// B ������ ���
					if (!isRegionA[region]) {
						connect=isConnect(region);
						break;
					}
				}
			}
			// 2 ���� ���� ��� ���� ������ ���
			if (connect) {
				int regionA=0;
				int regionB=0;
				// �� ������ �α� ���� ���Ѵ�.
				for (int idx=1;idx<=N;idx++) {
					if (isRegionA[idx]) regionA+=population[idx];
					else regionB+=population[idx];
				}
				// �ּ� �α� ���̸� ���Ѵ�.
				minDiff=Math.min(minDiff, Math.abs(regionA-regionB));
			}
			return;
		}
		// A ������ �� �� �ִ� ������ ����.
		isRegionA[cnt]=true;
		getSubset(cnt+1,chosen+1);
		isRegionA[cnt]=false;
		getSubset(cnt+1,chosen);
		
	}

}
	
	
