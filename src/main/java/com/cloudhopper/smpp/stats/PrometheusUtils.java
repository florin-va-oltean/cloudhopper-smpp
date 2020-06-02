package com.cloudhopper.smpp.stats;

import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.dropwizard.samplebuilder.CustomMappingSampleBuilder;
import io.prometheus.client.dropwizard.samplebuilder.MapperConfig;
import io.prometheus.client.dropwizard.samplebuilder.SampleBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * contains utilities for prometheus integration
 */
public class PrometheusUtils {
    /**
     * this translates from dropwizard dot notation to prometheus label multi-dimensional model
     * example: from smpp.session.test.3.4.TRANSCEIVER.rx.nbSessionsEver to nbSessionEver{systemid=test,version=3.4,type=TRANSCEIVER,direction=rx}
     * @return {@link io.prometheus.client.dropwizard.samplebuilder.CustomMappingSampleBuilder}
     */
    public static SampleBuilder smppSessionCountersBuilder(){
        MapperConfig config = new MapperConfig();
        config.setMatch("smpp.sessions.*.*.*.*.*.*");
        config.setName("smpp_sessions_${5}");
        Map<String,String> labels = new HashMap<>();
        labels.put("systemid","${0}");
        labels.put("version","${1}.${2}");
        labels.put("type","${3}");
        labels.put("direction","${4}");
        config.setLabels(labels);
        return new CustomMappingSampleBuilder(Arrays.asList(config));
    }
}
