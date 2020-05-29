package app.noah.config;

import org.hibernate.dialect.MySQL57Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class MySQLDialectCustom extends MySQL57Dialect
{
    public MySQLDialectCustom() {
        registerFunction(
                "group_concat",
                new StandardSQLFunction("group_concat", StandardBasicTypes.STRING)
        );

        registerFunction(
                "timestampadd",
                new StandardSQLFunction("timestampadd", StandardBasicTypes.DATE)
        );
    }
}
