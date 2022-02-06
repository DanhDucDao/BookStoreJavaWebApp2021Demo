package controller.helper;

import java.util.StringTokenizer;

public class FileNameUtil {
	private String name;
	private String extension;
	
	public FileNameUtil(String fileName) {
		StringTokenizer tokens = new StringTokenizer(fileName, ".");
		if(tokens.hasMoreTokens())
			name = tokens.nextToken();
		if(tokens.hasMoreTokens())
			extension = tokens.nextToken();
	}

	public String getName() {
		return name;
	}

	public String getExtension() {
		return extension;
	}
	
	
}
