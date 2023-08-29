package fr.kap35.databases.annotations;

import fr.kap35.databases.annotations.tableelements.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Table {

    public String connectionName();

    public String tableName();

}
