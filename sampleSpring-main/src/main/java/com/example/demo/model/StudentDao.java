package com.example.demo.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StudentDao {
  private Common common = null;

  public StudentDao(Common prCommon){
    common = prCommon;
  }

  public ArrayList<StudentData> selData(int id) throws Exception{
    ArrayList<StudentData> studentList = new ArrayList<>();
    StudentData studentDto = new StudentData();

    try (Connection conn = DriverManager.getConnection(common.getDb_url(),common.getDb_user(),
        common.getDb_password())){

      try{
        conn.setAutoCommit(false);

        StringBuilder sql = new StringBuilder();
        sql.append("select * from student ");
        if(id > 0){
          sql.append("where id = ? ");
        }

        PreparedStatement stmt = conn.prepareStatement(sql.toString());
        if(id > 0){
          stmt.setInt(1, id);
        }

        // SQL実行
        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()){
          studentDto = new StudentData();
          studentDto.setId(resultSet.getInt("id"));
          studentDto.setFamilyName(resultSet.getString("氏"));
          studentDto.setFirstName(resultSet.getString("名"));
          studentDto.setFamilyNameKana(resultSet.getString("セイ"));
          studentDto.setFirstNameKana(resultSet.getString("メイ"));
          studentDto.setTel(resultSet.getString("電話番号"));
          studentDto.setPost(resultSet.getString("郵便番号"));
          studentDto.setUndergraduate(resultSet.getString("学部"));
          studentDto.setDepartment(resultSet.getString("学科"));
          studentList.add(studentDto);
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

    return studentList;
  }

  public void insData(StudentData studentDto) throws Exception{
    try (Connection conn = DriverManager.getConnection(common.getDb_url(),common.getDb_user(),
        common.getDb_password())){

      try{
        conn.setAutoCommit(false);

        StringBuilder sql = new StringBuilder();
        sql.append("insert into student (id,氏,名,セイ,メイ,電話番号,郵便番号,学部,学科) values (");
        sql.append("?, ?, ?, ?, ?, ?, ?, ?, ? )");

        PreparedStatement stmt = conn.prepareStatement(sql.toString());
        stmt.setInt(1, this.getNewId());
        stmt.setString(2, studentDto.getFamilyName());
        stmt.setString(3, studentDto.getFirstName());
        stmt.setString(4, studentDto.getFamilyNameKana());
        stmt.setString(5, studentDto.getFirstNameKana());
        stmt.setString(6, studentDto.getTel());
        stmt.setString(7, studentDto.getPost().replace("-",""));
        stmt.setString(8, studentDto.getUndergraduate());
        stmt.setString(9, studentDto.getDepartment());

        // SQL実行
        int resultCnt = stmt.executeUpdate();

        conn.commit();
      } catch (Exception e) {
        conn.rollback();
        common.logProc(e);
        throw e;
      }
    } catch (Exception e){
      common.logProc(e);
      throw e;
    }
  }

  public int getNewId() throws Exception{
    int id = 0;

    try (Connection conn = DriverManager.getConnection(common.getDb_url(),common.getDb_user(),
      common.getDb_password())){

      try{
        StringBuilder sql = new StringBuilder();
        sql.append("select ifnull(max(id), 0) + 1 as max_id from student ");
        PreparedStatement stmt = conn.prepareStatement(sql.toString());

        ResultSet resultSet = stmt.executeQuery();

        while (resultSet.next()) {
          id = resultSet.getInt("max_id");
        }
      } catch (Exception e) {
        common.logProc(e);
        throw e;
      }
    } catch (Exception e){
      common.logProc(e);
      throw e;
    }

    return id;
  }

  public void updData(StudentData studentDto) throws Exception{
    try (Connection conn = DriverManager.getConnection(common.getDb_url(),common.getDb_user(),
        common.getDb_password())){

      try{
        conn.setAutoCommit(false);

        StringBuilder sql = new StringBuilder();
        sql.append("update student set ");
        sql.append("  氏 = ?, ");
        sql.append("  名 = ?, ");
        sql.append("  セイ = ?, ");
        sql.append("  メイ = ?, ");
        sql.append("  電話番号 = ?, ");
        sql.append("  郵便番号 = ?, ");
        sql.append("  学部 = ?, ");
        sql.append("  学科 = ? ");
        sql.append("where id = ? ");

        PreparedStatement stmt = conn.prepareStatement(sql.toString());
        stmt.setString(1, studentDto.getFamilyName());
        stmt.setString(2, studentDto.getFirstName());
        stmt.setString(3, studentDto.getFamilyNameKana());
        stmt.setString(4, studentDto.getFirstNameKana());
        stmt.setString(5, studentDto.getTel());
        stmt.setString(6, studentDto.getPost().replace("-",""));
        stmt.setString(7, studentDto.getUndergraduate());
        stmt.setString(8, studentDto.getDepartment());
        stmt.setInt(9, studentDto.getId());

        // SQL実行
        int resultCnt = stmt.executeUpdate();

        conn.commit();
      } catch (Exception e) {
        conn.rollback();
        common.logProc(e);
        throw e;
      }
    } catch (Exception e){
      common.logProc(e);
      throw e;
    }
  }
}
