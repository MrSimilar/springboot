package com.xinyet.hbase.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("myHbaseService")
@Slf4j
public class SerDemo {

    @Autowired
    @Qualifier("myConnection")
    private Connection hbaseConnection;

    String tableName = "hbase";
    String familyName = "DECRYPT_MSG";


    /**
     * 在hbase创建一张表，只指定表名、列族名。其他配置均默认
     * 表名是tableName = "hbase";
     * 列族名字是familyName = "DECRYPT_MSG";
     *
     * @return
     */
    public boolean addTab() {
        try {
            // logger.info("获取hbaseAdmin对象用于操作表");
            Admin hbaseConnectionAdmin = hbaseConnection.getAdmin();

            //判断表是否存在
            TableName tableName1 = TableName.valueOf(this.tableName);
            boolean tableExists = hbaseConnectionAdmin.tableExists(tableName1);
            log.info("表是否存在" + tableName + "：" + tableExists);

            //如果表不存在就新建
            if (!tableExists) {
                //表不存在
                //表 描述建造者
                TableDescriptorBuilder builder = TableDescriptorBuilder.newBuilder(tableName1);
                //列族 描述建造者
                ColumnFamilyDescriptorBuilder cfdb = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(familyName));

                //设置最大版本号 其实就是记录的轨迹信息，设置为3可以查询追溯到前1个版本前2个版本前三个版本，此功能相当于数据库的flashback闪回。默认1
                cfdb.setMaxVersions(3);

                //创建列族 描述
                ColumnFamilyDescriptor familyDescriptor = cfdb.build();
                //将列族加入到表描述中
                builder.setColumnFamily(familyDescriptor);
                //创建表 描述
                TableDescriptor tableDescriptor = builder.build();

                //创建表
                hbaseConnectionAdmin.createTable(tableDescriptor);
                log.info("表创建成功" + tableName);

            } else {
                log.warn("表已经存在：" + tableName);
            }

            return hbaseConnectionAdmin.tableExists(tableName1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 向表"hello_ssx_tab";中的列族"family1";添加一条记录
     * String colName = "col_a";
     * String rowKey = "row_001";
     *
     * @param value
     * @return
     */
    public boolean setValue(String value, String colName, String rowKey) {
        //指定表名
        TableName tableName1 = TableName.valueOf(this.tableName);
        try {

            //获取表对象
            Table table = hbaseConnection.getTable(tableName1);
            //设置行键值
            Put row001 = new Put(Bytes.toBytes(rowKey));
            //设置列族，列，值
            row001.addColumn(Bytes.toBytes(familyName),
                    Bytes.toBytes(colName), Bytes.toBytes(value));
            //添加记录
            table.put(row001);
            // logger.info("成功hbase添加数据信息：目标表："+tableName+",目标行键："+rowKey+",目标列族："+familyName+",目标列："+ colName +",目标值："+value);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;

    }

    /**
     * 查询表中的记录 指定rowkey
     * String rowKey = "row_001";
     *
     * @param rowKey
     * @return
     */
    public String getValue(String rowKey) {
        //指定表名
        TableName tableName1 = TableName.valueOf(this.tableName);
        try {
            //设置表
            Table table = hbaseConnection.getTable(tableName1);
            //指定要查询的行键
            Get get = new Get(Bytes.toBytes(rowKey));
            //查询返回结果
            Result result = table.get(get);
            //获取结果集合
            Cell[] cells = result.rawCells();
            //遍历结果集
            StringBuilder returnData = new StringBuilder();
            for (Cell cell : cells) {
                byte[] bytes = CellUtil.cloneRow(cell);
                String s = Bytes.toString(bytes);
                log.info("查询hbase数据信息：目标表：" + tableName + ",目标行键：" + rowKey + ",查询出的信息： 行键：" + s);
                returnData.append(s).append('\n');
                String s1 = Bytes.toString(CellUtil.cloneFamily(cell));
                log.info("查询hbase数据信息：目标表：" + tableName + ",目标行键：" + rowKey + ",查询出的信息： 列族：" + s1);
                returnData.append(s1).append('\n');
                String s2 = Bytes.toString(CellUtil.cloneQualifier(cell));
                log.info("查询hbase数据信息：目标表：" + tableName + ",目标行键：" + rowKey + ",查询出的信息： 列名：" + s2);
                returnData.append(s2).append('\n');
                String s3 = Bytes.toString(CellUtil.cloneValue(cell));
                log.info("查询hbase数据信息：目标表：" + tableName + ",目标行键：" + rowKey + ",查询出的信息： 值：" + s3);
                returnData.append(s3).append('\n');
            }
            return returnData.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "查询失败";
    }

}
