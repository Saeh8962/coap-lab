import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.MediaTypeRegistry;

public class Task2 extends Util.BaseTask {
	/**
	 * Query the temperature of one of the nodes.
	 */	
	public void run() {
		/* Store the URI of the CoAP resource you want to query
		 * in the String uri. */ 
		String uri = ...;
		/* Construct a new client object for this URI. */
		CoapClient client = ...;
		
		/* Perform a GET request with the client object. */
		CoapResponse response = ...;
		/* Deal with the response that we got. */
		handleResponse(response);
	}
	
	/**
	 * Handle a response received from a node.
	 * 
	 * Prints a warning if the response is invalid. Otherwise prints out
	 * the temperature at the node. If the temperature is above 50,
	 * the red LED on the node is turned on. If the temperature is below 50,
	 * the red LED on the node is turned off.
	 */	
	public static void handleResponse(CoapResponse response) {
		if (...) {
			/* Notify user if an error occurred. */
			System.out.println("An error occurred!");
		} else {
			/* Extract the temperature from the response and print it. */
			int temp = ...;
			int nodeId = ...;
			System.out.println("The temperature on node " + nodeId + " is " + temp);
			
			/* Construct a new Client object for the node's LED resource.
			 * Hint: Use Util.getBaseUri(response) for constructing the URI. */
			String uri = ...;
			CoapClient client = new CoapClient(uri);
			
			if (temp > 50) {
				/* If the temperature is too high, turn on the red LED. */
				client.post(..., MediaTypeRegistry.TEXT_PLAIN);
			} else {
				/* If the temperature is okay, turn off the red LED. */
				client.post(..., MediaTypeRegistry.TEXT_PLAIN);
			}
		}
	}
	
	public static void main(String[] argv) throws Exception {
		Task2 t = new Task2();
		t.run();
	}
}
