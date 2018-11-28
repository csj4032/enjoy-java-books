package chapter10.item72;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;

@Slf4j
public class CompleteFuture {

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		StatisticsMapper statisticsMapper = new StatisticsMapper() {
			@Override
			public List<Member> findByRegTimeBetween(long l, long l1) {
				return new ArrayList<Member>(){{
					add(new Member(1L));
					add(new Member(2L));
				}};
			}

			@Override
			public int hashCode() {
				return super.hashCode();
			}
		};

		List<Member> members = new ArrayList<>();
		CompletableFuture<List<Member>> completableFuture = CompletableFuture.supplyAsync(() -> statisticsMapper.findByRegTimeBetween(0l, 1300000000l));
		CompletableFuture<List<Member>> completableFuture1 = CompletableFuture.supplyAsync(() -> statisticsMapper.findByRegTimeBetween(1300000001l, 1325000000l));
		CompletableFuture<List<Member>> completableFuture2 = CompletableFuture.supplyAsync(() -> statisticsMapper.findByRegTimeBetween(1325000001l, 1350000000l));
		CompletableFuture<List<Member>> completableFuture3 = CompletableFuture.supplyAsync(() -> statisticsMapper.findByRegTimeBetween(1350000001l, 1375000000l));
		CompletableFuture<List<Member>> completableFuture4 = CompletableFuture.supplyAsync(() -> statisticsMapper.findByRegTimeBetween(1375000001l, 1400000000l));
		CompletableFuture<List<Member>> completableFuture5 = CompletableFuture.supplyAsync(() -> statisticsMapper.findByRegTimeBetween(1400000001l, 1425000000l));
		CompletableFuture<List<Member>> completableFuture6 = CompletableFuture.supplyAsync(() -> statisticsMapper.findByRegTimeBetween(1425000001l, 1450000000l));
		CompletableFuture<List<Member>> completableFuture7 = CompletableFuture.supplyAsync(() -> statisticsMapper.findByRegTimeBetween(1450000001l, 1514259047l));

		members.addAll(completableFuture.get());
		members.addAll(completableFuture1.get());
		members.addAll(completableFuture2.get());
		members.addAll(completableFuture3.get());
		members.addAll(completableFuture4.get());
		members.addAll(completableFuture5.get());
		members.addAll(completableFuture6.get());
		members.addAll(completableFuture7.get());

		List<Statistics.RequestMemberBetween> memberBetweens = new ArrayList<>();
		memberBetweens.add(Statistics.RequestMemberBetween.builder().from(1425000001l).to(1450000000l).build());
		memberBetweens.add(Statistics.RequestMemberBetween.builder().from(1450000001l).to(1475000000l).build());
		memberBetweens.add(Statistics.RequestMemberBetween.builder().from(1475000001l).to(1514259047l).build());
		memberBetweens.parallelStream().map(req -> statisticsMapper.findByRegTimeBetween(req.getFrom(), req.getTo())).flatMap(e -> e.stream()).map(e -> Statistics.ResponseMember.builder().mId(e.getId()).build()).collect(toList());
	}
}
