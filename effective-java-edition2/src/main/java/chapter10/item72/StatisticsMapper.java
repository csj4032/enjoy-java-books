package chapter10.item72;

import java.util.List;

public interface StatisticsMapper {

	List<Member> findByRegTimeBetween(long from, long to);
}
