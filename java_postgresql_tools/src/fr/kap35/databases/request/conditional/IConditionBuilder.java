package fr.kap35.databases.request.conditional;

public interface IConditionBuilder {

    public IConditionBuilder and(String column, Conditional conditional, Object value);
    public IConditionBuilder and(IConditionBuilder builder);
    public IConditionBuilder or(String column, Conditional conditional, Object value);
    public IConditionBuilder or(IConditionBuilder builder);
    public String getConditionQuery();

}
