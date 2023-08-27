package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * S1, S2를 골라야 한다.(가장 높은 점수~가장 낮은 점수 사이의 범위)
 * 
 * 첫 줄에 테스트 케이스 개수가 주어진다.
 * 두 번째 줄에 학생 수, 반의 최소 인원 수, 반의 최대 인원수가 주어진다.
 * 세 번째 줄에 각 학생의 점수가 주어진다.
 * 
 */
public class SWEA_반배치고사_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int T;					// 테스트케이스 개수
	static int N,N_min,N_max;		// 케이스 인원 수, 최소 인원, 최대 인원
	static int[] classScore;		// 각 반의 학생 점수들을 저장하는 배열
	static int minDiff;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 테스트케이스 개수를 입력받는다.
		T=Integer.parseInt(br.readLine());
		
		for (int test_case=1;test_case<=T;test_case++) {
			// 케이스 인원 수, 최소 인원 , 최대 인원을 입력받는다.
			st=new StringTokenizer(br.readLine().trim());
			
			N=Integer.parseInt(st.nextToken());
			N_min=Integer.parseInt(st.nextToken());
			N_max=Integer.parseInt(st.nextToken());
			
			// 각 반의 최고 점수와 최저 점수 초기화 -> s1, s2를 결정하기 위함
			int minScore=Integer.MAX_VALUE;
			int maxScore=0;
			classScore=new int[N];
			
			// 학생 점수를 저장한다.
			st=new StringTokenizer(br.readLine());
			for (int idx=0;idx<N;idx++) {
				classScore[idx]=Integer.parseInt(st.nextToken());
				if (classScore[idx]>maxScore) {
					maxScore=classScore[idx];
				}
				if (classScore[idx]<minScore) {
					minScore=classScore[idx];
				}
			}
			minDiff=Integer.MAX_VALUE;
			int[] classroom=null;
			// 각 반을 나눌 임의의 점수 s1, s2를 고른다. (minScore~maxScore 사이 임의의 점수)
			for (int s1=minScore;s1<maxScore;s1++) {
				for (int s2=s1+1;s2<=maxScore;s2++) {
					// 각 학생의 점수를 보고 분반을 시작한다.
					classroom=new int[3];
					
					for (int idx=0;idx<N;idx++) {
						// A분반(s2 이상)
						if (classScore[idx]>=s2) {
							classroom[0]+=1;
						}
						
						// B분반(s1 이상 s2미만)
						else if(classScore[idx]>=s1 && classScore[idx]<s2) {
							classroom[1]+=1;
						}
						// C분반(s1미만)
						else if(classScore[idx]<s1) {
							classroom[2]+=1;
						}
					}
					
					// 나눈 분반에서 최소 인원과 최대 인원을 만족하는지 확인
					// 만족하지 않는다면 다른 임의의 점수로 반 배정
					if (!isValid(classroom)) {
						continue;
					}
					
					// 이 임의의 점수로 조건을 만족한 경우
					// 최다 인원과 최소 인원의 차이를 구한다.
					int maxCnt=-1;
					int minCnt=Integer.MAX_VALUE;
					for (int idx=0;idx<3;idx++) {
						if (maxCnt<classroom[idx]) {
							maxCnt=classroom[idx];
						}
						if (minCnt>classroom[idx] ) {
							minCnt=classroom[idx];
						}
					}
					minDiff=Math.min(minDiff, maxCnt-minCnt);
				}
				
			}
			sb.append("#"+test_case+" ");
			if (minDiff==Integer.MAX_VALUE) {
				sb.append(-1);
			}else {
				sb.append(minDiff);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	// 최소 인원보다 적거나 최대 인원 초과시 false
	public static boolean isValid(int[] classroom) {
		boolean valid=true;
		for (int idx=0;idx<3;idx++) {
			if (classroom[idx]<N_min) {
				valid=false;
				break;
			}
			if (classroom[idx]>N_max) {
				valid=false;
				break;
			}
		}
		return valid;
	}

}
