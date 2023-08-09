package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * BOU 11286 : 절댓값 힙
 * 1. 배열에 0이 아닌 정수를 넣는다.
 * 2. 배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다.
 * 	2-1. 절댓값이 가장 작은 값이 여러개일 때는, 가장 작은 수를 출력하고, 그 값을 배열에서 제거한다.
 * 3. 0이 주어진 회수만큼 답을 출력한다.
 * 4. 배열이 비어있을 때 절댓값이 가장 작은 값을 출력하라고 한 경우에 0을 출력한다.
 * 
 * */
public class BOJ_11286_절댓값힙_김하연 {
	static BufferedReader br;
	static StringBuilder sb;				// 결과 문자열을 저장할 객체
	static PriorityQueue<Integer> plus;		// 양의 정수를 저장할 minHeap
	static PriorityQueue<Integer> minus;	// 음의 정수를 저장할 minHeap
	
	static int N;						// 연산 개수
	
	public static void main(String[] args) throws IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		sb=new StringBuilder();
		
		// 연산의 개수 입력 받는다.
		N=Integer.parseInt(br.readLine().trim());
		
		// 최소 힙 객체를 생성한다.
		plus=new PriorityQueue<Integer>();
		minus=new PriorityQueue<Integer>();
		
		// 연산에 대한 정보를 나타내는 정수 x를 입력받는다.
		for (int idx=0;idx<N;idx++) {
			int x=Integer.parseInt(br.readLine().trim());
			// 0 인 경우 절댓값이 가장 작은 정수를 출력한다.
			if (x==0) {					
				sb.append(getNum()+"\n");
				
			}
			// 음수일 경우, 음수만 저장해놓은 힙에 절댓값 형태로 저장한다.
			else if (x<0) {
				minus.add(-x);
			}
			// 양수인 경우, 양수만 저장해놓은 힙에 저장한다.
			else {
				plus.add(x);
			}
		}
		// 결과 문자열을 출력한다.
		System.out.println(sb);
		
	}
	// 절댓값이 가장 작은 수를 리턴하는 메소드
	public static int getNum() {
		int result=0;
		// 두 개의 힙 모두 비어있지 않은 경우
		if (!plus.isEmpty() && !minus.isEmpty()) {
			int plusCompare=plus.peek();
			int minusCompare=minus.peek();
			
			// 절댓값의 크기를 비교하여 더 작은 절댓값을 가진 원소를 제거한다.
			if (plusCompare<minusCompare) {
				result=plus.poll();
			}else if (plusCompare>=minusCompare) {	// 절댓값이 같은 경우, 크기가 작은 수를 제거해야 하므로
				result=-minus.poll();				// 음수만 들어있는 힙의 숫자를 제거한다.
				
			}
		}
		// 음수만 들어있는 힙이 비어있는 경우, 양수만 들어있는 숫자를 제거한다.
		else if(!plus.isEmpty()) {
			result=plus.poll();
			
		}
		// 양수만 들어있는 힙이 비어있는 경우, 음수만 들어있는 숫자를 제거한다.
		else if (!minus.isEmpty()) {
			result=-minus.poll();
			
		}
		// 두 개의 힙 모두 비어있는 경우 0을 리턴한다.
		else {
			result=0;
		}
		return result;
	}
}
