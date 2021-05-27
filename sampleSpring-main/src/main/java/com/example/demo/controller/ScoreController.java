package com.example.demo.controller;

import com.example.demo.model.Common;
import com.example.demo.model.ScoreData;
import com.example.demo.model.ScoreDao;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScoreController {
  Common common;

  public ScoreController(){
    common = new Common();
  }

  @RequestMapping("/scoreList")
  public String getScore(Model model) {
    return "scoreList";
  }

  @RequestMapping("/scoreList2")
  public String setScore(@RequestParam(name = "nengetsu") String nengetsu, @RequestParam(name = "id") String id, Model model) {
    try{
      ScoreDao scoreDao = new ScoreDao(common);
      scoreDao.nengetsu = nengetsu;
      scoreDao.student_id = id.length() == 0 ? -1 : Integer.parseInt(id);
      List<ScoreData> scoreListNengetsu = scoreDao.selData();
      List<ScoreData> scoreListDetail = scoreDao.selStudentScore();
      String scoreStudent = id.length() == 0 ? nengetsu : scoreListDetail.get(0).getStudentName();

      // viewへ値を渡す
      model.addAttribute("scoreListNengetsu", scoreListNengetsu);
      model.addAttribute("scoreListDetail", scoreListDetail);
      model.addAttribute("scoreStudent", scoreStudent);
    } catch (Exception e){
      e.printStackTrace();
    }
    return "scoreList";
  }
}
