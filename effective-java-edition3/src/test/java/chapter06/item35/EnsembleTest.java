package chapter06.item35;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EnsembleTest {

	@Test
	@DisplayName(value = "ensembleOrdinal")
	public void ensembleOrdinal() {
		assertThat(Ensemble.SOLO.ordinal(), is(0));
		assertThat(Ensemble.DOUBLE_QUARTET.ordinal(), is(8));
	}

	@Test
	@DisplayName(value = "ensemble")
	public void ensemble() {
		assertThat(Ensemble.SOLO.numberOfMusicians(), is(1));
		assertThat(Ensemble.DOUBLE_QUARTET.numberOfMusicians(), is(8));
	}
}