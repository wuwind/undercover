package com.wuwind.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCAppender extends org.apache.log4j.jdbc.JDBCAppender {

    @Override
    protected void execute(String sql) throws SQLException {
        System.out.println(sql);
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = this.getConnection();

            sql = sql.replaceAll("''","'");
            ps = connection.prepareStatement(sql);

            ps.executeUpdate();
//            String regex = "values\\s*\\(((.*?\\s*.*?)(###(.*?\\s*.*?))*)\\)";
//
//            Pattern pattern = Pattern.compile(regex);
//            Matcher m = pattern.matcher(sql);
//
//            m.find(); //查询记录
//            String res = m.group(1);
//            StringBuffer sqlBuff = new StringBuffer();
//
//            sqlBuff.append(sql.substring(0, sql.indexOf("values"))).append("values(");
//
//            String[] data = res.split("###");
//            for (String s : data) {
//                if (!s.trim().endsWith("nextval"))
//                    sqlBuff.append(res.substring(0, res.indexOf(s))).append("?").append(",");
//                else sqlBuff.append(s).append(",");
//
//                res = res.substring(res.indexOf("###") + 3);
//            }
//            sql = sqlBuff.substring(0, sqlBuff.length() - 1);
//            sql += ")";
//
//            ps = connection.prepareStatement(sql);
//
//            ps.setString(1,data[0]);
////            int i = 0;
////            for (String s : data) {
////                System.out.println(s);
//////                if (!s.trim().endsWith("nextval"))
////                    ps.setString(++i, s.replaceAll("^'", "").replaceAll("'$", ""));
////            }
//            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            this.closeConnection(con);
        }
    }
}
