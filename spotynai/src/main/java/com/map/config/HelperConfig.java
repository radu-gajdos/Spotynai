package com.map.config;

import com.map.Domain.entities.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HelperConfig {
    public static boolean isValidEmail(String email) {
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }

        if (parts[0].length() < 2) {
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char character : password.toCharArray()) {
            if (Character.isUpperCase(character)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(character)) {
                hasLowercase = true;
            } else if (Character.isDigit(character)) {
                hasDigit = true;
            } else if (isSpecialCharacter(character)) {
                hasSpecialChar = true;
            }

            if (hasUppercase && hasLowercase && hasDigit && hasSpecialChar) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSpecialCharacter(char character) {
        String specialCharacters = "!@#$%?_-+=^&*()[]{}<>;:'/";
        return specialCharacters.indexOf(character) >= 0;
    }

    public static List<SongEntity> iaSongs() {

        SongEntity song1 = new SongEntity(
                null,
                null,
                null,
                null,
                "Draperiile",
                160,
                LocalDate.of(2023, 10, 30),
                "Am tras draperiiilleeeee"
        );

        SongEntity song2 = new SongEntity(
                null,
                null,
                null,
                null,
                "Ma doare la basca",
                160,
                LocalDate.of(2023, 10, 30),
                "Ma doare la basca"
        );

        SongEntity song3 = new SongEntity(
                null,
                null,
                null,
                null,
                "Amintirile",
                160,
                LocalDate.of(2023, 10, 30),
                "AMINTIRILE MA GHINUIESC AMINTIRILE MA RASCOLESC"
        );

        List<SongEntity> songs = new ArrayList<SongEntity>();
        songs.add(song1);
        songs.add(song2);
        songs.add(song3);

        return songs;
    }

    public static AlbumEntity iaAlbum() {
        ArtistEntity guta = iaArtist();
        ArrayList<ArtistEntity> gutaA = new ArrayList<>();
        gutaA.add(guta);
        AlbumEntity fericire = new AlbumEntity(null, "Fericire", LocalDate.of(2023, 8, 10), null, guta);
        SongEntity song1 = new SongEntity(null, fericire, gutaA, null, "Ne place", 200, LocalDate.of(2023, 8, 10) , "KAKAEKAKEKAEKALALALAL alLALLA");
        fericire.setSongs(song1);
        return fericire;
    }

    public static ArtistEntity iaArtist() {
        return new ArtistEntity(null, "Guta", "Regele manelelor", null);
    }

    public static List<UserEntity> iaUseri() {
        UserEntity user1 = new UserEntity(null,"alexandru", "alexutusefutau@gmail.com",  "Parola2021!", LocalDate.of(2023, 8, 11),null, "User");
        UserEntity user2 = new UserEntity(null, "Paul", "paul.lacatus@gmail.com", "Parola2021!", LocalDate.of(2023, 8, 11), null, "Admin");
        UserEntity user3 = new UserEntity(null, "udar", "alexutusefutau@gmail.com", "Parola2021!", LocalDate.of(2023, 8, 11),null, "User");
        UserEntity user4 = new UserEntity(null, "radu.gajdos", "gajdosradu@gmail.com", "Parola2021!", LocalDate.of(2003, 6, 11), null, "Admin");

        List<UserEntity> result = new ArrayList<>();
        result.add(user1);
        result.add(user2);
        result.add(user3);
        result.add(user4);
        return result;
    }

    public static List<GenreEntity> iaGenre() {
        GenreEntity genre1 = new GenreEntity(null, "Pop", "Pop music: a genre characterized by catchy melodies, relatable lyrics, and widespread appeal, often driven by commercial success and popular culture influence.", null);
        GenreEntity genre2 = new GenreEntity(null, "Manele", "Manele music: a genre originating in Romania, known for its blend of Balkan folk, Turkish, and Roma influences, featuring emotional vocals and often controversial lyrics.", null);
        GenreEntity genre3 = new GenreEntity(null, "Rock", "Rock music: a diverse genre marked by amplified instruments, dynamic rhythms, and rebellious spirit, spanning various subgenres from classic and punk to alternative and metal.", null);
        List<GenreEntity> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);
        genres.add(genre3);

        return genres;
    }
}
