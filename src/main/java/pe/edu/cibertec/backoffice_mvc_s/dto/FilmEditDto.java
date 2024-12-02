package pe.edu.cibertec.backoffice_mvc_s.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record FilmEditDto(Integer filmId,
                          String title,
                          String description,
                          Integer releaseYear,
                          Integer languageId,
                          String languageName,
                          //Integer original_language_id,
                          Integer rentalDuration,
                          Double rentalRate,
                          Integer length,
                          Double replacementCost,
                          String rating,
                          String specialFeatures,
//                        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
                          Date lastUpdate) {
}
