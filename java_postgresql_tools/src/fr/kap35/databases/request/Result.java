package fr.kap35.databases.request;

import java.util.List;
import java.util.Map;

public class Result implements IResult {
    private Map<String, Object> data;
    private boolean failed = false;

    public void addValue(String key, Object value) {
        data.put(key, value);
    }
    public void setFailed(boolean is_failed) {
        failed = is_failed;
    }


    @Override
    public int getInt(String column_name) throws RuntimeException {
        try {
            Object data = getObject(column_name);
            if (data == null)
                throw new Exception("No data found with key " + column_name);
            if (data instanceof Integer)
                return (Integer) data;
            throw new Exception("Bad type of object got");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public float getFloat(String column_name) {
        try {
            Object data = getObject(column_name);
            if (data == null)
                throw new Exception("No data found with key " + column_name);
            if (data instanceof Float)
                return (Float) data;
            throw new Exception("Bad type of object got");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean getBoolean(String column_name) {
        try {
            Object data = getObject(column_name);
            if (data == null)
                throw new Exception("No data found with key " + column_name);
            if (data instanceof Boolean)
                return (Boolean) data;
            throw new Exception("Bad type of object got");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String getString(String column_name) {
        try {
            Object data = getObject(column_name);
            if (data == null)
                throw new Exception("No data found with key " + column_name);
            if (data instanceof String)
                return (String) data;
            throw new Exception("Bad type of object got");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Object getObject(String column_name) {
        if (column_name == null)
            return null;
        return this.data.get(column_name);
    }

    @Override
    public List<String> getColumns() {
        return null;
    }

    @Override
    public boolean isFailed() {
        return failed;
    }
}
