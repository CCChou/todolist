package com.rh.health;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import jakarta.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class DataCheck implements HealthCheck {

    @ConfigProperty(name = "data.ready", defaultValue = "false")
    private boolean dataReady;
    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Data ready check");
        try {
            simulateDataVerification();
            responseBuilder.up();
        } catch (IllegalStateException e) {
            responseBuilder.down();
        }
        return responseBuilder.build();
    }

    private void simulateDataVerification() {
        if (!dataReady) {
            throw new IllegalStateException("Cannot read data");
        }
    }
}
