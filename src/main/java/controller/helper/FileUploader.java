package controller.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUploader {
	public static String ABSOULUTE_BASE_LOCATION = "C:\\Users\\LAPTOP\\Documents\\PTIT Learning\\2021 - 1 - Phan tich thiet ke he thong thong tin\\Projects\\BookStore\\src\\main\\webapp\\images\\upload\\";

	private InputStream in;
	private FileOutputStream out;
	
	
	public FileUploader(String newFileName, InputStream in) throws IOException {
		super();
		this.in = in;
		File file = new File(ABSOULUTE_BASE_LOCATION + newFileName);
		if(!file.exists()) {
			file.createNewFile();
		}
		this.out = new FileOutputStream(file);
	}
	
	public void writeToServer() throws IOException {
		byte[] buffer = new byte[4096];
		int i;
		while( (i = in.read(buffer)) != -1) {
			out.write(buffer);
		}
	}
	
}
