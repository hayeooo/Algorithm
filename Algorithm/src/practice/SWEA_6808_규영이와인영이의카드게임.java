package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/*
 * SWEA 6808: 규영이와 인영이의 카드게임
 * 
 * 규영이와 인영이는 1에서 18까지의 수가 적힌 18장의 카드로 게임을 하고 있다.
 * 아홉 라운드에 걸쳐 게임이 진행되며,
 * 한 라운드에는 한 장씩 카드를 낸 다음 두 사람이 낸 카드에 적힌 수를 비교해서 점수를 계산한다.
 * 높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻고,
 * 낮은 수가 적힌 카드를 낸 사람은 아무런 점수를 얻을 수 없다.
 * 
 * 총점을 따졌을 때 더 높은 사람이 게임의 승자가 된다.
 * 규영이가 내는 카드와 카드의 순서는 고정되어 있다.
 * 인영이가 어떻게 카드를 내는지에 따라 규영이의 승패가 정해진다.
 * 규영이가 이기는 경우와 지는 경우가 총 몇 가지인지 구하는 프로그램을 작성한다.
 * 
 * 1. 테스트케이스 수를 입력받는다.
 * 2. 각 테스트케이스마다 규영이가 내는 카드 순서를 입력 받는다.
 * 3. 규영이가 낸 카드를 제외한 카드(인영이가 내는 카드)를 알아낸다.
 * 4. 인영이가 내는 카드의 9! 순열을 구한다.
 * 5. 각 순열마다 규영이와의 게임을 진행하며 승패를 결정한다.
 * 6. 인영이가 내는 카드의 모든 조합에 대한 게임이 끝난다면 이긴 횟수와 진 횟수를 출력한다.
 * */
public class SWEA_6808_규영이와인영이의카드게임 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int T;								// 테스트케이스 수
	static boolean[] cards;						// 규영이가 낸 카드 정보를 저장하는 배열
	static List<Integer> inPlayer;				// 인영이가 내는 카드
	static List<Integer> gyuPlayer;				// 규영이가 내는 카드
	static int[] comb;							// 인영이의 카드 조합
	static boolean[] isSelected;				// 순열 구현할 때, 중복 유무 검사를 위한 배열
	
	static int win;								// 규영이가 이긴 횟수
	static int lose;							// 규영이가 진 횟수
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 입력
		T=Integer.parseInt(br.readLine().trim());
		
		// 테스트케이스 수만큼 반복
		for (int test_case=1;test_case<=T;test_case++) {
			
			// 각 플레이어가 내는 카드를 저장하는 객체 생성
			inPlayer=new ArrayList<Integer>();			
			gyuPlayer=new ArrayList<Integer>();
			
			// 규영이가 낸 카드 정보를 저장하는 배열 크기 초기화
			cards=new boolean[19];
			
			// 규영이가 내는 카드 순서와 카드 정보를 저장한다.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<9;idx++) {
				int card=Integer.parseInt(st.nextToken());
				gyuPlayer.add(card);
				// 규영이가 낸 카드는 표시를 해준다.
				cards[card]=true;
			}
			
			// 인영이가 낼 수 있는 카드
			for (int idx=1;idx<19;idx++) {
				// 규영이가 내지 않은 카드는 인영이가 가진다.
				if (!cards[idx]) {
					inPlayer.add(idx);
				}
			}
			
			// 인영이가 낼 카드 조합 구하기
			comb=new int[9];
			isSelected=new boolean[9];
			// 이긴 횟수와 진 횟수를 초기화 한다.
			win=0;
			lose=0;
			// 인영이가 낼 수 있는 카드 순서를 구하고, 게임을 진행시켜
			// 이긴 횟수와 진 횟수를 구한다.
			permutations(0);
			
			System.out.println("#"+test_case+" "+win+" "+lose);
		}
	}
	// 인영이의 카드 순서를 정하는 순열 메소드
	public static void permutations(int cnt){
		// 9번째 순서까지 카드 순서를 정했을 경우
		if (cnt==9) {
			// 게임을 진행시킨다.
			startGame();
			return;
		}
		// 다음 자리에 들어갈 카드를 선정한다.
		for (int idx=0;idx<inPlayer.size();idx++) {
			if (isSelected[idx]) continue;	// 이미 선택된 카드는 제외한다.
			comb[cnt]=inPlayer.get(idx);
			isSelected[idx]=true;			// 선택 처리를 한다.
			permutations(cnt+1);			// 다음 자리 카드를 고른다.
			isSelected[idx]=false;			// 같은 자리에 다른 숫자를 넣어보기 위해 선택 처리를 무효화한다.
		}
	}
	// 게임을 진행하여 규영이가 승리하는지 여부를 계산한다.
	public static void startGame() {
		int inScore=0;
		int gyuScore=0;
		// 9개의 카드를 하나씩 비교한다.
		for (int round=0;round<9;round++) {
			int inCard=comb[round];
			int gyuCard=gyuPlayer.get(round);
			// 각 카드의 크기를 비교하여 이길 경우, 두 카드의 합을 점수로 계산하고
			// 질 경우, 점수를 획득하지 못한다.
			if (inCard>gyuCard) {
				inScore+=(inCard+gyuCard);
			}else if(inCard<gyuCard) {
				gyuScore+=(inCard+gyuCard);
			}
		}
		// 규영이가 진 경우
		if (inScore>gyuScore) {
			lose+=1;
		}
		// 규영이가 이긴 경우
		else if(gyuScore>inScore) {
			win+=1;
		}
		// 무승부는 포함하지 않는다.
	}

}
