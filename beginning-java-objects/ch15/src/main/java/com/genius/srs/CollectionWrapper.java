package com.genius.srs;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Getter
public abstract class CollectionWrapper {

	private String pathToFile;

	public boolean initializeObjects(String pathToFile, boolean primary) {
		this.pathToFile = pathToFile;
		String line;
		boolean outcome = true;

		try (BufferedReader bIn = new BufferedReader(new FileReader(getClass().getClassLoader().getResource(pathToFile).getFile()))) {
			line = bIn.readLine();
			while (line != null) {
				if (primary) {
					parseDataPrimary(line);
				} else {
					parseDataSecondary(line);
				}
				line = bIn.readLine();
			}
		} catch (IOException e) {
			outcome = false;
		}

		return outcome;
	}

	protected abstract void parseDataPrimary(String line);

	protected abstract void parseDataSecondary(String line);

}
