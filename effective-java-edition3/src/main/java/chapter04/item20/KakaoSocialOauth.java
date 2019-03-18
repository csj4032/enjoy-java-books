package chapter04.item20;

public class KakaoSocialOauth extends SocialOauthAbstract {

	public KakaoSocialOauth() {
		this.initialize();
	}

	private void initialize() {
		this.accessToken = "kakao";
	}
}
