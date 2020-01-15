package junitListener;

import InfluxDB.InfluxClient;
import InfluxDB.InfluxConfig;
import InfluxDB.InfluxResultWriter;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

public class MyTestRunner extends BlockJUnit4ClassRunner {
    private static final String HOST = System.getProperty("influx.host", "http://localhost:8086");
    private static final String USER = System.getProperty("influx.user", "root");
    private static final String PASS = System.getProperty("influx.pass", "root");
    private static final String DB = System.getProperty("influx.db", "OtusPageTest");
    private static final String MEASUREMENT = System.getProperty("influx.measurement", "UITests");
    private static final String envTag = System.getProperty("influx.envtag", "env3");

    private final MyTestListener listener;

    public MyTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
        InfluxConfig config = new InfluxConfig(HOST, USER, PASS, DB, MEASUREMENT, envTag);
        InfluxClient client = new InfluxClient(config);
        InfluxResultWriter writer = new InfluxResultWriter(client, config);
        this.listener = new MyTestListener(writer);
    }

    @Override
    public void run(RunNotifier notifier) {
        notifier.addListener(listener);
        notifier.fireTestRunStarted(Description.TEST_MECHANISM);
        super.run(notifier);
    }
}
