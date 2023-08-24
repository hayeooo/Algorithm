package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * BOJ 1759: ��ȣ�����
 * �ּ� �� ���� ������ �ּ� �� ���� �������� �����Ǿ� �ִ� ���� ����� ������ ��ȣ ���
 */
public class BOJ_1759_��ȣ�����_���Ͽ� {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int L, C;				// ���� �ٸ� L ���� ���ĺ� �ҹ���, C ���� ���ڰ� �־�����.
	static char[] alphas;
	static char[] comb;
	public static void main(String[] args) throws IOException {
		
		br=new BufferedReader(new InputStreamReader(System.in));
		
		st=new StringTokenizer(br.readLine().trim());
		// L, C �Է� �ޱ�
		L=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		
		// ���ĺ� ������ ������ �迭 ũ�� �ʱ�ȭ�ϱ�
		alphas=new char[C];
		
		// C ���� ���ĺ��� �Է¹޴´�.
		st=new StringTokenizer(br.readLine().trim());
		for (int idx=0;idx<C;idx++) {
			alphas[idx]=st.nextToken().charAt(0);
		}
		
		// ���� ������ ��ȣ���� ����� ���� ���ĺ� �迭�� �����Ѵ�.
		Arrays.sort(alphas);
		comb=new char[L];
		combinations(0,0,0,0);
	}
	// �ּ� �� ���� ������ �ּ� �� ���� �������� ������ ��ȣ��
	public static void combinations(int cnt,int startIdx,int consonants, int vowels) {
		// ��ȣ���� �ϼ��Ǿ��� ��
		if (cnt==L) {
			// �ּ� �ΰ��� ������ �ּ� �� ���� �������� �̷������ Ȯ��
			// ���ǿ� �����Ѵٸ� ����Ѵ�.
			if (consonants>=2 && vowels>=1) {
				for (int pwIdx=0;pwIdx<L;pwIdx++) {
					System.out.print(comb[pwIdx]);
				}
				System.out.println();
			}
			return;
		}
		// ���� ��ġ�� �� ���ĺ��� �����Ѵ�.
		for (int idx=startIdx;idx<C;idx++) {
			comb[cnt]=alphas[idx];			// ���ĺ� �ϳ��� �����Ѵ�.
			// ������ ���
			// ������ ������ �ϳ� ������Ű�� ���� ��ġ�� �� ���ĺ��� �����Ѵ�.
			if (comb[cnt]=='a'||comb[cnt]=='e'||comb[cnt]=='i'||comb[cnt]=='o'||comb[cnt]=='u') {
				combinations(cnt+1,idx+1,consonants,vowels+1);
			}
			// ������ ���
			// ������ ������ �ϳ� ������Ű�� ���� ��ġ�� �� ���ĺ��� �����Ѵ�.
			else {
				combinations(cnt+1,idx+1,consonants+1,vowels);
			}
			
		}
	}
}
