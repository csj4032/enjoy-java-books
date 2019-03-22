package chapter05.item30;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
public class RecursiveTypeBoundTest {

	@Test
	public void recursive() {
		List<Recursive> list = new ArrayList();
		list.add(new Recursive(1));
		list.add(new Recursive(2));
		Recursive recursive = RecursiveTypeBound.max(list);
		log.info("Max : {}", recursive.getId());
	}
}
