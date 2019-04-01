package chapter05.item28;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
public class ChooserTest {

	@Test
	public void chooserTest() {
		var chooser = new Chooser(List.of("A", "B"));
		var choose = chooser.choose();
		log.info("{}", choose);
	}
}
