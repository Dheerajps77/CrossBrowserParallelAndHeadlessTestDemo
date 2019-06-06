package Utilities;
import org.apache.log4j.Logger;


public class Log {
	 
    //Initialize Log4j instance
    private static Logger Log = Logger.getLogger(Log.class.getName());
 
    //We can use it when starting tests
    public static void StartLog (String testClassName){
        Log.info(testClassName);
    }
 
    //We can use it when ending tests
    public static void EndLog (String testClassName){
        Log.info(testClassName);
    }
 
    //Info Level Logs
    public static void Info (String message) {
        Log.info(message);
    }
 
    //Warn Level Logs
    public static void Warn (String message) {
        Log.warn(message);
    }
 
    //Error Level Logs
    public static void Error (String message, Exception e) {
        Log.error(message, e);
    }
 
    //Fatal Level Logs
    public static void Fatal (String message) {
        Log.fatal(message);
    }
 
    //Debug Level Logs
    public static void Debug (String message) {
        Log.debug(message);
    }
}