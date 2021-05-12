import java.util.ArrayList;
import java.util.List;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;
import org.eclipse.californium.core.CoapObserveRelation;
import org.eclipse.californium.core.CoapResponse;

public class Task4 extends Util.BaseTask implements CoapHandler {
	/**
	 * List of CoapObserveRelation objects for all active observations.
	 */
	List<CoapObserveRelation> relations = new ArrayList<CoapObserveRelation>();
	
	/**
	 * Observe the temperature on all nodes.
	 */		
	public void run() {
		/* For each node, construct a client, start an observation, and
		 * add the CoapObserveRelation object to the list "relations". */
		for (int i=2;i<=6;i++) {
			String uri = ...;
			CoapClient client = new CoapClient(uri);
			
			CoapObserveRelation rel = client.observe(this);
			relations.add(rel);
		}
	}
	
	/**
	 * Cancel all active observations.
	 */	
	public void shutdown() {
		running = false;
		/* Cancel all active observations. */
		for (CoapObserveRelation rel : relations) {
			...
		}
		System.out.println("Shutdown complete.");
	}
	
	/**
	 * Handle a response from an active observation.
	 */
	public void onLoad(CoapResponse response) {
		...
	}

	public void onError() {	}
	
	public static void main(String[] argv) throws Exception {
		Task4 t = new Task4();
		Util.handleShutdownRequest(t);
		t.run();
	}
}
