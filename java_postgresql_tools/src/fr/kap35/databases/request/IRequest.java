package fr.kap35.databases.request;

import fr.kap35.databases.request.conditional.ConditionBuilder;
import fr.kap35.databases.request.conditional.Conditional;
import fr.kap35.databases.request.conditional.IConditionBuilder;
import fr.kap35.databases.request.method.Method;
import fr.kap35.databases.request.utils.ResultCallback;

public interface IRequest {

    //get method
    public IRequest setMethod(Method method);
    public Method getMethod();

    // get table
    public IRequest setTable(String tableName);
    public String getTable();

    // get connection name
    public IRequest setConnectionName(String connectionName);
    public String getConnectionName();

    //get final query
    public String getQuery();

    // modifications
    public IRequest addModification(String column, String value);
    public IRequest addModification(String column, int value);
    public IRequest addModification(String column, boolean value);
    public IRequest addModification(String column, float value);
    public IRequest addModification(String column, Object value);
    public IRequest addGetValue(String column);
    public IRequest setCondition(IConditionBuilder condition);
    public IRequest setLimit(int limit);
    public IRequest setOffset(int offset);

    //callback
    public IRequest setCallback(ResultCallback callback);
    public ResultCallback getCallback();
}
