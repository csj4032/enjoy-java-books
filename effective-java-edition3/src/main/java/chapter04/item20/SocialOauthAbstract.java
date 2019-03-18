package chapter04.item20;

abstract class SocialOauthAbstract implements SocialOauth {

	protected String accessToken;

	public boolean isAuthorized() {
		return !accessToken.isBlank();
	}
}