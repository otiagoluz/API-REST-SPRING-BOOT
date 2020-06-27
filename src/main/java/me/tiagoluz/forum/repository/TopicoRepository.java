package me.tiagoluz.forum.repository;

import me.tiagoluz.forum.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

  Page<Topico> findByCursoNome(String nomeCurso, Pageable pageable );

  //exemplo usando JPQL
  @Query("SELECT t FROM Topico t WHERE t.curso.nome = :nomeCurso")
  List<Topico> carregarPorNomeCurso(@Param("nomeCurso") String nomeCurso);
}
