package processing;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author makafui
 */
public class ThreadedStreamHandler extends Thread {
	
	// Logging Functionality
	private static final Logger LOGGER = Logger.getLogger( ThreadedStreamHandler.class.getName() );

    InputStream inputStream;
    OutputStream outputStream;
    PrintWriter printWriter;
    StringBuilder outputBuffer = new StringBuilder();
    
   
    ThreadedStreamHandler(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    

    public void run() {
    
        BufferedReader bufferedReader = null;
        
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
               outputBuffer.append(line + "\n");
            }
        } catch (IOException ioe) {
        	LOGGER.log(Level.SEVERE, ioe.toString(), ioe);
            ioe.printStackTrace();

        } catch (Throwable t) {
           LOGGER.log(Level.SEVERE, t.toString(), t);
        } finally {
            try {
                bufferedReader.close();
                LOGGER.warning("Threaded Stream was interrupted");
            } catch (IOException e) {
                // ignore this one
            	   LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
    }

    private void doSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public StringBuilder getOutputBuffer() {
        return outputBuffer;
    }

}