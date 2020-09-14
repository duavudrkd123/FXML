package stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Students1ExampleNoStream {
public static void main(String[] args) {
	List<Students1> list = Arrays.asList(new Students1("홍길동", 90), new Students1("김정의" , 120));
	Iterator<Students1> iterator = list.iterator();
	while(iterator.hasNext()) {
		Students1 stu = iterator.next();
		System.out.println("이름: " + stu.getName() + ", 점수:" + stu.getScore());
	}
	System.out.println("====================================================");
	List<Students1> jory = Arrays.asList(new Students1("Amay" , 40), 
			new Students1("오우오우 싸발적이고",  1818));
	Iterator<Students1> iterator2 = jory.iterator();
	while(iterator2.hasNext()) {
		Students1 stu1 = iterator2.next();
		System.out.println
		("이름1: " + stu1.getName() + "\t점수1: " + stu1.getScore());
	}
	System.out.println("====================================================");
	List<Students1> loly = Arrays.asList(new Students1("이번엔", 900), new Students1("보지말아야지" , 800), 
			new Students1("볼꺼같아 ㅜㅜ", 700)	);
	Iterator<Students1> into = loly.iterator();
	while(into.hasNext()) {
		Students1 stu2 = into.next();
		System.out.println("이름: " + stu2.getName() + "점수" + stu2.getScore());
		
	}
	System.out.println("====================================================");
	List<Students1> holy = Arrays.asList(new Students1("홀리", 18), 
			new Students1("몰리", 122), new Students1("STAY", 113));
	Iterator<Students1> the = holy.iterator();
	while (the.hasNext()) {
		Students1 stu3 = the.next();
		System.out.println("name: " + stu3.getName() + "Score: " + stu3.getScore());
	
	
}
	System.out.println("====================================================");
	System.out.println();
}
}
