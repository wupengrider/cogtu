package com.cogtu.realtime.tools;

import java.sql.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lenovo on 2015/6/4.
 */
public class MysqlTool {

    // connect to MySQL
    public static Connection getConnection() {
        String url = "jdbc:mysql://172.16.1.5:3306/appserver?characterEncoding=UTF-8";
        String username = "root";
        String password = "yzmtest"; // 加载驱动程序以连接数据库
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        }
        //捕获加载驱动程序异常
        catch (ClassNotFoundException cnfex) {
            cnfex.printStackTrace();
        }
        //捕获连接数据库异常
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
        return conn;
    }

    // disconnect to MySQL
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // execute selection language
    public ResultSet selectSQL(String sql, Connection conn) {
        ResultSet rs = null;
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void main(String args[]) {
        MysqlTool tool = new MysqlTool();
        Connection conn = tool.getConnection();
        String s = "select * from T_LA_LOGREPORT";

        try {
            String insert = "insert into T_LA_LOGREPORT(ID,TIME_INTERVAL,TIMESTAMP,FILL_RATE,RENDER_RATE,CLICK_RATE,CRAWLER_ERR_TIME,CRAWLER_SUCC_TIME) values(?,?,?,?,?,?,?,?)";
            PreparedStatement pstatement = conn.prepareStatement(insert);
            pstatement.setString(1, UUID.randomUUID().toString());
            pstatement.setString(2, "1");
            pstatement.setTimestamp(3, new Timestamp(new Date().getTime()));
            pstatement.setDouble(4, 0.1);
            pstatement.setDouble(5, 0.2);
            pstatement.setDouble(6, 0.3);
            pstatement.setInt(7, 4);
            pstatement.setInt(8, 5);
            pstatement.executeUpdate();

            ResultSet rs = tool.selectSQL(s, conn);
            while (rs.next()) {
                String id = rs.getString("ID");
                String timeInterval = rs.getString("TIME_INTERVAL");
                Timestamp ts = rs.getTimestamp("TIMESTAMP");
                String fillRate = rs.getString("FILL_RATE");
                String renderRate = rs.getString("RENDER_RATE");
                String clickRate = rs.getString("CLICK_RATE");
                int crawlerErrTime = rs.getInt("CRAWLER_ERR_TIME");
                int crawlerSuccTime = rs.getInt("CRAWLER_SUCC_TIME");

                System.out.println("id=" + id + ", timeInterval=" + timeInterval + ", fillRate=" + fillRate + ", renderRate=" + renderRate
                        + ", clickRate=" + clickRate + ", crawlerErrTime=" + crawlerErrTime + ", crawlerSuccTime=" + crawlerSuccTime + ", timestamp=" + ts);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
