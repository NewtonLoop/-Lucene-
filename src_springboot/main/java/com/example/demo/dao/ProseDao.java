package com.example.demo.dao;

import com.example.demo.pojo.Prose;

import java.util.List;

public interface ProseDao {




        /**
         * 查询所有的Sku数据
         * @return
         **/

        public List<Prose> queryProseList();

}
