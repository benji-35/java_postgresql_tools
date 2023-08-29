package fr.kap35.databases.examples;

import fr.kap35.databases.connections.DatabaseConnection;
import fr.kap35.databases.connections.DatabaseType;
import fr.kap35.databases.request.ARequest;
import fr.kap35.databases.request.conditional.ConditionBuilder;
import fr.kap35.databases.request.conditional.Conditional;
import fr.kap35.databases.request.implemented.SqlRequest;
import fr.kap35.databases.request.method.Method;
import fr.kap35.databases.request.utils.ResultCallback;

public class RequestExample {

    DatabaseConnection connection = new DatabaseConnection();

    public void test() {
        connection.initialize();
        connection.addDatabaseConnection("localhost","8080", "db_name", "username", "password", DatabaseType.POSTGRESQL, "my_first_connection");

        SqlRequest create_table = (SqlRequest) new SqlRequest()
                .setMethod(Method.CREATE)
                .setConnectionName("my_first_connection")
                .setTable("user")
                .addModification("id", 1)
                .addModification("f_name", "Benjamin")
                .addModification("l_name", "Delvert")
                .addModification("email", null);

        ConditionBuilder condition = new ConditionBuilder("id", Conditional.EQUALS, 1);
        ResultCallback callback = (result) -> {
            if (result == null)
                return;
            if (result.isFailed()) {
                System.out.println("Result failed");
            } else {
                System.out.println("Result worked");
            }
        };
        SqlRequest edit_same = (SqlRequest) new SqlRequest()
                .setMethod(Method.EDIT)
                .setCallback(callback)
                .setConnectionName("my_first_connection")
                .setTable("user")
                .addModification("email", "test@test.com")
                .setCondition(condition);

        ConditionBuilder condition2 = (ConditionBuilder) new ConditionBuilder("id", Conditional.EQUALS, 1);
        ConditionBuilder condition3 = (ConditionBuilder) new ConditionBuilder("email", Conditional.EQUALS, null)
                .or("email", Conditional.EQUALS, "test@test.com");
        condition2 = (ConditionBuilder) condition2.and(condition3);
        // condition = WHERE id = 1 AND (email IS NULL OR email = 'test@test.com')
        SqlRequest delete_same = (SqlRequest) new SqlRequest()
                .setMethod(Method.DELETE)
                .setConnectionName("my_first_connection")
                .setTable("user")
                .setCondition(condition2);
        try {
            connection.getRunner().addToQueue(create_table);
            connection.getRunner().addToQueue(edit_same);
            connection.getRunner().addToQueue(delete_same);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
