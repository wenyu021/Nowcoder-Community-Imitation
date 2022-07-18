package com.newcoder.community.controller;

import com.newcoder.community.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author Wenyu Wang
 * @project community
 * @date 2022-07
 */
@Controller
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @RequestMapping("/data")
    @ResponseBody
    public String getData(){
        return masterService.find();
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "hello";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name+": "+value);
        }
        System.out.println(request.getParameter("code"));

        //response data
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>New Coder<h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Get request

    // /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit){
        System.out.println(current);
        System.out.println(limit);
        return "Some students";
    }

    // /student/123
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "student id = "+id;
    }

    // POST request
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "Student saved";
    }

    // 响应HTML
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name","张三");
        mav.addObject("age","22");
        mav.setViewName("/demo/view");  // view.html
        return mav;
    }

    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public  String getSchool(Model model){
        model.addAttribute("name","北大");
        model.addAttribute("age",80);

        return "demo/view";
    }

    // 响应Json数据（异步请求）
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age",25);
        emp.put("salary",2000.00);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age",25);
        emp.put("salary",2000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","二嘎");
        emp.put("age",27);
        emp.put("salary",5000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","猪头");
        emp.put("age",22);
        emp.put("salary",8000.00);
        list.add(emp);

        return list;
    }

}
