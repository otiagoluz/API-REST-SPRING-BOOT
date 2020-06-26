package me.tiagoluz.forum.controller.form;

import me.tiagoluz.forum.model.Topico;
import me.tiagoluz.forum.repository.TopicoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateTopicForm {
  @NotNull @NotEmpty @Length(min = 5)
  private String titulo;

  @NotNull @NotEmpty @Length(min = 10)
  private String mensagem;

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getMensagem() {
    return mensagem;
  }

  public void setMensagem(String mensagem) {
    this.mensagem = mensagem;
  }

  public Topico update(Long id, TopicoRepository topicoRepository) {
    Topico topico = topicoRepository.getOne(id);
    topico.setTitulo(this.titulo);
    topico.setMensagem(this.mensagem);

    return topico;
  }
}
