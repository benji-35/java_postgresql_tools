package fr.kap35.databases.examples;

import fr.kap35.databases.annotations.Table;
import fr.kap35.databases.annotations.tableelements.*;

@Table(connectionName = "", tableName = "")
public class TableExample {

    @Key
    @PrimaryKey
    @AutoIncrement
    public int id;

}
