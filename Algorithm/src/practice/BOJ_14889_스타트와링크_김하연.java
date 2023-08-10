package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 14889: 스타트와 링크
 * N명을 N/2명으로 이루어진 스타트 팀과 링크 팀으로 나눈다.
 * 팀에 속한 모든 쌍의 능력치 Sij와 Sji의 합을 구한다.
 * 스타트 팀의 능력치와 링크 팀의 능력치의 차이 최솟값을 구한다.
 * */
public class BOJ_14889_스타트와링크_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;						// N명의 사람들
	static int[][] s;					// 능력치를 저장하는 배열
	
	static int diff;					// 스타트 팀과 링크 팀 능력치 차이
	static boolean[] visited;			// 스타트 팀에 속한 사람 표시
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// N 명 사람들을 입력 받는다.
		N=Integer.parseInt(br.readLine().trim());		
		
		// 팀원 한 쌍의 능력치를 저장하는 배열
		s=new int[N][N];
		// 능력치 정보를 입력받는다.
		for (int row=0;row<N;row++) {
			st=new StringTokenizer(br.readLine().trim());
			for (int col=0;col<N;col++) {
				s[row][col]=Integer.parseInt(st.nextToken());
			}
		}
		// 스타트팀과 링크팀을 구분할 배열
		visited=new boolean[N];
		
		// 두 팀의 능력치 차이는 최대라고 가정
		diff=Integer.MAX_VALUE;
		
		// 스타트와 링크 팀의 조합을 만들어서 두 팀 간의 능력치 차이를 구한다.
		getSubSet(0,0);
		// 최소 능력치 차이를 출력한다.
		System.out.println(diff);
	}
	// 부분집합으로 구현
	public static void getSubSet(int cnt,int chosen) { // cnt: 현재까지 고려된 수, chosen: 한 팀에 들어간 사람의 수
		// 스타트 팀이 짜여졌다.
		if (cnt==N) {		// 모든 수가 고려되었을 때
			if (chosen==N/2) {	// 한 팀의 수가 정확히 N/2일 경우만 유효하다.
				getDiff();		// 두 팀의 능력치 차이를 구한다.
			}
			return;
		}
		// 모든 수가 고려되지 않았을 때
		visited[cnt]=true;		// 현재 팀원을 스타트 팀에 넣는다.
		getSubSet(cnt+1,chosen+1);// 다음 팀원을 구한다.
		visited[cnt]=false;		// 현재 팀원을 스타트 팀에 넣지 않고
		getSubSet(cnt+1,chosen);// 들어갈 팀원을 구한다.
	}
	// 스타트팀과 링크팀의 능력치 차이를 구하여
	// 능력치 차이의 최솟값을 저장한다.
	public static void getDiff() {
		// 각 팀의 능력치를 저장할 변수
		int startTeam=0;
		int linkTeam=0;
		
		// 부분집합을 통해 visited가 true면, 스타트 팀원이고
		// false면, 링크 팀원이다.
		for (int row=0;row<N;row++) {
			for (int col=0;col<N;col++) {
				if (visited[row] && visited[col]) {
					startTeam+=s[row][col];
				}else if (!visited[row] && !visited[col]) {
					linkTeam+=s[row][col];
				}
			}
		}
		// 능력치 차이의 최솟값을 저장한다.
		diff=Math.min(diff, Math.abs(startTeam-linkTeam));
	}

}
