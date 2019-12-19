package com.genius.stream;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(visibility = Value.Style.ImplementationVisibility.PRIVATE)
public interface Person {
	String getName();
	String getAddress();
}