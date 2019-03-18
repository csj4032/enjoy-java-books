package chapter04.item15;

class PackagePrivate {

	public String type;

	public PackagePrivate(String type) {
		this.type = type;
	}

	private class privated {

		public String role;

		public privated(String role) {
			this.role = role;
		}
	}
}