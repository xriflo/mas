package platform;

import java.util.ArrayList;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.core.Runtime;

public class PlatformRunner {
	
	public static Profile mainProfile;

	//containers
	public static AgentContainer mainContainer;
	//agents
	public static AgentController environmentAgent;
	public static AgentController representativeAgent;
	public static ArrayList<AgentController> bookingAgents = new ArrayList<AgentController>();

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setProfile();
		setContainers();
		createAgents();		
		startAgents();
	}
	
	
	public static void setProfile() {
		Properties mainProp = new ExtendedProperties();
		mainProp.setProperty(Profile.GUI, "true"); // start the JADE GUI
		mainProp.setProperty(Profile.MAIN, "true"); // is main container
		mainProp.setProperty(Profile.CONTAINER_NAME, "flori-main"); // you name it
		mainProfile = new ProfileImpl(mainProp);
		
	}
	
	public static void setContainers() {
		System.out.println("Setting containers...");
		Runtime rt = Runtime.instance();
		mainContainer = rt.createMainContainer(mainProfile);
		}
	
	public static void createAgents() {
		System.out.println("Setting agents...");
		try {
			environmentAgent = mainContainer.createNewAgent("environmentAgentController", "agents.EnvironmentAgent", null);
			representativeAgent = mainContainer.createNewAgent("representativeAgentController", "agents.RepresentativeAgent", null);
			for(Integer i=0; i<2; i++) {
				bookingAgents.add(mainContainer.createNewAgent("bookingAgentController"+i, "agents.BookingAgent", null));
			}
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void startAgents() {
		try {
			environmentAgent.start();
			representativeAgent.start();
			for(Integer i=0; i<2; i++)
				bookingAgents.get(i).start();
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
