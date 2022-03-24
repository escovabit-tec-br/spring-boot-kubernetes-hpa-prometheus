package br.tec.escovabit.apphpa.configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;

@Configuration
public class MicrometerConfiguration {

    private static final String UNKNOW = "unknow";
    private static final List<String> NON_APPLICATION_ENDPOINTS = Arrays.asList("/swagger", "/**", "/v2/api-docs",
            "/webjars");
    private static final Logger LOGGER = Logger.getLogger(MicrometerConfiguration.class.getName());
    private static final String TAG_URI = "uri";

    @Bean
    public static MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(
            @Value("${spring.application.name}") String applicationName) {

        return registry -> registry.config()
                .commonTags("host", getHostName(), "instance", getHostName(), "ip", getHostAddress(), "application",
                        applicationName)
                .meterFilter(denyFrameworkURIsFilter());
    }

    private static MeterFilter denyFrameworkURIsFilter() {
        return MeterFilter.deny(id -> isNonApplicationEndpoint(id.getTag(TAG_URI)));
    }

    private static boolean isNonApplicationEndpoint(String uri) {
        return uri != null
                && NON_APPLICATION_ENDPOINTS.stream().map(uri::startsWith).filter(i -> i).findFirst().orElse(false);
    }

    private static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            return UNKNOW;
        }
    }

    private static String getHostAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            LOGGER.log(Level.INFO, e.getMessage(), e);
            return UNKNOW;
        }
    }
}