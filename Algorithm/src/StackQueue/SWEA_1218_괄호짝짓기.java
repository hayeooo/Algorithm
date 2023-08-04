package StackQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * SWEA 1218: 괄호 짝짓기
 * 4 종류의 괄호 문자들 '()','[]','{}','<>'
 * 이 문자열에 사용된 괄호들의 짝이 모두 맞는지 판별하는 프로그램
 * 
 * 1. 문자열 길이만큼 char를 확인한다.
 * 2. 열린 괄호라면 stack에 push한다.
 * 3. 닫힌 괄호라면 stack에서 pop하여 나온 괄호가 짝이 맞는지 확인한다.
 * 4. 문자열 길이만큼 확인하지 못하고 중간에 stack이 비어있을 경우, 유효한 문자열이 아니다.
 * 5. 짝이 맞지 않은 경우, 유효한 문자열이 아니다.
 * 6. 문자열 끝까지 탐색한 후에도 stack에 괄호가 남아있다면 유효한 문자열이 아니다.
 * */
public class SWEA_1218_괄호짝짓기 {
	static BufferedReader br;
	static int N;						// 문자열 길이
	static int T=10;					// 테스트케이스 총 개수
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스만큼 반복
		for (int test_case=1;test_case<=T;test_case++) {
			
			// 문자열 길이 입력
			N=Integer.parseInt(br.readLine().trim());
			
			// 괄호 문자열 전체 입력 받기
			String testLine=br.readLine().trim();
			
			// stack 객체 생성
			BracketStack stack=new BracketStack();
			
			// 유효한 문자열이라고 가정
			boolean isValid=true;
			
			// 전체 문자를 확인
			for (int cIdx=0;cIdx<N;cIdx++) {
				// 문자열 하나
				char bracket=testLine.charAt(cIdx);
				
				// 열린 괄호면 stack에 push
				if (bracket=='(' || bracket=='[' || bracket=='{' || bracket=='<') {
					stack.push(bracket);
				}
				// 닫힌 괄호면 stack에서 pop 해서 비교 
				else {
					// 전체 문자열을 다 확인하기 전에
					// stack이 비었다면 유효한 문자열이 아님.
					if (stack.isEmpty()) {
						isValid=false;
						break;
					}
					
					//비교한 열린 괄호 pop
					char toCompare=stack.pop();
					
					// 짝이 맞을 경우 continue
					if (bracket==')' && toCompare=='(') {
						continue;
					}
					else if (bracket==']' && toCompare=='[') {
						continue;
					}else if (bracket=='}' && toCompare=='{') {
						continue;
					}else if (bracket=='>' && toCompare=='<') {
						continue;
					}
					// 그렇지 않을 경우 유효하지 않은 문자열
					else {
						isValid=false;
						break;
					}
					
				}
			}
			// 모든 문자열을 확인하였지만, stack에 문자가 남아있는 경우 유효한 문자열이 아님.
			if (!stack.isEmpty()) isValid=false;
			// 유효한 문자열 유무를 출력
			System.out.println("#"+test_case+" "+(isValid?1:0));
		}
		
	}

}

// 괄호를 push하고 pop하는 stack class
class BracketStack{
	int top;				// stack의 가장 윗부분을 가리키는 변수
	char[] arr;				// 열린 괄호 문자를 저장하는 배열
	int N;					// stack의 길이
	
	// 생성자
	public BracketStack() {	// 변수 초기화	
		top=-1;
		arr=new char[]{};
		N=0;
	}
	
	// 괄호를 stack에 추가
	public void push(char bracket) {
		N+=1;
		char[] tmp=new char[N];
		System.arraycopy(arr, 0, tmp, 0, N-1);
		
		top+=1;
		tmp[top]=bracket;
		arr=tmp;
	}
	
	// stack의 마지막 괄호 삭제
	public char pop() {
		if (isEmpty()) return (Character) null;
		char last=arr[top];
		arr=Arrays.copyOfRange(arr, 0, top);
		N-=1;
		top-=1;
		return last;
	}
	
	// stack의 마지막 괄호 반환
	public char peek() {
		if (isEmpty()) return (Character) null;
		return arr[top];
	}
	
	// stack의 크기 반환
	public boolean isEmpty() {
		if (top==-1) return true;
		return false;
	}
}