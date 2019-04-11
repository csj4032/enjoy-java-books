package chapter07.item43;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

@Slf4j
// https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
public class MethodReference {

	private static List<String> STRING_NUMBER;
	private static List<Integer> NUMBER;
	private static List<Instant> INSTANTS;
	private static List<Person> ROSTER;
	private static Person[] ROSTERASARRAY;

	@BeforeAll
	public static void init() {
		STRING_NUMBER = List.of("1", "2", "3");
		NUMBER = List.of(1, 2, 3, 4);
		INSTANTS = List.of(Instant.now(), Instant.now().minusMillis(10));
		ROSTER = List.of(new Person("A", LocalDate.now()), new Person("B", LocalDate.now().minus(1, ChronoUnit.YEARS)));
		ROSTERASARRAY = ROSTER.toArray(new Person[ROSTER.size()]);
	}

	@Test
	@DisplayName(value = "메서드 참조 유형 : 정적")
	public void staticMethodReference() {
		// Reference to a static method
		STRING_NUMBER.forEach(str -> Integer.parseInt(str));
		STRING_NUMBER.forEach(Integer::parseInt);
	}

	@Test
	@DisplayName(value = "메서드 참조 유형 : 한정적 (인스턴스)")
	public void instanceMethodReferenceBound() {
		// Reference to an instance method of a particular object
		Instant instant = Instant.now();
		INSTANTS.forEach(t -> instant.isAfter(t));
		INSTANTS.forEach(instant::isAfter);
		INSTANTS.forEach(t -> Instant.now().isAfter(t));
		INSTANTS.forEach(Instant.now()::isAfter);

		// Reference to an instance method of an arbitrary object of a particular type
		INSTANTS.forEach(t -> t.toString());
		INSTANTS.forEach(Instant::toString);

		ComparisonProvider myComparisonProvider = new ComparisonProvider();
		Arrays.sort(ROSTERASARRAY, myComparisonProvider::compareByName);
	}

	@Test
	@DisplayName(value = "메서드 참조 유형 : 비한정적 (인스턴스)")
	public void instanceMethodReferenceUnBound() {
		STRING_NUMBER.forEach(str -> str.toLowerCase());
		STRING_NUMBER.forEach(String::toLowerCase);
	}

	@Test
	@DisplayName(value = "메서드 참조 유형 : 클래스 생성자")
	public void classConstructMethodReference() {
		// Reference to a constructor
		List arrayList = STRING_NUMBER.stream().collect(toCollection(() -> new ArrayList<>()));
		STRING_NUMBER.stream().collect(toCollection(ArrayList::new));
		Assertions.assertEquals(STRING_NUMBER, arrayList);
	}

	@Test
	@DisplayName(value = "메서드 참조 유형 : 배열 생정자")
	public void arrayConstructMethodReference() {
		// Reference to a constructor
		NUMBER.stream().map(len -> new int[len]);
		List<int[]> arrayList = NUMBER.stream().map(int[]::new).map(e -> getInts(e)).collect(Collectors.toList());
		arrayList.forEach(e -> log.info("arrayList : {}", Arrays.toString(e)));
	}

	private int[] getInts(int[] e) {
		for (int i = 0; i < e.length; i++) e[i] = i;
		return e;
	}
}
//pushGAData('Button Click', '6_4_0'); event_link('D','600172680','4','0','');