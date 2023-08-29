package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BOJ 1107: 리모컨
 * 1. 고장난 버튼의 개수를 제외하고 이동하려는 채널에 가장 가까운 숫자를 구한다. (숫자 먼저 누르기)
 * 2. 이동하려는 채널과 지금의 채널의 차이를 구한다.
 * 
 * 수빈이가 지금 보고 있는 채널은 100번이다.
 * 
 * 시도 1: dfs
 * =======
 * 이동하려는 채널이 n자리라면, 차이가 가장 적은 n자리 채널을 구하려고 했으나
 * 10
 * 9
 * 1 2 3 4 5 6 7 8 9
 * tc에서 오답
 * 
 * 0부터 999999까지 채널에서 최소 차이를 구해야한다. (이동하려는 채널은 최대 500000번까지)
 * 숫자 하나씩 고장이 난 버튼인지 확인한다(최대 6번)
 * 1000000*6=6백만 , 시간 제한 2초
 * 
 */
public class BOJ_1107_리모컨_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static String toMoveStr;					// 이동하려는 채널(문자열)
	static int toMoveInt;						// 이동하려는 채널(정수형)
	static int M;								// 고장난 버튼 개수
	static boolean[] broken;		// 고장난 버튼 여부를 저장하는 배열
	
	
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 이동하려는 채널 정보를 입력받는다.
		toMoveStr=br.readLine().trim();
		toMoveInt=Integer.parseInt(toMoveStr);
		
		// 고장난 버튼 개수를 입력받는다.
		M=Integer.parseInt(br.readLine().trim());
		broken=new boolean[10];
		
		if (M>0) {
			// 고장난 버튼 번호를 입력받는다.
			st=new StringTokenizer(br.readLine().trim());
			for (int idx=0;idx<M;idx++) {
				int num=Integer.parseInt(st.nextToken());
				broken[num]=true;
			}			
		}
		
		// +, - 버튼만 누르는 경우
		int onlyUpDown=Math.abs(toMoveInt-100);
		
		int usingNum=Integer.MAX_VALUE;
		
		// 이동할 수 있는 채널 전부에 대해 탐색한다.(숫자 버튼을 누르는 경우)
		for (int tmpChannel=0;tmpChannel<=999999;tmpChannel++) {
			
			String tmpChannelStr=Integer.toString(tmpChannel);
			boolean valid=true;
			for (int idx=0;idx<tmpChannelStr.length();idx++) {
				// 고장난 버튼인 경우
				if (broken[tmpChannelStr.charAt(idx)-'0']) {
					valid=false;
					break;
				}
			}
			// 유효한 채널인 경우, 버튼 누르는 최소 횟수 구하기
			if (valid) {
				int diff=Math.abs(toMoveInt-tmpChannel);
				diff+=tmpChannelStr.length();			// 채널 번호 자릿수
				usingNum=Math.min(usingNum, diff);
			}
			
		}
		System.out.println(Math.min(onlyUpDown, usingNum));
		
		
	}
	
	

}
