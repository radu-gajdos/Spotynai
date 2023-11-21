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
@Table(name = "Album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Song_id_seq")
    private Long id;

    private String title;

    private LocalDate releaseDate;

    @ManyToMany(cascade = CascadeType.ALL)
    private ArrayList<Song> songs;

    @OneToOne
    private Artist artist;


}
