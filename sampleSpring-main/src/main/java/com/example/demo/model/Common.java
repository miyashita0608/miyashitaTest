package com.example.demo.model;

public class Common {
  private final String db_url;
  private final String db_user;
  private final String db_password;

  public String getDb_url() {
    return db_url;
  }

  public  String getDb_user() {
    return db_user;
  }

  public  String getDb_password() {
    return db_password;
  }

  public Common(){
    db_url = "jdbc:mysql://localhost/test4?serverTimezone=JST";
    db_user = "root";
    db_password = "root123";
  }

  public void logProc(Exception ex){
    try {
      ex.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
