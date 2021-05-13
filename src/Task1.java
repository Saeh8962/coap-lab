import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;

public class Task1 extends Util.BaseTask {
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
	 * Prints a warning if the response is invalid, otherwise print out
	 * the temperature at the node.
	 */
	public static void handleResponse(CoapResponse response) {
		if (Util.isInvalid(response)) {
			/* Notify user if an error occurred. */
			System.out.println("An error occurred!");
		} else {
			/* Extract the temperature from the response and print it. */
			int temp = Util.parseTemperature(response);
			System.out.println("The temperature is " + temp);
		}
	}
	
	public static void main(String[] argv) throws Exception {
		Task1 t = new Task1();
		t.run();
	}
}
