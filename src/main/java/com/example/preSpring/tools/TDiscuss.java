package com.sample;


public class TDiscuss {

  private long id;
  private String discussNum;
  private java.sql.Timestamp discussDate;
  private String discussTitle;
  private String discussPhoto;
  private String discussContent;
  private String courseNum;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getDiscussNum() {
    return discussNum;
  }

  public void setDiscussNum(String discussNum) {
    this.discussNum = discussNum;
  }


  public java.sql.Timestamp getDiscussDate() {
    return discussDate;
  }

  public void setDiscussDate(java.sql.Timestamp discussDate) {
    this.discussDate = discussDate;
  }


  public String getDiscussTitle() {
    return discussTitle;
  }

  public void setDiscussTitle(String discussTitle) {
    this.discussTitle = discussTitle;
  }


  public String getDiscussPhoto() {
    return discussPhoto;
  }

  public void setDiscussPhoto(String discussPhoto) {
    this.discussPhoto = discussPhoto;
  }


  public String getDiscussContent() {
    return discussContent;
  }

  public void setDiscussContent(String discussContent) {
    this.discussContent = discussContent;
  }


  public String getCourseNum() {
    return courseNum;
  }

  public void setCourseNum(String courseNum) {
    this.courseNum = courseNum;
  }

}
