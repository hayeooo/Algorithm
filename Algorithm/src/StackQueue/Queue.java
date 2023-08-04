package StackQueue;
/*
 * Queue
 * FIFO �ڷᱸ��
 * 1. offer(): queue�� ��� �߰�
 * 2. poll(): queue ��� ����
 * 3. peek(): queue�� ù ��° ��� ���� 
 * 4. isEmpty(): queue�� ����ִ��� �˻�
 * 5. size(): queue�� ���� ũ�� ����
 * */

import java.util.Arrays;

public class Queue {

	int top; // queue�� ������ ��� ��ġ
	Object[] arr; // queue�� ��Ҹ� ��� �迭
	int N; // queue �ȿ� ����ִ� ��� ����

	// ������ ȣ�� ��, �ʱ�ȭ
	Queue() {
		arr = new Object[] {};
		top = -1; // ����ִ� ����
		N = 0; // queue�� ũ��� 0
	}

	// queue�� ��� �߰�
	public void offer(Object element) {
		N += 1; // queue�� ũ�� 1 ����
		Object[] tmp = new Object[N]; // ���ο� ��Ҹ� ���� �迭 �ӽ� ����
		System.arraycopy(arr, 0, tmp, 0, N - 1); // ������ ���� ��ҵ��� ����

		top += 1; // ������ ��Ұ� ��� ��ġ ����
		tmp[top] = element; // ������ ��� �߰�
		arr = tmp; // ����� queue �ݿ�
	}

	// queue���� ��� ����
	public Object poll() {
		if (isEmpty())
			return null; // queue�� ����ִ� ���¶�� null ��ȯ
		Object first = arr[0]; // ������ ��
		arr = Arrays.copyOfRange(arr, 1, N); // ù ��° ��Ҹ� ������ ������ ��ҵ� ����
		N -= 1; // queue�� �� ����� ���� 1�� ����
		top -= 1; // queue�� ����ִ� ������ ��� ��ġ ����
		return first; // ù��° ��� ��ȯ
	}

	// queue���� ù ��° ���� ��ȯ
	public Object peek() {
		if (isEmpty())
			return null; // queue�� ����ִٸ� null ��ȯ
		return arr[0]; // ù ��° ��� ��ȯ
	}

	// queue�� ����ִ��� �˻�
	public boolean isEmpty() {
		if (top == -1)
			return true;
		return false;
	}

	// queue�� size ��ȯ
	public int size() {
		return N;
	}

}
