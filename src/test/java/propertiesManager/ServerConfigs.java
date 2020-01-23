package propertiesManager;

import org.aeonbits.owner.Config;

public interface ServerConfigs extends Config {


    @Key("influx.host")
    @DefaultValue("http://localhost:8086")
    String hostname();
    @Key("influx.user")
    @DefaultValue("root")
    String user();
    @Key("influx.pass")
    @DefaultValue("root")
    String password();
    @Key("influx.db")
    @DefaultValue("OtusPageTest")
    String dbname();
    @Key("influx.measurement")
    @DefaultValue("measurement")
    String measurement();
}

