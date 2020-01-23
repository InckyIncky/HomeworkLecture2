package InfluxDB;

import junit.framework.AssertionFailedError;
import org.influxdb.dto.Point;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class InfluxResultWriter {

    private final static Predicate<Failure> ASSERTION_FAILED =
            f -> f.getException().getClass().equals(AssertionFailedError.class);
    private final static Predicate<Failure> TEST_ERROR =
            f -> !f.getException().getClass().equals(AssertionFailedError.class);

    private final InfluxClient client;
    private final InfluxConfig config;
    private long timestamp;

    public InfluxResultWriter(InfluxClient client, InfluxConfig config) {
        this.client = client;
        this.config = config;
        client.createDbIfNeeded(config.getDbName());

    }

    public void writeStats(Result result) {
        timestamp = System.currentTimeMillis();
        writeTotalTestsCount(result.getRunCount());
        writeSuccessfulTestsCount(result.getRunCount() - result.getFailureCount());
        writeFailedTestsCount(result.getFailureCount());
        writeAssertionErrors(result.getFailures());
        writeTestError(result.getFailures());
    }

    private void writeTotalTestsCount(int total) {
        System.out.println("Total: " + total);
        Point p = getPoint("total", total);
        client.writePoint(p);
    }

    private void writeSuccessfulTestsCount(int success) {
        System.out.println("Succseessful tests: " + success);
        Point p = getPoint("succeeded", success);
        client.writePoint(p);
    }

    private void writeFailedTestsCount(int fails) {
        System.out.println("Failed tests: " + fails);
        Point p = getPoint("failed", fails);
        client.writePoint(p);
    }

    private void writeAssertionErrors(List<Failure> failures) {
        int assertionFailedCount = (int) failures.stream().filter(ASSERTION_FAILED).count();
        System.out.println("Assertion failed Tests: " + assertionFailedCount);
        Point p = getPoint("Assertion errors", assertionFailedCount);
        client.writePoint(p);
    }

    private void writeTestError(List<Failure> failures) {
        int testErrorCount = (int) failures.stream().filter(TEST_ERROR).count();
        System.out.println("Test with errors: " + testErrorCount);
        Point p = getPoint("testError", testErrorCount);
        client.writePoint(p);
    }


    private Point getPoint(String field, int value) {
        return Point
                .measurement(config.getMeasurement())
                .addField(field, value)
                .time(timestamp, TimeUnit.MILLISECONDS)
                .build();
    }
}
