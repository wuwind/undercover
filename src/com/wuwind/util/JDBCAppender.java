package com.wuwind.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCAppender extends org.apache.log4j.jdbc.JDBCAppender {

    @Override
    protected void execute(String sql) throws SQLException {
//        System.out.println("sql1:"+sql);
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = this.getConnection();

            StringBuilder sb = new StringBuilder();
            sql = sql.substring(0, sql.length()-1);
            String[] split = sql.split(",");
            for (String s : split) {
                if(s.startsWith("'") || s.startsWith("\"")) {
                    sb.append("'");
                }
                sb.append(s.replaceAll("'","").replaceAll("\"",""));
                if(s.endsWith("'") || s.endsWith("\"")) {
                    sb.append("'");
                }
                sb.append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append(")");
            sql = sb.toString();
//            System.out.println("sql2 :"+sql);

            ps = connection.prepareStatement(sql);
            ps.executeUpdate();

        } finally {
            if (ps != null) {
                ps.close();
            }
            this.closeConnection(con);
        }
    }
}
