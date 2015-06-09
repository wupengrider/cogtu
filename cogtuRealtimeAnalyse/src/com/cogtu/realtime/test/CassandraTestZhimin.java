package com.cogtu.realtime.test;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class CassandraTestZhimin {

    public static Cluster cluster;

    public static Session session;

    public void connect(String node) {
        cluster = Cluster.builder().addContactPoint(node).build();
        Metadata metadata = cluster.getMetadata();
        this.session = cluster.connect();
    }

    public void close() {
        cluster.close();
    }

    public static void main(String[] args) {
        CassandraTestZhimin client = new CassandraTestZhimin();
        // client.connect("10.51.104.174");
        client.connect("172.16.60.1");

        ResultSet resultSet = CassandraTestZhimin.session.execute("SELECT * FROM cogtu.imp_info"); //  WHERE  age='1' AND date = '20150101' ");
        for (Row row : resultSet) {
            int age = row.getInt("age");
            System.out.println("age is " + age);
        }

        PreparedStatement insertStatement = CassandraTestZhimin.session.prepare("UPDATE cogtu.cost SET cost = cost + 1000 WHERE adid='1' AND date='20150101';");
        BoundStatement boundStatement = new BoundStatement(insertStatement);

        CassandraTestZhimin.session.execute(boundStatement);
    }
}
