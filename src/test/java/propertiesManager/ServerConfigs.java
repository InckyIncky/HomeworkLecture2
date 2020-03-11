package propertiesManager;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"classpath:config.properties", "system:properties"})
public interface ServerConfigs extends Config {


    @Key("influx.host")
    String hostname();
    @Key("influx.user")
    String user();
    @Key("influx.pass")
    String password();
    @Key("influx.db")
    String dbname();
    @Key("influx.measurement")
    String measurement();


    @DefaultValue("browser")
    String browser();

}

