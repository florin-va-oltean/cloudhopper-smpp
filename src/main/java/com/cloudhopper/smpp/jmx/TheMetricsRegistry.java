package com.cloudhopper.smpp.jmx;

import com.codahale.metrics.MetricRegistry;

/**
 * an enum holding the registry for the application; we use the pattern enum for singleton
 * everyone that wants to create a metric must use this enum.
 */
public enum TheMetricsRegistry {
    INSTANCE;
    private static MetricRegistry registry = new MetricRegistry();
    public MetricRegistry metrics(){
        return registry;
    }
}
