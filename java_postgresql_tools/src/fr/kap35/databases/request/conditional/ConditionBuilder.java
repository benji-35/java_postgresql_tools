package fr.kap35.databases.request.conditional;

public class ConditionBuilder implements IConditionBuilder {
    private StringBuilder builder;

    private void addCondition(String column, Conditional conditional, Object value, boolean isAnd) {
        if (!builder.isEmpty()) {
            if (isAnd) {
                builder.append(" AND ");
            } else {
                builder.append(" OR ");
            }
        }
        builder.append(column);
        if (value == null) {
            if (conditional == Conditional.NO) {
                builder.append(" IS NOT NULL");
            } else {
                builder.append(" IS NULL");
            }
            return;
        }
        switch (conditional) {
            case NO -> builder.append(" != ");
            case LOWER -> builder.append(" < ");
            case EQUALS -> builder.append(" = ");
            case HIGHER -> builder.append(" > ");
            case HIGHER_EQUALS -> builder.append(" >= ");
            case LOWER_EQUALS -> builder.append(" <= ");
        }
        switch (value.getClass().getName()) {
            case "String" -> {
                builder.append("'");
                builder.append(value.toString().replace("'", "''"));
                builder.append("'");
            }
            case "boolean" -> {
                boolean b_val = (boolean) value;
                if (b_val)
                    builder.append("true");
                else
                    builder.append("false");
            }
            case "float" -> builder.append((float) value);
            case "int" -> builder.append((int) value);
            case "Integer" -> {
                Integer i_val = (Integer) value;
                builder.append(i_val);
            }
            default -> {
            }
        }
    }

    public ConditionBuilder(String column, Conditional conditional, Object value) {
        addCondition(column, conditional, value, false);
    }

    @Override
    public IConditionBuilder and(String column, Conditional conditional, Object value) {
        addCondition(column, conditional, value, true);
        return this;
    }

    @Override
    public IConditionBuilder and(IConditionBuilder builder) {
        this.builder.append("AND (");
        this.builder.append(builder.getConditionQuery());
        this.builder.append(")");
        return this;
    }

    @Override
    public IConditionBuilder or(String column, Conditional conditional, Object value) {
        addCondition(column, conditional, value, false);
        return this;
    }

    @Override
    public IConditionBuilder or(IConditionBuilder builder) {
        this.builder.append("OR (");
        this.builder.append(builder.getConditionQuery());
        this.builder.append(")");
        return this;
    }

    @Override
    final public String getConditionQuery() {
        return builder.toString();
    }
}
