package insight.newjava.ch01;

import java.util.List;
import java.util.stream.Stream;

public class KoreaHouseAddress implements HouseAddress {

	private String postCode;
	private String address;
	private String detailAddress;

	public KoreaHouseAddress(String postCode, String address, String detailAddress) {
		this.postCode = postCode;
		this.address = address;
		this.detailAddress = detailAddress;
	}

	@Override
	public String getPostCode() {
		return postCode;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String getDetailAddress() {
		return detailAddress;
	}

	@Override
	public String getCountryCode() {
		return HouseAddress.super.getCountryCode();
	}

	public static void main(String[] args) {
		var address = new KoreaHouseAddress("11111", "", "");
		System.out.println(address.getCountryCode());

		Stream.Builder<String> builder = Stream.builder();
		builder.accept("a");
		builder.accept("a");
		builder.accept("a");
		builder.build().forEach(System.out::println);

		Stream.<String>builder()
				.add("1")
				.add("2")
				.build()
				.forEach(System.out::println);;

	}
}
