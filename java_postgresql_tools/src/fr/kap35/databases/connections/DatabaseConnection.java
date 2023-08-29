package fr.kap35.databases.connections;

import java.util.HashMap;
import java.util.Map;
//connections


public class DatabaseConnection implements IDatabaseConnection {

    Map<String, ConnectionTools> connections = new HashMap<>();
    DatabaseRunner thread = null;

    @Override
    final public IDatabaseConnection getDatabaseConnection() {
        return this;
    }

    @Override
    public boolean addDatabaseConnection(String host, String port, String dbName, String username, String password, DatabaseType dbType, String connectionName) {
        if (connections.containsKey(connectionName)) {
            return false;
        }
        try {
            connections.put(connectionName, new ConnectionTools(host, port, dbName, username, password, dbType));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public ConnectionTools getConnection(String connectionName) {
        if (!connections.containsKey(connectionName)) {
            return null;
        }
        try {
            return connections.get(connectionName);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void initialize() {
        //initialize tables
        thread = new DatabaseRunner(this);
        thread.runnerStart();
    }

    @Override
    public void stop() {
        //stop threads
        thread.runnerStop();
    }

    @Override
    public IRunner getRunner() {
        return thread;
    }

}
