import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;

public class Task3 extends Util.BaseTask {
	/**
	 * Continuously query the temperature on all nodes.
	 */	
	public void run() {
		while (running) {
			/* For each node, construct a client, send a GET request, and
			 * handle the response. */
			for (int i=2;i<=6;i++) {
		
			String uri = "coap://[aaaa::212:7402:"+i+":"+i+"0"+i+"]/sensors/temp";
			/* Construct a new client object for this URI. */
			CoapClient client = new CoapClient(uri);
		
			/* Perform a GET request with the client object. */
			CoapResponse response = client.get();
			/* Deal with the response that we got. */
			Task2.handleResponse(response);
			}
			
			/* Sleep for 5 seconds. */
			Util.sleep(5);
		}
	}
	
	

	/**
	 * Stop the run loop.
	 */
	public void shutdown() {
		running = false;
		System.out.println("Shutdown complete.");
	}
	
	public static void main(String[] argv) throws Exception {
		Task3 t = new Task3();
		Util.handleShutdownRequest(t);
		t.run();
	}
}
