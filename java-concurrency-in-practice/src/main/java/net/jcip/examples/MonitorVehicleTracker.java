package net.jcip.examples;

import java.util.Map;

public class MonitorVehicleTracker {

	private final Map<String, MutablePoint> locations;

	public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
		this.locations = locations;
	}
}