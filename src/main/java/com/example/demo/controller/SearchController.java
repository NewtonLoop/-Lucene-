package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.example.demo.pojo.Prose;
import com.example.demo.service.SearchService;
import com.example.demo.service.SearchServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 */
@Controller
@RequestMapping(value = "/do/prose")
public class SearchController {

    @Autowired
    private SearchService searchService;

    private SearchServiceImpl searchServiceimpl;
    private String title;
    private String author;
    private String contain;

    /**
     *
     * @param qtitle
     * @param qauthor
     * @param qcontain
     * @return
     */

    @PostMapping("/test")
    @ResponseBody
    public void save(@RequestParam("title") String qtitle,
                       @RequestParam("author") String qauthor,
                     @RequestParam("contain") String qcontain) throws Exception {
        this.title = qtitle;
        this.author = qauthor;
        this.contain = qcontain;

        searchService.query(qtitle, qauthor,qcontain,1);

        System.out.println("书名：" + this.title + ", 作者: " + this.author + "  " + this.contain);

    }

    /**
     *
     * @return
     * @throws Exception
     */

    @GetMapping("/test")
    @ResponseBody
    public String testget() throws Exception {
        searchService = new SearchServiceImpl();
        List<Prose> list = searchService.query(this.title, this.author, this.contain,10);
        List<Prose> query = new ArrayList<>();
        if(StringUtils.isEmpty(this.title) && StringUtils.isEmpty(this.contain)){
            Random r = new Random();
            int size;
            if(list.size() < 50)
                size = list.size();
            else
                size = 50;
            for (int i = 0; i < size; i ++){
                int j = r.nextInt(list.size() - 1);
                query.add(list.get(j));
            }
            list = query;
        }
        Object obg = JSONArray.toJSON(list);
        String result = obg.toString();
        return result;
    }


    /**
     * 搜索
     * @param qt   查询的类型
     * @param qa        查询关键字
     * @param qc
     * @param page          当前页
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/proseAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Prose> query(String qt, String qa, String qc,Integer page) throws Exception{

        //处理当前页
//        if (StringUtils.isEmpty(page)) {
//            page = 1;
//        }
//        if (page <= 0) {
//            page = 1;
//        }

        //调用service查询
        searchService = new SearchServiceImpl();
        List<Prose>  list = new ArrayList<>();
//        List<Prose>  list= searchService.query(category, querystring, page);

        list= searchService.query("夏天","汪曾祺", "都是", 1);

//        model.addAttribute("result", resultModel);
//
//        for(Prose prose : list){
//            System.out.println(prose.getTitle());
//        }
//
//        //查询条件回显到页面
//        model.addAttribute("queryString", queryString);
//        model.addAttribute("price", price);
//        model.addAttribute("page", page);
        return list;
    }

    @RequestMapping(value = "/prose", method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "success!";
    }

    static class ParamDemo{
        private String param1;
        private String param2;

        public String getParam1() {
            return param1;
        }

        public void setParam1(String param1) {
            this.param1 = param1;
        }

        public String getParam2() {
            return param2;
        }

        public void setParam2(String param2) {
            this.param2 = param2;
        }
    }

    static class QueryProse{
        private String title;
        private String contain;
        private String author;

        public String getTitle() {
            return title;
        }

        public String getContain() {
            return contain;
        }

        public String getAuthor() {
            return author;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContain(String contain) {
            this.contain = contain;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

    }

}
