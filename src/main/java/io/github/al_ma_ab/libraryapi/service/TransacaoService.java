package io.github.al_ma_ab.libraryapi.service;


import io.github.al_ma_ab.libraryapi.model.Autor;
import io.github.al_ma_ab.libraryapi.model.GeneroLivro;
import io.github.al_ma_ab.libraryapi.model.Livro;
import io.github.al_ma_ab.libraryapi.repository.AutorRepository;
import io.github.al_ma_ab.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository
                .findById(UUID.fromString("f7202f8b-aad1-4acb-afb4-d6535ab3a7c9"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2025, 3, 26));

        //Não preciso da linha abaixo, porque eu tenho uma Transação aberta @Transactional, e isso é o
        //Suficiente para fazer a transação no banco de dados e salvar
        //livroRepository.save(livro);
    }



    @Transactional
    public void executar(){

        // Salva o autor
        Autor autor = new Autor();
        autor.setNome("Francisca 2");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1981, 6, 30));

        autorRepository.save(autor);

        // Salva o livro
        Livro livro = new Livro();
        livro.setIsbn("90855-11111");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("livro da francisca 2");
        livro.setDataPublicacao(LocalDate.of(2025, 10, 1));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Francisca 2")){
            throw new RuntimeException("Rollback!");
        }

    }
}
