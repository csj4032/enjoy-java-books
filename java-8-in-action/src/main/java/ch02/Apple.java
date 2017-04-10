package ch02;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Apple {
	private Integer weight;
	private String color;
}
