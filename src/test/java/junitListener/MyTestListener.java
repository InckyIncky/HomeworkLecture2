package junitListener;

import InfluxDB.InfluxResultWriter;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;

public class MyTestListener extends RunListener {

    private final InfluxResultWriter influxWriter;

    public MyTestListener(InfluxResultWriter influxWriter) {
        super();
        this.influxWriter = influxWriter;
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        influxWriter.writeStats(result);
    }
}
