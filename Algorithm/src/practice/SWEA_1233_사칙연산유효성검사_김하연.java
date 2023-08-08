package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * SWEA 1223: 사칙연산 유효성 검사
 * 1번 정점은 root .. N/2번 노드까지 연산자와 피연산자 2개가 있어야 한다.
 * N/2+1 .. N번 노드까지 피연산자 1개가 들어있어야 한다.
 * 
 * 입력 데이터를 받으며 
 * 위의 조건에 부합하는지 확인하고
 * 계산이 불가능한 식일 경우 flag 변수를 업데이트한다.
 * 
 * */
public class SWEA_1233_사칙연산유효성검사_김하연 {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;					// 정점의 개수
	static final int TC=10;			// 테스트케이스 개수
	static int isValid;				// 적합한 계산식인지 여부(적합하다면 1, 부적합하다면 0)
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 개수만큼 반복
		for (int test_case=1;test_case<=TC;test_case++) {
			N=Integer.parseInt(br.readLine());
			
			// 적합한 계산식이라고 가정
			isValid=1;
			
			// 루트 노드에서부터 N/2번 노드까지 연산자와 피연산자가 있는지 확인
			for (int idx=1;idx<=N/2;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				// 정점 정보
				int node=Integer.parseInt(st.nextToken());
				
				// 연산자 정보
				String operator=st.nextToken();
				
				// 연산자가 아닌 경우
				if (!operator.equals("-") && !operator.equals("+") && !operator.equals("*") && !operator.equals("/")) {
					isValid=0;
				}
				// 피연산자가 2개 미만인 경우 부적합한 계산식
				if (st.countTokens()<2) {
					isValid=0;
					continue;
				}
			}
			// N/2+1번 노드부터 N번 노드까지 피연산자가 있는지 확인
			for (int idx=N/2+1;idx<=N;idx++) {
				st=new StringTokenizer(br.readLine().trim());
				// 정점 정보
				int node=Integer.parseInt(st.nextToken());
				// 피연산자가 없는 경우 : 부적합한 계산식
				if (st.countTokens()!=1) {
					isValid=0;
					continue;
				}
				// 피연산자 형태가 연산자인 경우: 부적합한 계산식
				String operand=st.nextToken();
				if (operand.equals("-") || operand.equals("+") || operand.equals("*") || operand.equals("/")) {
					isValid=0;
				}
			}
			// 결과 출력
			System.out.println("#"+test_case+" "+isValid);
			
		}

	}

}
