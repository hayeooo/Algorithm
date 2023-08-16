package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
/*
 * BOJ 6987: 월드컵
 * 월드컵 조별 최종 예선에서 6개국으로 구성된 각 조별로 동일한 조에 소속된 국가들과 한 번씩, 총 5번의 경기를 치른다.
 * 조별리그가 끝난 후, 기자가 보내온 각 나라의 승, 무승부, 패의 수가 가능한 결과인지를 판별하려고 한다.
 * 
 * 각 팀의 점수와 다른 팀의 점수를 제거해나가며 계산한다.
 * 모든 계산이 끝나고 각 팀의 점수 중에 0이 아닌 수가 나온다면, 불가능한 결과이다.
 * 
 * 팀의 경기는 A vs B, A vs C, A vs D, A vs F,.. E vs F 순으로 이루어진다.
 * 어려웠던 점: 경기하는 두 팀과 진행된 경기 수와 (한 팀이 이겼을 경우, 무승부인 경우, 진 경우)를 탐색하는 DFS를 연결시키는 것
 * => google에 풀이코드 도움을 받음..!!
 */
public class BOJ_6987_월드컵_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[][] scores;		// 각 팀의 점수(승, 무승부, 패)를 저장하는 배열
	static final int win=0;
	static final int same=1;
	static final int lose=2;
	
	// home vs enemy로 15번의 게임이 진행된다.
	// => 이 부분 풀이 코드 참고함.
	static int[] home= {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
	static int[] enemy= {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 점수를 저장할 배열 크기 초기화
		scores=new int[6][3];
		
		// 첫째 줄부터 넷째 줄까지 각 줄마다 6개국의 결과가 나라별로 승, 무승부, 패의 순서로 주어진다.
		for (int idx=0;idx<4;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			// 각 팀의 점수를 입력 받는다.
			for (int team=0;team<6;team++) {
				scores[team]=new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
			}
		}
		dfs(0);
	}
	
	public static boolean dfs(int stage) {
		// 기저 조건
		if (stage==15) {
			return true;
		}
		int homeTeam=home[stage];
		int enemyTeam=enemy[stage];
		
		// 해당 stage에서 homeTeam이 이긴 경우
		if (scores[homeTeam][win]>0 && scores[enemyTeam][lose]>0) {
			scores[homeTeam][win]--;
			scores[enemyTeam][lose]--;
			dfs(stage+1);
		}
		// 해당 stage에서 homeTeam이 무승부인 경우
		if (scores[homeTeam][same]>0 && scores[enemyTeam][lose]>0) {
			
		}
		// 해당 stage에서 homeTeam이 진 경우
		if (scores[homeTeam][lose]>0 && scores[enemyTeam][lose]>0) {
			
		}
		return false;
	}
}
