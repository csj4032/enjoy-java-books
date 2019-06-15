package com.genius.basic;

import java.text.SimpleDateFormat;

public class ThreadSafeFormatter {

	public static ThreadLocal<SimpleDateFormat> dataFormatter = new ThreadLocal<>() {

		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}

		@Override
		public SimpleDateFormat get() {
			return super.get();
		}
	};
}
