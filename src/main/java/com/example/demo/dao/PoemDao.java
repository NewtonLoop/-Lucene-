package com.example.demo.dao;

import com.example.demo.pojo.Poem;
import com.example.demo.pojo.Stitching_Poetry;

import java.util.List;

public interface PoemDao {


    public List<Poem> queryAllSearch();

    public List<Stitching_Poetry> queryStitchSearch();

}
