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
import java.util.List;
import java.util.UUID;


@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(2000, 10, 1));

        Autor autor = autorRepository.
                findById(UUID.fromString("fd3a447c-4b40-457b-8286-e63dabb4c3a0")).
                orElse(null);

        //livro.setAutor(autor);

        var livroSalvo = repository.save(livro);
        System.out.println("Livro salvo " + livroSalvo);

    }

    @Test
    void salvarAutorELivroTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84864");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(2025, 10, 1));

        Autor autor = new Autor();
        autor.setNome("Alexandra Ceribelli Martins");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1981, 6, 30));

        autorRepository.save(autor);

        livro.setAutor(autor);

        var livroSalvo = repository.save(livro);
        System.out.println("Livro salvo " + livroSalvo);

    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(2025, 10, 1));

        Autor autor = new Autor();
        autor.setNome("Alexandra Ceribelli");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1981, 6, 30));

        livro.setAutor(autor);

        var livroSalvo = repository.save(livro);
        System.out.println("Livro salvo " + livroSalvo);

    }

    @Test
    void atualizarAutorDoLivro(){
        UUID id =  UUID.fromString("c6307fd7-f12e-4bdf-9476-dd94b68d253a");
        var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("d41ce44a-3695-400c-bc26-176810776e94");
        Autor autor = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(autor);

        repository.save(livroParaAtualizar);

    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("c6307fd7-f12e-4bdf-9476-dd94b68d253a");
        repository.deleteById(id);
    }

    @Test
    void deletarCascade(){
        UUID id = UUID.fromString("bc369edf-b1c5-4ee3-84c4-7633a0491cf9");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("8bbc58f9-38d3-493c-9e55-07f3fed3b400");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro");
        System.out.println(livro.getTitulo());
        System.out.println("Autor");
        System.out.println(livro.getAutor().getNome());

    }

    @Test
    void pesquisaPorTitulo(){
        List<Livro> lista = repository.findByTitulo("UFO");
        lista.forEach(System.out::println);

    }

    @Test
    void pesquisaPorTituloPrecoTest(){
        var preco = BigDecimal.valueOf(104);
        var tituloPesquisa = "UFO";
        List<Livro> lista = repository.findByTituloAndPreco(tituloPesquisa, preco);
        lista.forEach(System.out::println);
    }
    

    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = repository.listarTodosOrdenadoPorTituloAndPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros(){
        var resultado = repository.listarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarNomesNaoRepetitosDeLivros(){
        var resultado = repository.listarNomesDiferentesDeLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosLivros(){
        var resultado = repository.listarGenerosAutoresAmericanos();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryParamTest(){
        var resultado = repository.findByGenero(GeneroLivro.FICCAO, "dataPublicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarPorGeneroQueryPositionalParamTest(){
        var resultado = repository.findByGeneroPositionalParameters(GeneroLivro.MISTERIO, "dataPulicacao");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletePorGeneroTest(){
        repository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    void updateDataPublicacaoTest(){
        repository.updateDataPublicacao(LocalDate.of(2000, 1, 25 ));
    }

}