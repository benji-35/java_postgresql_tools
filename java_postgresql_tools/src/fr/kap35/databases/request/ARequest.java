package fr.kap35.databases.request;

import fr.kap35.databases.request.conditional.IConditionBuilder;
import fr.kap35.databases.request.method.Method;
import fr.kap35.databases.request.utils.ResultCallback;

import java.util.List;

public abstract class ARequest implements IRequest {

    protected Method method;
    protected String tableName;
    private String connectionName;
    protected List<String> modifications;
    protected String condition;
    protected int limit = -1;
    protected int offset = -1;

    protected ResultCallback callback = null;

    @Override
    public IRequest setMethod(Method method) {
        this.method = method;
        return this;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public IRequest setTable(String tableName) {
        this.tableName = tableName;
        return this;
    }

    @Override
    public String getTable() {
        return tableName;
    }

    @Override
    public IRequest setConnectionName(String connectionName) {
        this.connectionName = connectionName;
        return this;
    }

    @Override
    public String getConnectionName() {
        return connectionName;
    }

    @Override
    public String getQuery() {
        return "";
    }

    @Override
    public IRequest addModification(String column, String value) {
        return this;
    }

    @Override
    public IRequest addModification(String column, int value) {
        return this;
    }

    @Override
    public IRequest addModification(String column, boolean value) {
        return this;
    }

    @Override
    public IRequest addModification(String column, float value) {
        return this;
    }

    @Override
    public IRequest addModification(String column, Object value) {
        StringBuilder builder = new StringBuilder();
        builder.append(column);
        builder.append(" = ");
        if (value == null) {
            builder.append("NULL");
        } else {
            if (value instanceof String s_val) {
                builder.append("'");
                builder.append(s_val.replace("'", "''"));
                builder.append("'");
            } else if (value instanceof Float) {
                builder.append((float) value);
            } else if (value instanceof Integer) {
                builder.append((int) value);
            }
        }
        modifications.add(builder.toString());
        return this;
    }

    @Override
    public IRequest addGetValue(String column) {
        return this;
    }

    @Override
    public IRequest setCondition(IConditionBuilder condition) {
        this.condition = condition.getConditionQuery();
        return this;
    }

    @Override
    public IRequest setLimit(int limit) {
        this.limit = limit;
        return this;
    }

    @Override
    public IRequest setOffset(int offset) {
        this.offset = offset;
        return this;
    }

    @Override
    public IRequest setCallback(ResultCallback callback) {
        this.callback = callback;
        return this;
    }

    @Override
    public ResultCallback getCallback() {
        return callback;
    }
}
