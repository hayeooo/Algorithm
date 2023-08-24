package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MSTPrimTest {
	
	static int V, adjMatrix[][];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
		V=Integer.parseInt(in.readLine());
		adjMatrix=new int[V][V];
		
		for (int i=0;i<V;i++) {
			StringTokenizer st=new StringTokenizer(in.readLine()," ");
			for (int j=0;j<V;j++) {
				adjMatrix[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		// �湮 ����(Ʈ�� ���� ǥ��)
		boolean[] visited=new boolean[V];
		int[] minEdge=new int[V];	// �ڽŰ� Ʈ���� ������ �� �ּ� ���� ���
			
		Arrays.fill(minEdge, Integer.MAX_VALUE);	// �ּҰ� ������ ���� ū ������ ����
		minEdge[0]=0;								// 0�� ������ Ʈ�� ������ ��������
		
		int result=0;								// �ּ� ���� Ʈ���� ���
		int min=0, minVertex=0;
		
		for (int c=0;c<V;c++) {
			minVertex=-1;
			min=Integer.MAX_VALUE;
			
			// step1: �̹湮(��Ʈ��) ���� �� �ּҰ�������� ������ ����
			for (int i=0;i<V;i++) {
				if(!visited[i] && min>minEdge[i]) {
					minVertex=i;
					min=minEdge[i];
				}
			}
			
			// step2: Ʈ�� ������ �߰�
			visited[minVertex]=true;		// �湮 ó��
			result+=min;					// ����Ʈ�� ��� ����
			
			// step3: Ʈ���� �߰��� ���ο� ���� �������� ��Ʈ�� �������� ���� ��� ���
			for (int i=0;i<V;i++) {
				if(!visited[i]&&adjMatrix[minVertex][i]!=0) {
					if (minEdge[i]>adjMatrix[minVertex][i]) {
						minEdge[i]=adjMatrix[minVertex][i];
					}
				}
			}
		}
	}

}
