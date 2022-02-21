package ru.gb.demoelastic.doc;

/**
 * {"title": "Pilot", "season": 1, "episode": 1, "subId": "1", "timeStamp": "00:00:46,470 --> 00:00:47,515", "text": "Ты почему опоздала?\n"}
 */
public class SubTitlesDoc {

  private String title;
  private Integer season;
  private Integer episode;
  private String subId;
  private String timeStamp;
  private String text;

  public SubTitlesDoc() {
  }

  public SubTitlesDoc(String title, Integer season, Integer episode, String subId, String timeStamp, String text) {
    this.title = title;
    this.season = season;
    this.episode = episode;
    this.subId = subId;
    this.timeStamp = timeStamp;
    this.text = text;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getSeason() {
    return season;
  }

  public void setSeason(Integer season) {
    this.season = season;
  }

  public Integer getEpisode() {
    return episode;
  }

  public void setEpisode(Integer episode) {
    this.episode = episode;
  }

  public String getSubId() {
    return subId;
  }

  public void setSubId(String subId) {
    this.subId = subId;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
