package com.proton.steps;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.proton.domain.LogicalServer;
import com.proton.domain.Server;
import com.proton.domain.VPN;
import com.proton.utils.ConfigurationUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class LogicalServerSteps {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogicalServerSteps.class);
	private String apiUrl;
	private Response response;
	int lowLoadedServerCount = 0, mediumLoadedServerCount = 0, highLoadedServerCount = 0;
	int basicServerCount = 0, secureCoreServerCount = 0, torServerCount = 0, p2pServerCount = 0, freeServerCount = 0;
	int pluServerCount = 0, regularServerCount = 0;
	int onlineServerCount = 0, offlineServerCount = 0;

	public void getApiUrl() {
		apiUrl = ConfigurationUtils.getApiBaseUrl();
	}

	public void getRequest() {
		RestAssured.baseURI = apiUrl;
		RequestSpecification httpRequest = RestAssured.given();
		response = httpRequest.get();

	}

	public void sendApiRequest() {
		SerenityRest.useRelaxedHTTPSValidation();
		response = SerenityRest.given().baseUri(ConfigurationUtils.getApiBaseUrl()).when()
				.get(ConfigurationUtils.getApiBaseUrl());

	}

	@Step
	/**
	 * This method validates that all the required values are retrieved on
	 * performing API get request
	 */
	public void verifyResponse() {
		ResponseBody body = response.getBody();
		VPN vpn = body.as(VPN.class);
		List<LogicalServer> logicalServers = vpn.getLogicalServers();
		for (LogicalServer logicalServer : logicalServers) {
			Assert.assertNotNull(logicalServer.getID());
			Assert.assertNotNull(logicalServer.getEntryCountry());
			Assert.assertNotNull(logicalServer.getExitCountry());
			Assert.assertNotNull(logicalServer.getName());
			Assert.assertNotNull(logicalServer.getServers());
			Assert.assertNotNull(logicalServer.getDomain());
			Assert.assertNotNull(logicalServer.getLoad());
			Assert.assertNotNull(logicalServer.getTier());
			// Assert.assertNotNull(logicalServer.getCity()); //can be null
			// Assert.assertNotNull(logicalServer.getRegion()); //can be null
			Assert.assertNotNull(logicalServer.getFeatures());
			Assert.assertNotNull(logicalServer.getLocation());
			Assert.assertNotNull(logicalServer.getScore());
			Assert.assertNotNull(logicalServer.getStatus());
			List<Server> servers = logicalServer.getServers();
			for (Server server : servers) {
				Assert.assertNotNull(server.getEntryIP());
				Assert.assertNotNull(server.getExitIP());
				Assert.assertNotNull(server.getDomain());
				Assert.assertNotNull(server.getID());
				Assert.assertNotNull(server.getStatus());
			}
		}

	}

	@Step
	/** This method validate the load on each server */
	public void listServersWithLoad() {
		ResponseBody body = response.getBody();
		VPN vpn = body.as(VPN.class);
		List<LogicalServer> logicalServers = vpn.getLogicalServers();
		ArrayList<String> lowLoad = new ArrayList<String>();
		ArrayList<String> mediumLoad = new ArrayList<String>();
		ArrayList<String> highLoad = new ArrayList<String>();

		for (LogicalServer logicalServer : logicalServers) {
			Assert.assertTrue(logicalServer.getLoad() > 0);
			if (logicalServer.getLoad() < 50) {
				lowLoadedServerCount++;
				lowLoad.add(logicalServer.getName());
				LOGGER.info("Server " + logicalServer.getName() + " is in green");

			} else if (logicalServer.getLoad() > 50 && logicalServer.getLoad() < 90) {
				mediumLoadedServerCount++;
				mediumLoad.add(logicalServer.getName());
				LOGGER.info("Server " + logicalServer.getName() + " is in yellow");
			} else if (logicalServer.getLoad() > 90) {
				highLoadedServerCount++;
				highLoad.add(logicalServer.getName());
				LOGGER.info("Server " + logicalServer.getName() + " is in red");

			}
			/**
			 * Assert.assertTrue(k==0); No instruction to fail the test even
			 * though the load is greater than 90
			 */

		}
		Serenity.recordReportData().withTitle("List of servers with their respective load")
				.andContents("Servers with high Load" + highLoad.toString() + "\nServers with medium load"
						+ mediumLoad.toString() + "\nServers with low load" + lowLoad.toString() + "\n"
						+ lowLoadedServerCount + " servers are in green\n" + mediumLoadedServerCount
						+ " servers are in yellow\n" + highLoadedServerCount + " servers are in red");
	}

	@Step
	/** This method validates the type of the servers */
	public void verifyServers() {
		ResponseBody body = response.getBody();
		VPN vpn = body.as(VPN.class);
		ArrayList<String> plusServer = new ArrayList<String>();
		ArrayList<String> regularServer = new ArrayList<String>();
		ArrayList<String> onlineServer = new ArrayList<String>();
		ArrayList<String> offlineServer = new ArrayList<String>();
		List<LogicalServer> logicalServers = vpn.getLogicalServers();
		for (LogicalServer logicalServer : logicalServers) {
			// Tier validation
			if (logicalServer.getTier() >= 2) {
				LOGGER.info("Server " + logicalServer.getName() + " is a plus server");
				plusServer.add(logicalServer.getName());
				pluServerCount++;

			} else {
				LOGGER.info("Server " + logicalServer.getName() + " is a regular server");
				regularServer.add(logicalServer.getName());
				regularServerCount++;
			}

			/* Score validation- No adequate info to validate the score of a
			   server*/

			// Status validation
			if (logicalServer.getStatus().equals(1)) {
				LOGGER.info("Server " + logicalServer.getName() + " is online");
				onlineServer.add(logicalServer.getName());

				onlineServerCount++;
			} else {
				LOGGER.info("Server " + logicalServer.getName() + " is offline");
				offlineServer.add(logicalServer.getName());
				offlineServerCount++;
			}

		}
		Serenity.recordReportData().withTitle("Server details")
				.andContents(pluServerCount + " servers are plus servers\n" + regularServerCount
						+ " servers are regular servers\n" + onlineServerCount + " servers are online\n"
						+ offlineServerCount + " servers are offline" + "\nPlus servers are " + plusServer
						+ "\nRegular servers are " + regularServer + "\nOnline servers are " + onlineServer
						+ "\nOffline servers are" + offlineServer);
		/**
		 * Assert.assertTrue(d==0); No instruction to fail the test on the
		 * occurrence of offline servers
		 */
	}

	@Step
	/**
	 * This method validates the occurrence of at least one secure core server,
	 * one free server and one basic server for applications of VPN to function
	 * properly
	 */
	public void verifyFeatures() {
		ResponseBody<?> body = response.getBody();
		VPN vpn = body.as(VPN.class);
		ArrayList<String> basicServer = new ArrayList<String>();
		ArrayList<String> secureCoreServer = new ArrayList<String>();
		ArrayList<String> torServer = new ArrayList<String>();
		ArrayList<String> p2pServer = new ArrayList<String>();
		ArrayList<String> freeServer = new ArrayList<String>();

		List<LogicalServer> logicalServers = vpn.getLogicalServers();
		for (LogicalServer logicalServer : logicalServers) {

			// Features
			switch (logicalServer.getFeatures()) {
			case 0:
				LOGGER.info("Server " + logicalServer.getName() + " is a basic server");
				basicServer.add(logicalServer.getName());
				basicServerCount++;
				break;
			case 1:
				LOGGER.info("Server " + logicalServer.getName() + " is a secure core server");
				secureCoreServer.add(logicalServer.getName());
				secureCoreServerCount++;
				break;
			case 2:
				LOGGER.info("Server " + logicalServer.getName() + " is a tor server");
				torServer.add(logicalServer.getName());
				torServerCount++;
				break;
			case 4:
				LOGGER.info("Server " + logicalServer.getName() + " is a p2p server");
				p2pServer.add(logicalServer.getName());
				p2pServerCount++;
				break;
			default:
				LOGGER.info("Server " + logicalServer.getName() + " is not a valid server");
			}

			if (logicalServer.getDomain().contains("-free")) {
				LOGGER.info("Server " + logicalServer.getName() + " is a free server");
				freeServer.add(logicalServer.getName());
				freeServerCount++;
			}
		}
		Serenity.recordReportData().withTitle("Types of servers")
				.andContents(basicServerCount + " servers are basic servers\n" + secureCoreServerCount
						+ " servers are secure core servers\n" + torServerCount + " servers are tor servers\n"
						+ p2pServerCount + " servers are p2p servers\n" + freeServerCount
						+ " servers are free servers\n" + "Basic servers " + basicServer + "\nSecure core servers "
						+ secureCoreServer + "\nTor servers " + torServer + "\nP2P servers " + p2pServer
						+ "\nFree servers " + freeServer);
		Assert.assertTrue(basicServerCount >= 1);
		Assert.assertTrue(secureCoreServerCount >= 1);
		Assert.assertTrue(freeServerCount >= 1);
	}

}
