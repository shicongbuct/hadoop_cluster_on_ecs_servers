package com.xinde;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;


public class HadoopOpr {
    static String ClusterName = "ns1";
    private static final String HADOOP_URL = "hdfs://"+ClusterName;
    public static Configuration conf;


    static {
        conf = new Configuration(false);
        conf.set("fs.defaultFS", "hdfs://ns1");
        conf.set("dfs.nameservices", "ns1");
        conf.set("dfs.ha.namenodes.ns1", "nn1,nn2");

        conf.set("dfs.client.use.datanode.hostname", "true");
        conf.set("dfs.namenode.rpc-address.ns1.nn1", "node1:8020");
        conf.set("dfs.namenode.rpc-address.ns1.nn2", "node2:8020");


        conf.set("dfs.client.failover.proxy.provider.ns1",
                            "org.apache.hadoop.hdfs.server.namenode.ha.ConfiguredFailoverProxyProvider");
        conf.set("fs.hdfs.impl", "org.apache.hadoop.hdfs.DistributedFileSystem");
    }


    public static void uploadFile() {
        String localSrc = "d:\\temp\\wtf.epub";
        String dest = "/upload/wtf.epub";

        try {
            FileSystem fileSystem = FileSystem.get(URI.create("hdfs://ns1"), conf, "root");
            FSDataOutputStream out = fileSystem.create(new Path(dest), new Progressable() {
                @Override
                public void progress() {
                    System.out.print(".");
                }
            });
            InputStream in = new BufferedInputStream((new FileInputStream(localSrc)));

            IOUtils.copyBytes(in, out, 4096, true);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
