package io.github.al_ma_ab.libraryapi;

import io.github.al_ma_ab.libraryapi.model.Autor;
import io.github.al_ma_ab.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {
    public static void main(String[] args){
        var context  = SpringApplication.run(Application.class, args);
        AutorRepository repository = context.getBean(AutorRepository.class);

        exemploSalvarRegistro(repository);
    }

    public static void exemploSalvarRegistro(AutorRepository autorRepository){
        Autor autor = new Autor();

        autor.setNome("alexandre martins");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1983, 04, 02));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);

    }
}
