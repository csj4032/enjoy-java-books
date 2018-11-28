package chapter02.item01;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 서비스 등록과 접근이 사용되는 객체 생성 불가능 클래스
public class Services {

	private Services() {

	}

	private static final Map<String, Provider> providers = new ConcurrentHashMap<>();
	public static final String DEFAULT_PROVIDER_NAME = "<def>";

	public static void registerDefaultProvider(Provider provider) {
		registerProvider(DEFAULT_PROVIDER_NAME, provider);
	}

	public static void registerProvider(String defaultProviderName, Provider provider) {
		providers.put(defaultProviderName, provider);
	}

	public static Service newInstance() {
		return newInstance(DEFAULT_PROVIDER_NAME);
	}

	public static Service newInstance(String name) {
		Provider p = providers.get(name);
		if (p == null) throw new IllegalArgumentException("No provider");
		return p.newService();
	}
}