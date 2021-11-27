package com.example.demo.dao;

import com.example.demo.pojo.Prose;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProseImpl implements ProseDao {

    @Override
    public List<Prose> queryProseList() {
        // 数据库链接
        Connection connection = null;
        // 预编译statement
        PreparedStatement preparedStatement = null;
        // 结果集
        ResultSet resultSet = null;
        // 商品列表
        List<Prose> list = new ArrayList<Prose>();

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/search_engine", "root", "123456");

            // SQL语句
            String sql = "SELECT * FROM texts";
            // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 获取结果集
            resultSet = preparedStatement.executeQuery();
            // 结果集解析
            while (resultSet.next()) {
                Prose prose = new Prose();
                prose.setTitle(resultSet.getString("title"));
                prose.setAuthor(resultSet.getString("author"));
                prose.setKey1(resultSet.getString("keyword1"));
                prose.setKey2(resultSet.getString("keyword2"));
                prose.setKey3(resultSet.getString("keyword3"));
                prose.setKey4(resultSet.getString("keyword4"));
                prose.setKey5(resultSet.getString("keyword5"));
                prose.setSummary1(resultSet.getString("summary1"));
                prose.setSummary2(resultSet.getString("summary2"));
                prose.setEmotion(resultSet.getFloat("emotion"));
                prose.setContain(resultSet.getString("contain"));

                list.add(prose);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
