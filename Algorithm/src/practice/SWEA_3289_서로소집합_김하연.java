package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * SWEA 3289: 서로소 집합
 * 초기에 {1},{2}...{n}이 각각 n개의 집합을 이루고 있다.
 * 합집합 연산과, 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산을 수행한다.
 * 
 * 합집합 연산을 수행하고
 * 두 원소가 같은 집합에 포함되어 있는지 확인하는 연산에 대해
 * 같은 집합에 속해있다면 1을, 아니면 0을 순서대로 한줄에 연속하여 출력한다.
 */
public class SWEA_3289_서로소집합_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;		// 결과 문자열을 출력을 위한 객체
	
	static int T;					// 테스트 케이스 개수
	static int n,m;					// 원소의 개수, 연산의 개수
	
	static int[] parents;			// 해당 원소가 속한 집합의 대표 원소를 저장하는 배열
	static int[] rank;				// 해당 원소의 트리 높이를 저장하는 배열
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스 수 입력받는다.
		T=Integer.parseInt(br.readLine().trim());
		
		// 테스트케이스 수만큼 반복한다.
		for (int test_case=1;test_case<=T;test_case++) {
			st=new StringTokenizer(br.readLine().trim());
			
			// n, m을 입력받는다.
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			
			// 집합을 생성한다.
			makeSet();
			
			// 테스트케이스 번호 넣기
			sb.append("#"+test_case+" ");
			
			// m 번의 연산을 수행한다.
			for (int cmd=0;cmd<m;cmd++) {
				st=new StringTokenizer(br.readLine().trim());
				
				// 연산 번호와 원소 2개를 입력받는다.
				int cmdNum=Integer.parseInt(st.nextToken());
				int e1=Integer.parseInt(st.nextToken());
				int e2=Integer.parseInt(st.nextToken());
				
				// 합집합 연산인 경우
				if (cmdNum==0) {
					union(e1,e2);
				}
				// 같은 집합에 포함되어 있는지 확인하는 연산인 경우
				// 같은 집합이면 1 아니면 0을 출력한다.
				else if(cmdNum==1) {
					if (find(e1)==find(e2)) {
						sb.append(1);
					}
					else {
						sb.append(0);
					}
				}
			}
			// 한 테스트케이스를 모두 마쳤다면 개행문자를 삽입한다.
			sb.append("\n");
		}
		// 모든 테스트케이스에 대한 결과를 출력한다.
		System.out.println(sb);

	}
	// 집합을 초기화한다.
	public static void makeSet() {
		// 각 원소가 속한 집합의 대표 원소를 저장할 배열 크기를 초기화한다.
		parents=new int[n+1];
		// 각 원소의 트리 높이를 저장하는 배열 크기를 초기화한다.
		rank=new int[n+1];
		
		for (int idx=1;idx<n;idx++) {
			// 초기 집합은 대표 원소가 자기 자신
			parents[idx]=idx;
			
			// 트리 높이는 0
			rank[idx]=0;
		}
		
	}
	
	// 해당 원소가 속한 집합의 대표 원소를 찾는다.
	public static int find(int e) {
		if (parents[e]==e) {
			return e;
		}
		// 대표 원소를 자기 자신의 부모로 저장한다.
		return parents[e]=find(parents[e]);
	}
	
	// 두 원소가 속한 집합을 합친다.
	public static void union(int e1, int e2) {
		// 각 원소가 속한 집합의 대표 원소 구한다.
		int root1=find(e1);
		int root2=find(e2);
		
		// 두 원소가 같은 집합에 속해있다면 합 연산 종료
		if (root1==root2) return;
		
		// root1 높이 > root2 높이
		// root2가 속한 집합이 root1으로 들어간다.
		if (rank[root1]>rank[root2]) {
			parents[root2]=root1;
		}
		
		// root1 높이 < root2 높이
		// root1가 속한 집합이 root2로 들어간다.
		else if (rank[root1]<rank[root2]) {
			parents[root1]=root2;
		}
		
		// 높이가 같은 경우
		// 한 쪽 높이를 높여주고
		// 높여준 곳으로 다른 집합이 들어간다.
		else {
			rank[root1]+=1;
			parents[root2]=root1;
		}
	}
}
