package cn.edu.nju.lc.springstudy;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SpringStudyApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void testIterator() {
		MyList myList = new MyList();
		for (String s : myList) {
			System.out.println(s);
		}
	}

	private static class MyList implements Iterable<String> {

		private List<String> arr = Arrays.asList("1", "2");

		@Override
		public Iterator<String> iterator() {
			return new Iterator<String>() {
				private int index = 0;
				@Override
				public boolean hasNext() {
					return index < arr.size();
				}

				@Override
				public String next() {
					return arr.get(index++);
				}
			};
		}
	}

}
