import java.util.Scanner;

import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;


public class Util {
	public static abstract class BaseTask {
		boolean running = true;
		
		public abstract void run();
		public void shutdown() { }
	}
	
	public static void handleShutdownRequest(final BaseTask t) {
		new Thread() {
			public void run() {
				Scanner s = new Scanner(System.in);
				while (true) {
					String line = s.nextLine().trim().toLowerCase();
					if ("quit".equals(line)) {
						System.out.println("Shutting down ...");
						t.shutdown();
						break;
					}
				}
			}
		}.start();
	}
	
	public static int parseTemperature(CoapResponse response) {
		String responseText = response.getResponseText();
		if (responseText.startsWith("TEMP ")) {
			return Integer.parseInt(responseText.substring(5));
		} else {
			return -9999;
		}
	}
	
	public static String getBaseURI(CoapResponse response) {
		return "coap://[" + response.advanced().getSource().getHostAddress() + "]";
	}
	
	public static int getNodeId(CoapResponse response) {
		String addr = response.advanced().getSource().getHostAddress();
		return Integer.parseInt(addr.substring(addr.length()-1));
	}
	
	public static boolean isInvalid(CoapResponse response) {
		return response == null || response.getCode() != ResponseCode.CONTENT;
	}
	
	public static void debugInfo(CoapResponse response) {
		if (response == null) {
			System.out.println("Response is null");
		} else {
			System.out.println(response.advanced().getSource().getHostAddress() + " " + response.getCode() + " " + response.getResponseText());
		}
	}
	
	public static void sleep(long seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException ie) {	}
	}
}
