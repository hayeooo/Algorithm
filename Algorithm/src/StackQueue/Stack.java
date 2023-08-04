package StackQueue;

import java.util.Arrays;

/*
 * Stack
 * LIFO �ڷᱸ��
 * 1. push(): ���ÿ� ��� �߰�
 * 2. pop(): top�� ��ġ�� ��� ����
 * 3. peek(): stack top�� �ִ� ��� �ݳ�
 * 4. isEmpty(): stack�� ����ִ��� �˻�
 * */
public class Stack {
	int top = -1; // ���� stack top�� ��ġ
	Object[] arr; // stack�� ��Ҹ� �����ϴ� �迭
	int N = 0; // ���� stack�� ����� ����� ��

	public Stack() { // stack ��ü�� �����ϸ� �迭�� �����Ѵ�.
		arr = new Object[] {};
	}

	// ���ÿ� ��� �߰�
	public void push(Object element) {
		N += 1; // ���� stack�� ����� ����� ���� �ϳ� �ø���.
		Object[] tmp = new Object[N]; // ���ο� ��Ұ� �� �� �ִ� �迭�� ���� �Ҵ��Ѵ�.
		System.arraycopy(arr, 0, tmp, 0, N - 1); // ������ ����Ǿ��ִ� ��Ҹ� �����Ѵ�.

		top += 1; // stack�� top ��ġ�� �����Ѵ�.
		tmp[top] = element; // top ��ġ�� ���ο� ��Ҹ� �ִ´�.
		arr = tmp; // ����� stack�� �����Ѵ�.
	}

	// stack�� ��� ����
	public Object pop() {
		if (isEmpty()) { // ������ ����� ��� -1 ����
			return null;
		}
		Object last = arr[top]; // ������ ��Ҹ� �̸� ����
		arr = Arrays.copyOfRange(arr, 0, top); // ������ ��Ҹ� �����ϰ� ���ʿ� ��ġ�� ��ҵ��� ����
		top -= 1; // top�� ��ġ ����
		return last; // ��� ����
	}

	// stack top�� �ִ� ��� �ݳ�
	public Object peek() {
		return arr[top];
	}

	// ���� ������ ������� �˻�
	public boolean isEmpty() {
		if (top == -1)
			return true;
		return false;
	}

	// ���� ������ ũ�� ����
	public int size() {
		return N;
	}
}
