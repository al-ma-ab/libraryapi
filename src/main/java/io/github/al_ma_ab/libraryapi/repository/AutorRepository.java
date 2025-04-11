package io.github.al_ma_ab.libraryapi.repository;

import io.github.al_ma_ab.libraryapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
