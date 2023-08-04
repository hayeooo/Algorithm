package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparatorTest {
	public static void main(String[] args) {
		List<User> user=new ArrayList<User>();
		user.add(new User("a",12));
		user.add(new User("a",10));
		user.add(new User("bc",11));
		user.add(new User("acd",11));
		
		Collections.sort(user);
		for (int i=0;i<user.size();i++) {
			System.out.println(user.get(i));
		}
	}
}

class User implements Comparable<User>{
	private String name;
	private int age;
	
	public User(String name, int age) {
		this.name=name;
		this.age=age;
	}
	@Override
	public int compareTo(User o) {
		if (age>o.age) return 1;
		else if (age<o.age) return -1;
		else return name.compareTo(o.name);
	}
	
	public String toString() {
		return "name: "+name+" age: "+age;
	}
	
}