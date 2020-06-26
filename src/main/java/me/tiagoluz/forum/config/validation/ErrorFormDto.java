package me.tiagoluz.forum.config.validation;

public class ErrorFormDto {

  private String campo;
  private String error;

  public ErrorFormDto(String campo, String error) {
    this.campo = campo;
    this.error = error;
  }

  public String getCampo() {
    return campo;
  }

  public String getError() {
    return error;
  }

}
