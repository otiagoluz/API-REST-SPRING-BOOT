package me.tiagoluz.forum.repository;

import me.tiagoluz.forum.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {

  Curso findByNome(String nome);
}
