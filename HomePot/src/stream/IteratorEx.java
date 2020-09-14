package stream;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorEx {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("혹일동", "일동", "러블리");
		Iterator<String> iterator = list.iterator();
		while (iterator.hasNext()) {
			String name = iterator.next();
			System.out.println(name);
		}
	}
}