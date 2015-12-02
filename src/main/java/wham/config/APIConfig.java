package wham.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

@ApplicationPath("/api")
public class APIConfig extends ResourceConfig {

	public APIConfig() {
		packages("wham");
		register(RolesAllowedDynamicFeature.class);
	}
}
