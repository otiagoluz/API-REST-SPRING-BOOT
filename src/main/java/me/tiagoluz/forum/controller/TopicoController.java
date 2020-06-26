package me.tiagoluz.forum.controller;

import me.tiagoluz.forum.controller.dto.DetalhesTopicoDto;
import me.tiagoluz.forum.controller.dto.TopicoDto;
import me.tiagoluz.forum.controller.form.TopicoForm;
import me.tiagoluz.forum.controller.form.UpdateTopicForm;
import me.tiagoluz.forum.model.Topico;
import me.tiagoluz.forum.repository.CursoRepository;
import me.tiagoluz.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

  @Autowired
  private TopicoRepository topicoRepository;

  @Autowired
  private CursoRepository cursoRepository;

  @GetMapping
  public List<TopicoDto> lista(String nomeCurso) {
    System.out.println(nomeCurso);
    if (nomeCurso == null) {
      List<Topico> topicos = topicoRepository.findAll();
      return TopicoDto.converter(topicos);
    } else {
      List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);
      return TopicoDto.converter(topicos);
    }
  }

  @PostMapping
  @Transactional
  public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
    Topico topico = form.converter(cursoRepository);
    topicoRepository.save(topico);

    URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
    return ResponseEntity.created(uri).body(new TopicoDto(topico));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetalhesTopicoDto> detail(@PathVariable Long id) {
    Optional<Topico> topico = topicoRepository.findById(id);
    if (topico.isPresent()) {
      return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));
    }
    return ResponseEntity.notFound().build();

//    return topico.map(value ->
//      ResponseEntity.ok(new DetalhesTopicoDto(value))).orElseGet(() ->
//      ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<TopicoDto> update(@PathVariable Long id, @RequestBody @Valid UpdateTopicForm form) {
    Optional<Topico> optional = topicoRepository.findById(id);
    if (optional.isPresent()) {
      Topico topico = form.update(id, topicoRepository);
      return ResponseEntity.ok(new TopicoDto(topico));
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  @Transactional
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Topico> optional = topicoRepository.findById(id);
    if (optional.isPresent()) {
      topicoRepository.deleteById(id);
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
  }

}
