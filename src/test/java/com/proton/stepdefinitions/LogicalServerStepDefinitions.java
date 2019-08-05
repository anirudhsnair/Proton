package com.proton.stepdefinitions;

import com.proton.steps.LogicalServerSteps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import net.thucydides.core.annotations.Steps;

public class LogicalServerStepDefinitions {
	@Steps
	LogicalServerSteps logicalServer;

	@Given("^Retrieve API URL$")
	public void getUrl() {
		logicalServer.getApiUrl();
	}

	@When("^GET request is passed to the URL$")
	public void apiRequestGet() {
		logicalServer.getRequest();

	}

	@Then("^All required values should be available in the retrieved response$")
	public void verifyRetrievedResponse() {
		logicalServer.verifyResponse();

	}

	@Then("^The servers should work properly$")
	public void verifyWorkingOfServers() {
		logicalServer.verifyServers();
	}

	@Then("^The servers should have acceptable amount of load$")
	public void listServersWithHugeLoad() {
		logicalServer.listServersWithLoad();
	}

	@Then("^There should be atleast one secure core server, one basic server and one free server$")
	public void verifyServerFeatures() {
		logicalServer.verifyFeatures();
	}
}
