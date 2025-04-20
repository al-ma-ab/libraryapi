package io.github.al_ma_ab.libraryapi.repository;

import io.github.al_ma_ab.libraryapi.model.Autor;
import io.github.al_ma_ab.libraryapi.model.GeneroLivro;
import io.github.al_ma_ab.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {


    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void SalvarTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio ceribelli");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1983, 04, 02));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void AtualizarTest(){
        var id = UUID.fromString("2e868634-faf1-4180-ae63-bf9c1158e32d");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();

            System.out.println("Dados do autor: ");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1983, 1, 30));

            repository.save(autorEncontrado);
        }


    }

    @Test
    public void ListarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void CountTest(){
        System.out.println("contagem de valores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("9202091f-f811-4ced-9f20-9597d21c5c0c");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("be1e8bb2-5f3f-4ea0-b546-64906732c657");
        var maria = repository.findById(id).get(); //aqui só estou chamando o metodo get porque eu sei que existe a maria
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio ceribelli Gonçalves Martins");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(2025, 01, 25));

        Livro livro = new Livro();
        livro.setIsbn("99999-84874");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("O Roubo da casa assombrada");
        livro.setDataPublicacao(LocalDate.of(1999, 10, 1));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("99999-84787");
        livro2.setPreco(BigDecimal.valueOf(304));
        livro2.setGenero(GeneroLivro.FANTASIA);
        livro2.setTitulo("O misterio da libelula");
        livro2.setDataPublicacao(LocalDate.of(1999, 10, 1));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor(){
        var id = UUID.fromString("7d9cf984-faef-44fe-9acb-f2a4773330e6");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}
