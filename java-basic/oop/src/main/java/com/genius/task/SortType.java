package com.genius.task;

import java.util.Comparator;

public enum SortType implements Comparator<Task> {

	TITLE_DESC {
		@Override
		public int compare(Task o1, Task o2) {
			return o1.getTitle().compareTo(o2.getTitle());
		}
	},
	DATE_DESC {
		@Override
		public int compare(Task o1, Task o2) {
			return o1.getDate().compareTo(o2.getDate());
		}
	},
	TITLE_ASC {
		@Override
		public int compare(Task o1, Task o2) {
			return o2.getTitle().compareTo(o1.getTitle());
		}
	},
	DATE_ASC {
		@Override
		public int compare(Task o1, Task o2) {
			return o2.getDate().compareTo(o1.getDate());
		}
	};
}
