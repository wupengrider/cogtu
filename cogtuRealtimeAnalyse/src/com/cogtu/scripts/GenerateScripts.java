package com.cogtu.scripts;

import java.util.ArrayList;

public class GenerateScripts {

    public static void main(String[] args) throws ClassNotFoundException {
        // String scp =
        // "scp -r /root/DataStaxEnterprise-4.6.5.2015040907-linux-x64-installer.run root@tihuan:/root/";

        // String scp =
        // "scp -r /root/dsc-cassandra-2.1.4/conf root@tihuan:/root/dsc-cassandra-2.1.4";

        // String scp =
        // "scp -r /root/datastax-agent-5.1.1/conf root@tihuan:/root/datastax-agent-5.1.1/";

        // String scp =
        // "scp -r /root/cdh5.3.0/spark-1.2.0-cdh5.3.2.tar.gz root@tihuan:/root/cdh5.3.0/";

        // String scp = "scp -r /etc/hosts/ root@tihuan:/etc/";

        // String scp = "scp -r /root/mylib/ root@tihuan:/root/";

        // String scp = "scp -r /root/cdh5.3.0/hadoop-2.5.0-cdh5.3.2/etc/hadoop/ root@tihuan:/root/cdh5.3.0/hadoop-2.5.0-cdh5.3.2/etc/";
        // String scp = "scp -r /root/cdh5.3.0/hadoop-2.5.0-cdh5.3.2/etc/hadoop/yarn-site.xml root@tihuan:/root/cdh5.3.0/hadoop-2.5.0-cdh5.3.2/etc/";
        // String scp = "scp -r /root/testWorkspaces root@tihuan:/root";
        String scp = "scp -r /root/testWorkspaces/wupeng/cogtuTest/cogtuRealtimeAnalyse.jar root@tihuan:/root/testWorkspaces/wupeng/cogtuTest";

        // String scp =
        // "scp -r spark-1.2.0-bin-hadoop2.4 root@tihuan:/root/cdh5.3.0/";

        // String scp =
        // "scp -r /root/cdh5.3.0/spark-1.2.0-bin-hadoop2.4/conf/ root@tihuan:/root/cdh5.3.0/spark-1.2.0-bin-hadoop2.4/";

        // String scp =
        // "scp -r /root/cdh5.3.0/spark-1.2.0-bin-hadoop2.4/conf/ root@tihuan:/root/cdh5.3.0/spark-1.2.0-bin-hadoop2.4/";

        // String scp =
        // "scp -r /root/cdh5.3.0/hadoop-2.5.0-cdh5.3.2/share/hadoop/mapreduce root@tihuan:/root/cdh5.3.0/hadoop-2.5.0-cdh5.3.2/share/hadoop/";

        // String scp =
        // "scp -r /root/cdh5.3.0/spark-1.2.0-bin-hadoop2.4/lib root@tihuan:/root/cdh5.3.0/spark-1.2.0-bin-hadoop2.4/";

        // String scp ="scp -r /etc/profile root@tihuan:/etc/";

        // String scp ="scp -r /root/mylib root@tihuan:/root/";

        // String scp =
        // "scp -r /root/cdh5.3.0/hbase-0.98.6-cdh5.3.2/conf root@tihuan:/root/cdh5.3.0/hbase-0.98.6-cdh5.3.2/";

        // String scp =
        // "scp -r /root/cdh5.3.0/spark-1.2.0-bin-hadoop2.4/conf root@tihuan:/root/cdh5.3.0/spark-1.2.0-bin-hadoop2.4/";

        // String scp =
        // "scp -r /root/cdh5.3.0/spark-1.2.0-bin-hadoop2.4/conf root@tihuan:/root/cdh5.3.0/spark-1.2.0-bin-hadoop2.4";
        // String scp =
        // "scp -r /root/apache-cassandra-2.1.3/conf root@tihuan:/root/apache-cassandra-2.1.3/";

        ArrayList<String> name = new ArrayList<String>();

        name.add("iZ25kyuwbquZ");
        name.add("iZ2587zrv74Z");
        name.add("iZ25nlih1cnZ");
        name.add("iZ25ov1lt6yZ");

        name.add("iZ25hc55c9lZ");
        name.add("iZ25e12rel0Z");
        name.add("iZ25220fqoqZ");
        name.add("iZ25yygtbj3Z");

        // name.add("iZ25n47niewZ");
        // name.add("iZ25pq2uwhbZ");
        // name.add("iZ25czv7sidZ");
        // name.add("iZ25yki9xftZ");
        // name.add("iZ25t6xh6cmZ");
        //
        // name.add("iZ25vrm74whZ");
        // name.add("iZ25l4a89hhZ");
        // name.add("iZ253m2018gZ");
        // name.add("iZ25scw7q62Z");
        //
        // // name.add("iZ25pwnpcgrZ");
        // // name.add("iZ25rbvqab1Z");
        // // name.add("iZ25o99kfutZ");
        // // name.add("iZ25h35nwzxZ");

        for (int i = 0; i < name.size(); i++) {
            System.out.println(scp.replace("tihuan", name.get(i)));
        }

    }
}
