package insight.newjava.ch01;

public interface HouseAddress {

	String DefaultCountry = "Korea";

	String getPostCode();

	String getAddress();

	String getDetailAddress();

	default String getCountryCode() {
		return getDefaultCountryCode();
	}

	private String getDefaultCountryCode() {
		return HouseAddress.DefaultCountry;
	}
}
