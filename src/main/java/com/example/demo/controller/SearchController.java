package com.example.demo.controller;


import com.alibaba.fastjson.JSONArray;
import com.example.demo.pojo.Poem;
import com.example.demo.pojo.Stitching_Poetry;
import com.example.demo.service.SearchService;
import com.example.demo.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(value = "/Haizi")
public class SearchController {

    @Autowired
    private SearchService all_searchService;
    @Autowired
    private SearchService stitching_searchService;
    @Autowired
    private SearchService short_searchService;
    @Autowired
    private SearchService medium_searchService;
    @Autowired
    private SearchService long_searchService;

    private String all_title;
    private String all_label;
    private String all_contain;

    private String stitching_key;


    /**
     *
     * @param qtitle
     * @param qlabel
     * @param qcontain
     * @throws Exception
     */

    @PostMapping("/Allsearch")
    @ResponseBody
    public void getAllSearch(@RequestParam("title") String qtitle,
                             @RequestParam("label") String qlabel,
                             @RequestParam("contain") String qcontain) throws Exception{
        this.all_title = qtitle;
        this.all_label = qlabel;
        this.all_contain = qcontain;

        System.out.println("client post AllSearch\n title: " + this.all_title + "\nlabel: " + this.all_label + "\ncontain" + this.all_contain);
    }


    /**
     *
     * @param qkey
     */

    @PostMapping("/Stitching")
    @ResponseBody
    public void getStitchingSearch(@RequestParam("key") String qkey){
        this.stitching_key = qkey;
        System.out.println("Client post StitchingSearch: " + this.stitching_key);
    }


    /**
     *
     * @return
     * @throws Exception
     */

    @GetMapping("/Allsearch/get")
    @ResponseBody
    public String postAllSearch() throws Exception {
        all_searchService = new SearchServiceImpl();
        List<Poem> poems = new ArrayList<>();
        poems = all_searchService.query_all(this.all_title, this.all_label, this.all_contain, 30);
        Object obg = JSONArray.toJSON(poems);
        String result = obg.toString();
        return result;
    }



    /**
     *
     * @return
     * @throws Exception
     */

    @GetMapping("/Stitching/get")
    @ResponseBody
    public String postStitchingSearch() throws Exception{
        stitching_searchService = new SearchServiceImpl();
        List<Stitching_Poetry> stitching_poetries = new ArrayList<>();

        if(!StringUtils.isEmpty(this.stitching_key))
            stitching_poetries = stitching_searchService.query_stitching(this.stitching_key, 50);

        Random r = new Random();
        List<Stitching_Poetry> stitching = stitching_searchService.query_stitching("", 2000);

        for(int i = 0; i < 20; i ++){
            int j = r.nextInt(stitching.size()-1);
            stitching_poetries.add(stitching.get(j));
        }
        Object obg = JSONArray.toJSON(stitching_poetries);
        String result = obg.toString();
        return result;
    }

    /**
     *
     * @return
     * @throws Exception
     */

    @GetMapping("/random/get")
    @ResponseBody
    public String postRandomSearch() throws Exception{
        Random r = new Random();
        stitching_searchService = new SearchServiceImpl();
        List<Stitching_Poetry> stitching_random = new ArrayList<>();
        List<Stitching_Poetry> stitching = stitching_searchService.query_stitching("", 2000);
        List<Stitching_Poetry> pomes = new ArrayList<>();
        for(int i = 0; i < 20; i ++){
            int j = r.nextInt(stitching_random.size()-1);
            stitching_random.add(stitching.get(j));
        }



        Object obg = JSONArray.toJSON(stitching_random);
        String result = obg.toString();
        return result;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/short/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Poem> postShort() throws Exception {
        short_searchService = new SearchServiceImpl();
        List<Poem> poems = new ArrayList<>();
        poems = all_searchService.query_all("", "短篇", "", 232);
        return poems;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/medium/get", method = RequestMethod.GET)
    @ResponseBody
    public List<Poem> postMedium() throws Exception {
        medium_searchService = new SearchServiceImpl();
        List<Poem> poems = new ArrayList<>();
        poems = all_searchService.query_all("", "文论", "", 20);
        return poems;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/long/get", method = RequestMethod.GET)
    @ResponseBody
    public String postLong() throws Exception {
        long_searchService = new SearchServiceImpl();
        List<Poem> poems = new ArrayList<>();
        poems = all_searchService.query_all("", "长篇", "", 20);
        Object obg = JSONArray.toJSON(poems);
        String result = obg.toString();
        return result;
    }


}
