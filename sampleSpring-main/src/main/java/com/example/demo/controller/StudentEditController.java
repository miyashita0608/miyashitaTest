package com.example.demo.controller;

import com.example.demo.model.Common;
import com.example.demo.model.ScoreDao;
import com.example.demo.model.ScoreData;
import com.example.demo.model.StudentDao;
import com.example.demo.model.StudentData;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentEditController {
  Common common;

  public StudentEditController(){
    common = new Common();
  }

  @GetMapping("/studentEdit")
  public String editStudent(@RequestParam(name = "id") String id, @RequestParam(name = "send") String send, Model model) {
    String returnPage = "editStudent";
    try{
      if(Integer.parseInt(id) >= 0) {
        switch(send){
          case "更新":
            StudentDao studentDao = new StudentDao(common);
            List<StudentData> students = studentDao.selData(Integer.parseInt(id));
            model.addAttribute("studentList", students.get(0));
            break;
          case "成績照会":
            ScoreDao scoreDao = new ScoreDao(common);
            scoreDao.student_id = Integer.parseInt(id);
            List<ScoreData> scoreListNengetsu = scoreDao.selData();
            List<ScoreData> scoreListDetail = scoreDao.selStudentScore();
            model.addAttribute("scoreListNengetsu", scoreListNengetsu);
            model.addAttribute("scoreListDetail", scoreListDetail);
            model.addAttribute("scoreStudent", scoreListDetail.get(0).getStudentName());
            returnPage = "scoreList";
            break;
        }
      }else{
        StudentData students = new StudentData();
        students.setId(-1);
        model.addAttribute("studentList", students);
      }
    } catch (Exception e){
      e.printStackTrace();
    }
    return returnPage;
  }

  @PostMapping("/edit")
  public String execStudent(@ModelAttribute StudentData data, Model model) {
    try{
      StudentDao studentDao = new StudentDao(common);
      if(data.getId() < 0) {
        studentDao.insData(data);
      }else{
        studentDao.updData(data);
      }
      List<StudentData> students = studentDao.selData(0);
      model.addAttribute("studentList", students);
    } catch (Exception e){
      e.printStackTrace();
    }
    return "studentList";
  }
}
