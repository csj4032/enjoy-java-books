package com.genius.payroll.domain;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
public class Member {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long memberId;
}
