package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 3289: ���μ� ����
 * �ʱ⿡ {1},{2}...{n}�� ���� n���� ������ �̷�� �ִ�.
 * ������ �����, �� ���Ұ� ���� ���տ� ���ԵǾ� �ִ��� Ȯ���ϴ� ������ �����Ѵ�.
 * 
 * ������ ������ �����ϰ�
 * �� ���Ұ� ���� ���տ� ���ԵǾ� �ִ��� Ȯ���ϴ� ���꿡 ����
 * ���� ���տ� �����ִٸ� 1��, �ƴϸ� 0�� ������� ���ٿ� �����Ͽ� ����Ѵ�.
 */
public class SWEA_3289_���μ�����_���Ͽ� {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;		// ��� ���ڿ��� ����� ���� ��ü
	
	static int T;					// �׽�Ʈ ���̽� ����
	static int n,m;					// ������ ����, ������ ����
	
	static int[] parents;			// �ش� ���Ұ� ���� ������ ��ǥ ���Ҹ� �����ϴ� �迭
	static int[] rank;				// �ش� ������ Ʈ�� ���̸� �����ϴ� �迭
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// �׽�Ʈ���̽� �� �Է¹޴´�.
		T=Integer.parseInt(br.readLine().trim());
		
		// �׽�Ʈ���̽� ����ŭ �ݺ��Ѵ�.
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			
			// n, m�� �Է¹޴´�.
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			
			// ������ �����Ѵ�.
			makeSet();
			
			// �׽�Ʈ���̽� ��ȣ �ֱ�
			sb.append("#"+test_case+" ");
			
			// m ���� ������ �����Ѵ�.
			for (int cmd=0;cmd<m;cmd++) {
				st=new StringTokenizer(br.readLine().trim());
				
				// ���� ��ȣ�� ���� 2���� �Է¹޴´�.
				int cmdNum=Integer.parseInt(st.nextToken());
				int e1=Integer.parseInt(st.nextToken());
				int e2=Integer.parseInt(st.nextToken());
				
				// ������ ������ ���
				if (cmdNum==0) {
					union(e1,e2);
				}
				// ���� ���տ� ���ԵǾ� �ִ��� Ȯ���ϴ� ������ ���
				// ���� �����̸� 1 �ƴϸ� 0�� ����Ѵ�.
				else if(cmdNum==1) {
					if (find(e1)==find(e2)) {
						sb.append(1);
					}
					else {
						sb.append(0);
					}
				}
			}
			// �� �׽�Ʈ���̽��� ��� ���ƴٸ� ���๮�ڸ� �����Ѵ�.
			sb.append("\n");
		}
		// ��� �׽�Ʈ���̽��� ���� ����� ����Ѵ�.
		System.out.println(sb);

	}
	// ������ �ʱ�ȭ�Ѵ�.
	public static void makeSet() {
		// �� ���Ұ� ���� ������ ��ǥ ���Ҹ� ������ �迭 ũ�⸦ �ʱ�ȭ�Ѵ�.
		parents=new int[n+1];
		// �� ������ Ʈ�� ���̸� �����ϴ� �迭 ũ�⸦ �ʱ�ȭ�Ѵ�.
		rank=new int[n+1];
		
		for (int idx=1;idx<n;idx++) {
			// �ʱ� ������ ��ǥ ���Ұ� �ڱ� �ڽ�
			parents[idx]=idx;
			
			// Ʈ�� ���̴� 0
			rank[idx]=0;
		}
		
	}
	
	// �ش� ���Ұ� ���� ������ ��ǥ ���Ҹ� ã�´�.
	public static int find(int e) {
		if (parents[e]==e) {
			return e;
		}
		// ��ǥ ���Ҹ� �ڱ� �ڽ��� �θ�� �����Ѵ�.
		return parents[e]=find(parents[e]);
	}
	
	// �� ���Ұ� ���� ������ ��ģ��.
	public static void union(int e1, int e2) {
		// �� ���Ұ� ���� ������ ��ǥ ���� ���Ѵ�.
		int root1=find(e1);
		int root2=find(e2);
		
		// �� ���Ұ� ���� ���տ� �����ִٸ� �� ���� ����
		if (root1==root2) return;
		
		// root1 ���� > root2 ����
		// root2�� ���� ������ root1���� ����.
		if (rank[root1]>rank[root2]) {
			parents[root2]=root1;
		}
		
		// root1 ���� < root2 ����
		// root1�� ���� ������ root2�� ����.
		else if (rank[root1]<rank[root2]) {
			parents[root1]=root2;
		}
		
		// ���̰� ���� ���
		// �� �� ���̸� �����ְ�
		// ������ ������ �ٸ� ������ ����.
		else {
			rank[root1]+=1;
			parents[root2]=root1;
		}
	}
}
