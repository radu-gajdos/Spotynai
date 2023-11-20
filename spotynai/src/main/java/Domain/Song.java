package Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Song")
public class Song {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Song_id_seq")

    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Album albumId;

    @ManyToMany(cascade = CascadeType.ALL)
    private ArrayList<Artist> artist;

    @ManyToMany(cascade = CascadeType.ALL)
    private ArrayList<Genre> genre;

    private String title;

    private int duration;

    private LocalDate releaseDate;

    private String lyrics;

}
