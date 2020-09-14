package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Students1Example {
	public static void main(String[] args) {
			List<Students1> list = Arrays.asList(new Students1("홍길동", 90), 
					new Students1("항굴동", 80));
			
			Stream<Students1> stream = list.stream();
			stream.forEach(s -> {
				String name = s.getName();
				int score = s.getScore();
				System.out.println(name + ", " + score);
			});
			
	}

}
