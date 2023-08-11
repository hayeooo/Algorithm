package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * BOJ 2839: 설탕 배달
 * 3kg 봉지와 5kg 봉지로 설탕 Nkg를 최대한 적은 봉지 개수로 배달하려고 한다.
 * 설탕을 정확하게 Nkg 배달해야 할 때, 봉지를 몇 개 가져가면 되는지 그 수를 구한다.
 * */
public class BOJ_2839_설탕배달_김하연 {
	static BufferedReader br;
	static int N;							// 설탕의 무게
	public static void main(String[] args) throws NumberFormatException, IOException {
		br=new BufferedReader(new InputStreamReader(System.in));
		// 설탕의 무게를 입력 받는다.
		N=Integer.parseInt(br.readLine());
		
		// 최소 봉지의 수
		int cnt=0;
		
		// 5로 나누어질 수 있을 때까지 3kg씩 뺀다.
		while (true) {
			// N이 음수인 경우
			if (N<0) {
				break;
			}
			// 5로 나눌 수 있는 경우
			else if(N%5==0) {
				break;
			}
			// 다음 수도 3으로 나눌 수 있을 경우
			else if (N-3>=0) {
				cnt+=1;
			}
			N-=3;
		}
		// 음수인 경우, Nkg 설탕을 정확하게 담을 수 없다.
		if (N<0) {
			System.out.println(-1);
		}
		// N이 0인 경우, 3kg만으로만 담을 수 있다.
		else if(N==0) {
			System.out.println(cnt);
		}
		// 남은 설탕 무게에서 사용할 수 있는 5kg 봉지 개수를 더한다.
		else {
			System.out.println(cnt+(N/5));
		}
		
	}

}
