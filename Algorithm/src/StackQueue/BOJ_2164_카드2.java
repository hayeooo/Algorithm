package StackQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

/*
 * BOJ 2164: ī��2
 * N���� ī�尡 �ְ�, 1�� ī�尡 ���� ����, N�� ī�尡 ���� �Ʒ��� �ִ�.
 * ���� ���� �ִ� ī�带 �ٴڿ� ������, �� ���� ���� ���� �ִ� ī�带 ���� �Ʒ��� �ű��.
 * ī�尡 �� �� ���� ������ �ݺ��Ѵ�.
 * 
 * ���� ���� �ִ� ī�带 ������ ���� �Ʒ��� ī�带 �߰��ϹǷ�
 * ����⼺�� ������ ArrayDeque�� Ȱ���Ѵ�.
 * 
 * */
public class BOJ_2164_ī��2 {
	static BufferedReader br;
	static int N; // N���� ī��
	static Deque<Integer> que; // ������ ī�� ������ �����ϴ� queue

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		que = new ArrayDeque<>(); // queue ��ü ����

		N = Integer.parseInt(br.readLine()); // N �Է�

		// 1���� N���� ī�带 �ִ´�.
		for (int card = 1; card <= N; card++) {
			que.offer(card);
		}

		// ī�尡 �� �� ���� ������ �ݺ��Ѵ�.
		while (que.size() > 1) {
			que.poll(); // ���� ���� �ִ� ī�带 �ٴڿ� ������.
			int toMoved = que.poll(); // �� ����, ���� ���� �ִ� ī�带
			que.offer(toMoved); // ���� �Ʒ��� �ִ� ī�� ������ �ű��.
		}
		System.out.println(que.peek()); // ���� �������� ���� �Ǵ� ī�带 ����Ѵ�.
	}
}
