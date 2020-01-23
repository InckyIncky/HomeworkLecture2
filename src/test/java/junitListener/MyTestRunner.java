package junitListener;

import InfluxDB.InfluxClient;
import InfluxDB.InfluxConfig;
import InfluxDB.InfluxResultWriter;
import org.aeonbits.owner.ConfigFactory;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import propertiesManager.ServerConfigs;

public class MyTestRunner extends BlockJUnit4ClassRunner {
    private static final ServerConfigs cfg = ConfigFactory.create(ServerConfigs.class);
    private final MyTestListener listener;

    public MyTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
        InfluxConfig config = new InfluxConfig(cfg);
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
