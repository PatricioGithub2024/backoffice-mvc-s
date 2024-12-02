package pe.edu.cibertec.backoffice_mvc_s.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDetailDto;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDto;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmEditDto;
import pe.edu.cibertec.backoffice_mvc_s.entity.Film;
import pe.edu.cibertec.backoffice_mvc_s.entity.Language;
import pe.edu.cibertec.backoffice_mvc_s.repository.FilmRepository;
import pe.edu.cibertec.backoffice_mvc_s.repository.LanguageRepository;
import pe.edu.cibertec.backoffice_mvc_s.service.MaintenanceService;

import java.util.*;

@Service  //ya que tiene que estar mappeado para hacer @Autowired
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    FilmRepository filmRepository;
    @Autowired
    private LanguageRepository languageRepository;


    @Override
    public List<FilmDto> getAllFilms() {

        List<FilmDto> films = new ArrayList<FilmDto>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            //realizar la discriminacion
            FilmDto filmDto = new FilmDto(film.getFilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalRate());
            films.add(filmDto);
        });

        return films;
    }

    @Override
    public FilmDetailDto getFilmById(int id) {

        Optional<Film> optional = filmRepository.findById(id);

        return optional.map(
                film -> new FilmDetailDto(film.getFilmId(),
                        film.getTitle(),
                        film.getDescription(),
                        film.getReleaseYear(),
                        film.getLanguage().getLanguageId(),
                        film.getLanguage().getName(),
                        film.getRentalDuration(),
                        film.getRentalRate(),
                        film.getLength(),
                        film.getReplacementCost(),
                        film.getRating(),
                        film.getSpecialFeatures(),
                        film.getLastUpdate())

        ).orElse(null);
    }

    @Override
    public FilmEditDto getFilmEditById(int id) {
        Optional<Film> optional = filmRepository.findById(id);

        return optional.map(
                film -> new FilmEditDto(
                        film.getFilmId(),
                        film.getTitle(),
                        film.getDescription(),
                        film.getReleaseYear(),
                        film.getLanguage().getLanguageId(),
                        film.getLanguage().getName(),
                        film.getRentalDuration(),
                        film.getRentalRate(),
                        film.getLength(),
                        film.getReplacementCost(),
                        film.getRating(),
                        film.getSpecialFeatures(),
                        film.getLastUpdate())

        ).orElse(null);
    }

    @Override
    public void updateFilmById(int id, FilmEditDto filmEditDto) {

        // Buscar por Id
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Film not found"));
        //para language
        Language language = languageRepository.findById(filmEditDto.languageId())
                .orElseThrow(() -> new EntityNotFoundException("Language with ID not found"));

        // Actualizar
        film.setTitle(filmEditDto.title());
        film.setDescription(filmEditDto.description());
        film.setReleaseYear(filmEditDto.releaseYear());
        film.setLanguage(language);
        film.setRentalDuration(filmEditDto.rentalDuration());
        film.setRentalRate(filmEditDto.rentalRate());
        film.setLength(filmEditDto.length());
        film.setReplacementCost(filmEditDto.replacementCost());
        film.setRating(filmEditDto.rating());
        film.setSpecialFeatures(filmEditDto.specialFeatures());
        film.setLastUpdate(new Date());
        // Guardar en DB
        filmRepository.save(film);

    }


}
