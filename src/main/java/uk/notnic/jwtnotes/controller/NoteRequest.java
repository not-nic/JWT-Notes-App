package uk.notnic.jwtnotes.controller;

public class NoteRequest {

  private String content;

  public NoteRequest() {
  }

  public NoteRequest(String content) {
    this.content = content;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
