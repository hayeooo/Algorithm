package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 17281: 야구
 * 1. 4번 선수를 첫 번째 선수로 배치한 후, 나머지 선수들을 순열로 나열한다.
 * 2. 각 선수가 이닝에서 얻는 결과를 보고 점수를 계산한다.
 * 	2-1. 3아웃이 되기 전에는 이닝은 끝나지 않고, 타순은 이닝이 변경되어도 순서를 유지한다.
 * 	2-2. 1: 안타, 2: 2루타, 3: 3루타, 4: 홈런, 0: 아웃
 */
public class BOJ_17281_야구_김하연 {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;							// 이닝 수
	static int[] comb;						// 참가 선수들 순서
	static int[] tmpComb;
	static boolean[] selected;				// 선택한 선수를 저장하는 배열
	static int[][] innings;					// 각 선수가 이닝에서 얻은 결과(1번~9번)
	static int maxScore;					// 최고 득점
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		N=Integer.parseInt(br.readLine().trim());
		innings=new int[N][9];
		
		// 각 선수가 각 이닝에서 얻은 결과를 입력 받는다.
		for (int idx=0;idx<N;idx++) {
			st=new StringTokenizer(br.readLine().trim());
			
			for (int player=0;player<9;player++) {
				innings[idx][player]=Integer.parseInt(st.nextToken());
			}
		}
		
		comb=new int[9];
		tmpComb=new int[8];
		selected=new boolean[9];
		// 1번 선수를 4번 타자로 미리 결정한다.
		perm(0);
		comb[3]=0;
		selected[0]=true;
		System.out.println(maxScore);
	}
	
	// 선수들의 순서를 정한다.
	public static void perm(int cnt) {
		// 선수의 조합이 결정되면
		if (cnt==9) {
			// 게임을 진행한다.
			// 1번 선수를 4번 타자에 넣고 게임을 진행한다.
			for (int idx=0;idx<3;idx++) {
				comb[idx]=tmpComb[idx];
			}
			comb[3]=0;
			for(int idx=3;idx<8;idx++) {
				comb[idx+1]=tmpComb[idx];
			}
			startGame();
			return;
		}
		for (int idx=1;idx<9;idx++) {
			if (selected[idx]) continue;
			tmpComb[cnt]=idx;
			selected[idx]=true;
			perm(cnt+1);
			selected[idx]=false;
		}
	}
	// 게임을 시작한다.
	public static void startGame() {
		int seq=0;				// 순서
		int totalScore=0;
		// 총 N이닝동안 게임을 진행해야 한다.
		for (int curInnings=0;curInnings<N;curInnings++) {
			
			int out=0;
			boolean[] stand=new boolean[5];	// 홈, 1루, 2루, 3루 , 홈
			
			// 3아웃이 발생하지 않을 때까지 이닝은 끝나지 않는다.
			while (out<3) {
				// 현재 선수 번호
				int player=comb[seq];
				// 타자 결과
				int res=innings[curInnings][player];
				stand[0]=true;
				// 안타인 경우
				if (res==1) {
					// 일단 한 루씩 이동
					for (int idx=3;idx>=0;idx--) {
						stand[idx+1]=stand[idx];
					}
					// 홈에 들어왔을 경우, 점수 획득
					if (stand[4]==true) {
						totalScore+=1;
						stand[4]=false;
					}
				}
				// 2루타인 경우
				else if(res==2) {
					// 2 칸씩 이동
					for (int c=0;c<2;c++) {
						for (int idx=3;idx>=0;idx--) {
							stand[idx+1]=stand[idx];
						}
						// 홈에 들어왔을 경우, 점수 획득
						if (stand[4]==true) {
							totalScore+=1;
							stand[4]=false;
						}
						stand[0]=false;
					}
					
				}
				// 3루타인 경우
				else if(res==3) {
					// 세 칸씩 이동
					for (int c=0;c<3;c++) {
						// 3 칸씩 이동
						for (int idx=3;idx>=0;idx--) {
							stand[idx+1]=stand[idx];
						}
						// 홈에 들어왔을 경우, 점수 획득
						if (stand[4]==true) {
							totalScore+=1;
							stand[4]=false;
						}
						stand[0]=false;
					}
				}
				// 홈런인 경우
				else if(res==4) {
					// 1루,2루,3루에 선수가 있으면 점수 획득
					for (int idx=1;idx<=3;idx++) {
						if (stand[idx]) totalScore+=1;
					}
					// 타자가 홈에 들어온 경우
					totalScore+=1;
					stand=new boolean[5];
				}
				// 아웃인 경우
				else if(res==0) {
					out+=1;
				}
				seq=(seq+1)%9;
			}
		}
		// 최대 득점으로 업데이트
		maxScore=Math.max(maxScore, totalScore);
	}

}
