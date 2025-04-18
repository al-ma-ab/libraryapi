package io.github.al_ma_ab.libraryapi.repository;

import io.github.al_ma_ab.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {


    @Autowired
    AutorRepository repository;

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
}
