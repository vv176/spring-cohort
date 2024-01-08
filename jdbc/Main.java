package exceptionhandling.classroom.jdbc;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.*;

public class Main {
    // certain format : jdbc:mysql://localhost:3306/vivek
    // "jdbc:postgresql://localhost:5432/postgres",
    //                "postgres", ""


    public static void main(String[] args) throws SQLException {
        //batch("jdbc:postgresql://localhost:5432/postgres",
          //      "postgres", "", "products");
        pool();
    }

    public static void pool() throws SQLException {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:postgresql://localhost:5432/postgres");
        p.setDriverClassName("org.postgresql.Driver");// "org.postgresql.Driver"
        p.setUsername("postgres");
        p.setPassword("");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
                "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        DataSource dataSource = new DataSource();
        dataSource.setPoolProperties(p);
        Connection conn = null;
        long s = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            try {
                conn = dataSource.getConnection();
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("select * from products");
                rs.close();
                stm.close();
            } finally {
                if (conn != null)
                    conn.close();
            }
        }
        long e = System.currentTimeMillis();
        System.out.println((e-s));
    }

    public static void func(String url, String user,
                            String pwd) throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, pwd);
        Statement s1 = conn
                .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
        // statement <==assoc==> 1 res set only
        ResultSet rs1 =
                s1.executeQuery("select * from products where name='Kindle'");
        while (rs1.next()) {
            System.out.println(rs1.getString("id"));
        }

        while (rs1.previous()) {
            System.out.println(rs1.getString("id"));
        }

    }

    public static void infinite() throws SQLException {
        while(true) {
            Connection connection = null;
            try {
                 connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres", "");
                Statement statement = connection.createStatement();
                statement.executeQuery("select * from products");
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
    }

    public static void connectToDB(String url, String user,
                                   String pwd, String table) throws SQLException {
        Connection conn = DriverManager.getConnection(url,
                user, pwd);
        Statement statement = conn.createStatement();
        //ResultSet rs = statement.executeQuery("select * from " + table);
        /**while (rs.next()) {
            System.out.println(rs.getString("description"));
        }**/
        int cnt = statement.executeUpdate("update " + table +
                " set full_version = '100.0' where name='Kindle'");
        System.out.println(cnt);
    }

    public static void update(String url, String user,
                             String pwd, String table) throws SQLException {
        Connection conn = DriverManager.getConnection(url,
                user, pwd);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        conn.setAutoCommit(false);
        statement.executeUpdate("update products set id = 1 where id = 1101");
        conn.commit();
    }
    // batchAPI(List<String> queries)
    public static void batch(String url, String user,
                             String pwd, String table) throws SQLException {
        Connection conn = DriverManager.getConnection(url,
                user, pwd);// connection pool
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        boolean r = statement.execute("select * from products where id < 3000;" +
                "update products set name = 'JBL+++' where name = 'JBL++'");
        if (r) {
            ResultSet rs = statement.getResultSet();
            while(rs.next()) {
                System.out.println(rs.getInt("id"));
            }
        } else {
            int c = statement.getUpdateCount();
            System.out.println(c + " rows impacted");
        }
        boolean k = statement.getMoreResults();
        if (k) {
            ResultSet rs = statement.getResultSet();
            while(rs.next()) {
                System.out.println(rs.getInt("id"));
            }
        } else {
            int c = statement.getUpdateCount();
            System.out.println(c + " rows impacted");
        }
    }
    public static void func1(String url, String user,
                            String pwd, String table) throws SQLException {
        Connection conn = DriverManager.getConnection(url,
                user, pwd);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs =
         statement.executeQuery("select * from " + table);
        while (rs.next()) {
            if (rs.getString("name").equals("Apple Watch++")) {
                // insert;
                rs.moveToInsertRow();
                rs.updateInt("id",rs.getInt("id")*20);
                rs.updateString("name", "Apple Watch+++");
                rs.insertRow();
                rs.moveToCurrentRow();
            }
        } // update our result set on the go.
    }

}
// products(id,....) feedback(pid,.....) commit
// exec....
// exec....
// conn.commit()
// transaction
//  App ---Query--->  (r1r2r3r4r5r6r7r8....)DB(started executing, creating results)
// physical resources are init at DB side
// data : hard drive
// object : RAM
//
// cursor: R1: Hashmap id:1 | name:Kindle | desc:reader  | ver:1.190
// R2
// R3
// diff dialects
// JDBC, wont have to learn SQL??
// products(id,....) | feedbacks(pid, uid, review)
// 1, Apple      1, good
// 2 Kindle      1, costly
// 3 iMac        2, ....
//R1
//R2
//R3
//R4
