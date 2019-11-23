package com.genius.reactive.rxPubsubApp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import rx.Subscriber;
import rx.Subscription;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
public class TemperatureController {

	private final TemperatureSensor temperatureSensor;

	public TemperatureController(TemperatureSensor temperatureSensor) {
		this.temperatureSensor = temperatureSensor;
	}

	@RequestMapping(value = "/temperature-stream", method = RequestMethod.GET)
	public SseEmitter events(HttpServletRequest request) {
		log.info("SSE stream opened for client " + request.getRemoteAddr());
		RxSeeEmitter emitter = new RxSeeEmitter();
		Subscription subscription = temperatureSensor.temperatureStream().subscribe(emitter.getSubscriber());
		return emitter;
	}

	static class RxSeeEmitter extends SseEmitter {
		static final long SSE_SESSION_TIMEOUT = 30 * 60 * 1000L;
		private final static AtomicInteger sessionIdSequence = new AtomicInteger(0);
		private final int sessionId = sessionIdSequence.incrementAndGet();
		private final Subscriber<Temperature> subscriber;

		RxSeeEmitter() {
			super(SSE_SESSION_TIMEOUT);
			this.subscriber = new Subscriber<>() {
				@Override
				public void onNext(Temperature temperature) {
					try {
						RxSeeEmitter.this.send(temperature);
						log.info("[{}] << {} ", sessionId, temperature.getValue());
					} catch (IOException e) {
						log.warn("[{}] Can not send event to SSE, closing subscription, message: {}", sessionId, e.getMessage());
						unsubscribe();
					}
				}

				@Override
				public void onError(Throwable e) {
					log.warn("[{}] Received sensor error: {}", sessionId, e.getMessage());
				}

				@Override
				public void onCompleted() {
					log.warn("[{}] Stream completed", sessionId);
				}
			};

			onCompletion(() -> {
				log.info("[{}] SSE completed", sessionId);
				subscriber.unsubscribe();
			});
			onTimeout(() -> {
				log.info("[{}] SSE timeout", sessionId);
				subscriber.unsubscribe();
			});
		}

		Subscriber<Temperature> getSubscriber() {
			return subscriber;
		}

		int getSessionId() {
			return sessionId;
		}
	}
}
