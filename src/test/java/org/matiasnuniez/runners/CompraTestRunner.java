package org.matiasnuniez.runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(
        key = Constants.FEATURES_PROPERTY_NAME,
        value = "classpath:features/purchase"
)
@ConfigurationParameter(
        key = Constants.PLUGIN_PROPERTY_NAME,
        value = "net.serenitybdd.cucumber.core.plugin.SerenityReporterParallel"
)
@ConfigurationParameter(
        key = Constants.GLUE_PROPERTY_NAME,
        value = "org.matiasnuniez"
)
public class CompraTestRunner {
}
