package InfluxDB;

import org.aeonbits.owner.ConfigFactory;
import propertiesManager.ServerConfigs;

public class InfluxConfig {

    ServerConfigs cfg;

    public InfluxConfig(ServerConfigs cfg) {
        this.cfg = ConfigFactory.create(ServerConfigs.class);;
    }

    public String getHost() {
        return cfg.hostname();
    }

    public String getUser() { return cfg.user(); }

    public String getPass() {
        return cfg.password();
    }

    public String getDbName() { return cfg.dbname(); }

    public String getMeasurement() {
        return cfg.measurement();
    }

    @Override
    public String toString() {
        return "InfluxConfig{" +
                "host='" + cfg.hostname() + '\'' +
                ", user='" + cfg.user() + '\'' +
                ", pass='" + cfg.password() + '\'' +
                ", dbName='" + cfg.dbname() + '\'' +
                ", measurement='" + cfg.measurement() + '\'' +
                '}';
    }
}
