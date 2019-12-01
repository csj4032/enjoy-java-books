package com.genius.reactive;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.time.Duration;

import static java.lang.String.format;
import static java.time.LocalDateTime.now;

public class CpuLoadTest {

	@Test
	public void loadCpuData() throws InterruptedException {
		OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		Flux<Double> loadStream = Flux.interval(Duration.ofMillis(100)).map(ignore -> operatingSystemMXBean.getSystemCpuLoad());
		System.out.println("Application pid :" + applicationPid());
		loadStream
				.filter(load -> !load.isNaN())
				.subscribe(load -> System.out.println(format("[%s] System CPU load: %2.2f %%", now(), load * 100.0)));

		Thread.sleep(10_000);
	}

	private int applicationPid() {
		String appName = ManagementFactory.getRuntimeMXBean().getName();
		String pidInString = appName.split("@")[0];
		return Integer.parseInt(pidInString);
	}

}
