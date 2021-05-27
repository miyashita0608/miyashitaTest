package com.example.demo.controller;

import com.example.demo.model.Common;
import com.example.demo.model.StudentDao;
import com.example.demo.model.StudentData;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentListController {
  Common common;

  public StudentListController(){
    common = new Common();
  }

  @RequestMapping("/studentList")
  public String getStudent(Model model) {
    try{
      StudentDao studentDao = new StudentDao(common);
      List<StudentData> students = studentDao.selData(0);
      model.addAttribute("studentList", students);
    } catch (Exception e){
      e.printStackTrace();
    }
    return "studentList";
  }
}
