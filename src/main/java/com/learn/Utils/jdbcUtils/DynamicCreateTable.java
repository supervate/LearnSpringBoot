package com.learn.Utils.jdbcUtils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;
import java.util.*;

@Slf4j
public class DynamicCreateTable {

    public static void main(String[] args) throws SQLException {
        DriverManagerDataSource dataSource = getDatasource();
        List<Map> rows = new ArrayList<>();
        for (int i=0;i < 1000; i++) {
            Map column = new HashMap();
            column.put("测试1", "a");
            column.put("测试啊啊啊", "操");
            rows.add(column);
        }
        AutoCreateTable(dataSource.getConnection(),"测试表",rows.get(0));
        StoreData(dataSource,"测试表" ,rows);
    }

    public static DriverManagerDataSource getDatasource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");// 数据库驱动
        dataSource.setUrl("jdbc:mysql://192.168.31.221:3306/inputdata?characterEncoding=utf8");// 数据库连接和编码方式
        dataSource.setUsername("root");// 用户名
        dataSource.setPassword("123456");// 密码
        return dataSource;
    }

    public static void StoreData(DriverManagerDataSource dataSource, String tableName, List<Map> rowdatas)  {
        // 模板类对象
        JdbcTemplate template = new JdbcTemplate(dataSource);
        int rowCount = rowdatas.size();
        String[] sqls = new String[rowCount];
        for (int i = 0; i < rowCount; i++) {
            String id = get16UUID();
            Map rowdata = rowdatas.get(i);
            String sql = "INSERT INTO " + tableName + "(id,";
            for (Object o : rowdata.keySet()) {
                sql += (String)o + ",";
            }
            //切掉最后一个 ','
            sql = sql.substring(0, sql.length()-1);
            sql += ") VALUES('" + id + "',";
            for (Object o : rowdata.keySet()) {
                sql += "'" + rowdata.get(o) + "',";
            }
            //切掉最后一个 ','
            sql = sql.substring(0, sql.length()-1);
            sql += ")";
            sqls[i] = sql;
        }
        template.batchUpdate(sqls);
    }

    /**
     * 判断该表是否存在
     * @param tableName
     * @param conn
     * @return
     */
    public static boolean validateTableExist(String tableName, Connection conn){
        boolean flag = false;
        try {
            DatabaseMetaData meta = conn.getMetaData();
            String type [] = {"TABLE"};
            ResultSet rs = meta.getTables(null, null, tableName, type);
            flag = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 动态创建表
     * @param conn
     * @param tableName
     * @param columnMap
     * @return
     */
    public static boolean AutoCreateTable(Connection conn, String tableName, Map columnMap){
        if (columnMap.isEmpty()){
            log.error("未提供任何表字段的信息,表创建失败！");
        }
        try {
            Statement stmt = conn.createStatement();
            //如果表不存在,则动态创建一张表
            if (!validateTableExist(tableName,conn)) {
                String sql = "CREATE TABLE "+tableName+" (id VARCHAR(32)";

                for (Object o : columnMap.keySet()) {
                    sql += ", " + (String)o + " VARCHAR(255)";
                }
                sql += ", PRIMARY KEY (id))";
                stmt.execute(sql);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static String get16UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2];
    }
}
