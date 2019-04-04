package chapter06.item36;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

public class TextTest {

	private Text text;
	private ByteArrayOutputStream outContent;

	@BeforeEach
	private void setUp() {
		this.text = new Text();
		outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void applyStylesTest() {
		Text text = mock(Text.class);
		ArgumentCaptor<Set> valueCapture = ArgumentCaptor.forClass(Set.class);
		doNothing().when(text).applyStyles(valueCapture.capture());
		text.applyStyles(Set.of(Text.Style.UNDERLINE, Text.Style.BOLD));
		assertEquals(Set.of(Text.Style.UNDERLINE, Text.Style.BOLD), valueCapture.getValue());
	}

	@Test
	public void applyStylesPrintTest() {
		text.applyStyles(Set.of(Text.Style.UNDERLINE, Text.Style.BOLD));
		assertEquals("UNDERLINEBOLD", outContent.toString());
	}
}