package chapter10.item72;

import lombok.Builder;
import lombok.Data;

public class Statistics {

	@Data
	@Builder
	public static class RequestMemberBetween {
		Long from;
		Long to;
	}

	@Data
	@Builder
	public static class ResponseMember {
		Long mId;
	}
}
