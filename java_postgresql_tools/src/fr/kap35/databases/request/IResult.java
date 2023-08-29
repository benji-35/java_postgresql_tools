package fr.kap35.databases.request;

import java.util.List;

public interface IResult {

    public int getInt(String column_name);
    public float getFloat(String column_name);
    public boolean getBoolean(String column_name);
    public String getString(String column_name);
    public Object getObject(String column_name);
    public List<String> getColumns();
    public boolean isFailed();

}
