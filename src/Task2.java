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
		String uri = "coap://[aaaa::212:7402:2:202]:5683/sensors/temp";
		/* Construct a new client object for this URI. */
		CoapClient client = new CoapClient(uri);
		
		/* Perform a GET request with the client object. */
		CoapResponse response = client.get();
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
		if (Util.isInvalid(response)) {
			/* Notify user if an error occurred. */
			System.out.println("An error occurred!");
		} else {
			/* Extract the temperature from the response and print it. */
			int temp = Util.parseTemperature(response);
			int nodeId = Util.getNodeId(response);
			System.out.println("The temperature on node " + nodeId + " is " + temp);
			
			/* Construct a new Client object for the node's LED resource.
			 * Hint: Use Util.getBaseUri(response) for constructing the URI. */
			String uri = Util.getBaseURI(response);
			uri = uri+"/actuators/leds?color=r";
			CoapClient client = new CoapClient(uri);
			
			if (temp > 50) {
				/* If the temperature is too high, turn on the red LED. */
				client.post("mode=on", MediaTypeRegistry.TEXT_PLAIN);
			} else {
				/* If the temperature is okay, turn off the red LED. */
				client.post("mode=off", MediaTypeRegistry.TEXT_PLAIN);
			}
		}
	}
	
	public static void main(String[] argv) throws Exception {
		Task2 t = new Task2();
		t.run();
	}
}
