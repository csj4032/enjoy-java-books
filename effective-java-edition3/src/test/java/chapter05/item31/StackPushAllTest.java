package chapter05.item31;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

public class StackPushAllTest {

	@Test
	public void pushAll() {
		List<? extends Number> list = List.of(1, 2, 3, 4);
		StackPushAll<Number> stackPushAll = new StackPushAll();
		stackPushAll.pushAll(list);
		System.out.println(stackPushAll);
	}

	@Test
	public void popAll() {
		List<Number> list = new ArrayList();
		StackPushAll<Integer> stackPushAll = new StackPushAll();
		stackPushAll.add(2);
		stackPushAll.add(1);
		stackPushAll.popAll(list);
		System.out.println(list);
	}

	@Test
	public void max() {
		List<String> list = List.of("a", "b");
		var max = StackPushAll.max(list);
		System.out.println(max);
	}

	@Test
	public void scheduledFutureMax() {
		List<ScheduledFuture<?>> scheduledFutures = List.of();
		StackPushAll.max(scheduledFutures);
	}
}