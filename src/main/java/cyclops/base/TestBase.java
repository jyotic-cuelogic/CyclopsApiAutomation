package cyclops.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

	public Properties prop;

	public TestBase() {
		try {
			prop = new Properties();

			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + File.separator + "src"
					+ File.separator + "main" + File.separator + "java" + File.separator + "cyclops" 
					+ File.separator + "config" + File.separator + "config.properties");
			prop.load(ip);

		}

		catch (FileNotFoundException e) {

			e.printStackTrace();
		}

		catch (IOException e) {

			e.printStackTrace();
		}

	}

}
