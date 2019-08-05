package com.proton.utils;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;

public class ConfigurationUtils {
	public static String getApiBaseUrl() {
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();

		return variables.getProperty("env.var.api.baseUrl");

	}

}
