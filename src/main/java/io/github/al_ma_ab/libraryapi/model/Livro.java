package io.github.al_ma_ab.libraryapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data // Lombok: gera automaticamente getters, setters, toString, equals e hashCode para todos os campos da classe.
@AllArgsConstructor
public class Livro {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false )
    private String titulo;

    @Column(name = "data_publicacao", nullable = false)
    private LocalDate data_publicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false )
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, nullable = true)
    private BigDecimal preco;
    //private BigDecimal;

    @ManyToOne //relacionamento muitos para um
    @JoinColumn(name = "id_autor")
    private Autor autor;
}
