package com.example.demo.dao;

import com.example.demo.pojo.Poem;
import com.example.demo.pojo.Stitching_Poetry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PoemImpl implements PoemDao {

    @Override
    public List<Poem> queryAllSearch(){
        Connection connection = null;

        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        List<Poem> list = new ArrayList<>();

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/search_engine", "root", "123456");

            // SQL语句
            String sql = "SELECT * FROM ljy_all_search";
            // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 获取结果集
            resultSet = preparedStatement.executeQuery();
            // 结果集解析
            while (resultSet.next()) {
                Poem poem = new Poem();
                poem.setFirst_line(resultSet.getString("first_line"));
                poem.setContain(resultSet.getString("contain"));
                poem.setLabel(resultSet.getString("label"));
                poem.setTitle(resultSet.getString("title"));

                list.add(poem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<Stitching_Poetry> queryStitchSearch(){
        Connection connection = null;

        PreparedStatement preparedStatement = null;

        ResultSet resultSet = null;

        List<Stitching_Poetry> list = new ArrayList<>();

        try {
            // 加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 连接数据库
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/search_engine", "root", "123456");

            // SQL语句
            String sql = "SELECT * FROM distinct_search";
            // 创建preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            // 获取结果集
            resultSet = preparedStatement.executeQuery();
            // 结果集解析
            while (resultSet.next()) {
                Stitching_Poetry poem = new Stitching_Poetry();
                poem.setLine(resultSet.getString("line"));
                poem.setTitle(resultSet.getString("title"));

                list.add(poem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
