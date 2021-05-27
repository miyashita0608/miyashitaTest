package com.example.demo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ScoreDao {
  private Common common = null;
  public String nengetsu;
  public int student_id;

  public ScoreDao(Common prCommon){
    common = prCommon;
    this.nengetsu = "";
    this.student_id = -1;
  }

  public ArrayList<ScoreData> selData() throws Exception{
    ArrayList<ScoreData> scoreList = new ArrayList<>();
    ScoreData scoreData = new ScoreData();

    try (Connection conn = DriverManager.getConnection(common.getDb_url(),common.getDb_user(),
        common.getDb_password())){

      try{
        conn.setAutoCommit(false);

        StringBuilder sql = new StringBuilder();
        sql.append("select  ");
        sql.append("  DATE_FORMAT(試験日, '%Y/%m') as 年月, ");
        sql.append("  sum(成績) as 合計, ");
        sql.append("  avg(成績) as 平均, ");
        sql.append("  round(avg(成績), 0) as 平均2 ");
        sql.append("from  score ");
        if(nengetsu.length() > 0 && student_id >= 0){
          sql.append("where DATE_FORMAT(試験日, '%Y/%m') = ? ");
          sql.append("  and student_id = ? ");
        } else if(nengetsu.length() > 0){
          sql.append("where DATE_FORMAT(試験日, '%Y/%m') = ? ");
        } else if(student_id >= 0){
          sql.append("where student_id = ? ");
        }
        sql.append("group by DATE_FORMAT(試験日, '%Y/%m') ");
        sql.append("order by DATE_FORMAT(試験日, '%Y/%m') ");

        PreparedStatement stmt = conn.prepareStatement(sql.toString());
        if(nengetsu.length() > 0 && student_id >= 0){
          stmt.setString(1, nengetsu);
          stmt.setInt(2, student_id);
        } else if(nengetsu.length() > 0){
          stmt.setString(1, nengetsu);
        } else if(student_id >= 0){
          stmt.setInt(1, student_id);
        }

        // SQL実行
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
          scoreData = new ScoreData();
          scoreData.setNengetsu(resultSet.getString("年月"));
          scoreData.setScoreSum(resultSet.getInt("合計"));
          scoreData.setScoreAvg(resultSet.getDouble("平均"));
          scoreData.setScoreAvg_round(resultSet.getDouble("平均2"));
          scoreList.add(scoreData);
        }

        resultSet.close();
      } catch (Exception e) {
        conn.rollback();
        common.logProc(e);
        throw e;
      }
    } catch (Exception e){
      common.logProc(e);
      throw e;
    }

    return scoreList;
  }

  public ArrayList<ScoreData> selStudentScore() throws Exception{
    ArrayList<ScoreData> scoreList = new ArrayList<>();
    ScoreData scoreData = new ScoreData();

    try (Connection conn = DriverManager.getConnection(common.getDb_url(),common.getDb_user(),
        common.getDb_password())){

      try{
        conn.setAutoCommit(false);

        StringBuilder sql = new StringBuilder();
        sql.append("select ");
        sql.append("  student.id, 氏, 名, 試験日, 科目, 成績 ");
        sql.append("from ( ");
        sql.append("  select * from student ");
        if(student_id >= 0){
          sql.append("  where id = ? ");
        }
        sql.append("  ) student ");
        sql.append("  left join ( ");
        sql.append("  select * from score ");
        if(student_id >= 0 && nengetsu.length() > 0){
          sql.append("  where student_id = ? ");
          sql.append("    and DATE_FORMAT(試験日, '%Y/%m') = ? ");
        } else if(student_id >= 0){
          sql.append("  where student_id = ? ");
        } else if(nengetsu.length() > 0){
          sql.append("  where DATE_FORMAT(試験日, '%Y/%m') = ? ");
        }
        sql.append("  ) score ");
        sql.append("on student.id = score.student_id ");
        sql.append("order by student.id, 試験日, 科目 ");

        PreparedStatement stmt = conn.prepareStatement(sql.toString());
        if(student_id >= 0 && nengetsu.length() > 0) {
          stmt.setInt(1, student_id);
          stmt.setInt(2, student_id);
          stmt.setString(3, nengetsu);
        } else if(student_id >= 0){
          stmt.setInt(1, student_id);
          stmt.setInt(2, student_id);
        } else if(nengetsu.length() > 0){
          stmt.setString(1, nengetsu);
        }

        // SQL実行
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
          scoreData = new ScoreData();
          scoreData.setStudent_id(resultSet.getInt("id"));
          scoreData.setStudentName(resultSet.getString("氏") + " " + resultSet.getString("名"));
          scoreData.setTestDate(resultSet.getString("試験日"));
          scoreData.setSubject(resultSet.getString("科目"));
          scoreData.setScore(resultSet.getInt("成績"));
          scoreList.add(scoreData);
        }

        resultSet.close();
      } catch (Exception e) {
        conn.rollback();
        common.logProc(e);
        throw e;
      }
    } catch (Exception e){
      common.logProc(e);
      throw e;
    }

    return scoreList;
  }
}
