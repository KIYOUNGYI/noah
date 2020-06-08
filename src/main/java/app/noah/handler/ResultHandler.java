package app.noah.handler;

import org.springframework.http.ResponseEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class ResultHandler {

    public static ResponseEntity<Object> handle(Object body, Connection conn) throws SQLException {

        if (conn != null && !conn.isClosed()) {
            conn.close();
        }

        System.out.println("Connection is closed_" + conn.isClosed());

        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<?> handle(Object body) {

        if (body == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(body);

    }

    public static ResponseEntity<?> handle(Connection conn) throws SQLException {

        if (conn != null && !conn.isClosed()) {
            conn.close();
        }

        System.out.println("Connection is closed_" + conn.isClosed());

        return ResponseEntity.ok().build();
    }

}
