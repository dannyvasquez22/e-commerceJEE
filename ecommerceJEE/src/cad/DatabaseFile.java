package cad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DatabaseFile {

	public static String[] read() {
		String[] settings = new String[5];
		
		File archivo;
		FileReader fr = null;
		BufferedReader br;
		String[] data;
		
		try {
			archivo = new File("config.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			int i = 0;
			while (br.ready()) {
				data = br.readLine().split("=");
				settings[i] = data[1];
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return settings;
	}
	
}
