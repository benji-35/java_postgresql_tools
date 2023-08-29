package fr.kap35.databases.connections;

import fr.kap35.databases.request.IRequest;
import fr.kap35.databases.request.IResult;
import fr.kap35.databases.request.Result;

import java.sql.*;

public class ConnectionTools {

    private String username = null;
    private String url = null;
    private String password = null;
    private DatabaseType type = DatabaseType.UNDEFINED;

    public ConnectionTools(String host, String port, String db_name, String username, String password, DatabaseType dbType) {
        switch (dbType) {
            case POSTGRESQL -> postgresqlConnection(host, port, db_name, username, password);
            case MYSQL -> mysqlConnection(host, port, db_name, username, password);
            default -> {}
        }
    }

    private void postgresqlConnection(String host, String port, String db_name, String username, String password) {
        url = "jdbc:postgresql://" + host + ":" + port + "/" + db_name;
        this.username = username;
        this.password = password;
        this.type = DatabaseType.POSTGRESQL;
    }

    private void mysqlConnection(String host, String port, String db_name, String username, String password) {
        url = "jdbc:mysql://" + host + ":" + port + "/" + db_name;
        this.username = username;
        this.password = password;
        this.type = DatabaseType.POSTGRESQL;
    }

    public Connection getSqlConnection() {
        try {
            switch (type) {
                case MYSQL -> Class.forName("com.mysql.cj.jdbc.Driver");
                case POSTGRESQL -> Class.forName("com.postgresql.cj.jdbc.Driver");
                default -> {
                    return null;
                }
            }
            return DriverManager.getConnection(url,username,password);
        } catch(SQLException | ClassNotFoundException e) {
            System.out.println("Error while trying to connect");
        }
        return null;
    }

    public DatabaseType getType() {
        return type;
    }

    public IResult executeQuery(String query, boolean needResult) {
        Result result = new Result();
        result.setFailed(true);
        if (query == null)
            return result;
        switch (this.type) {
            case POSTGRESQL, MYSQL -> {
                try {
                    Connection con = getSqlConnection();
                    if (con == null) {
                        throw new Exception();
                    }
                    Statement st = con.createStatement();
                    ResultSet result_st = st.executeQuery(query);
                    con.close();
                    result.setFailed(false);
                    ResultSetMetaData md = result_st.getMetaData();
                    for (int i = 0; i < md.getColumnCount(); i++)
                        result.addValue(md.getColumnLabel(i), result_st.getObject(i));
                } catch(Exception e) {
                    return result;
                }
            }
            case MONGODB -> {}
            default -> {}
        }
        return result;
    }

    public IResult executeQuery(String query) {
        return executeQuery(query, true);
    }
}
