package fr.kap35.databases.connections;

public interface IDatabaseConnection {

    public IDatabaseConnection getDatabaseConnection();

    public boolean addDatabaseConnection(String host, String port, String dbName, String username, String password, DatabaseType dbType, String connectionName);

    public ConnectionTools getConnection(String connectionName);

    public void initialize();

    public void stop();

    public IRunner getRunner();

}
