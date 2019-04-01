package chapter04.item20;

import org.junit.jupiter.api.Test;

public class SocialOauthSkeletal {

	@Test
	public void skeletal() {
		SocialOauth kakao = new KakaoSocialOauth();
		System.out.println(kakao.isAuthorized());
	}
}
