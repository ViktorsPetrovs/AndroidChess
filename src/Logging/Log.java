package Logging;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

public class Log {
	static {
		try {
			LogManager.getLogManager().readConfiguration(new FileInputStream("loggs/logging.properties"));
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}	
}
