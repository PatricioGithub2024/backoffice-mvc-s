package pe.edu.cibertec.backoffice_mvc_s.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDetailDto;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmDto;
import pe.edu.cibertec.backoffice_mvc_s.dto.FilmEditDto;
import pe.edu.cibertec.backoffice_mvc_s.service.MaintenanceService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/maintenance")         //define ruta del mapping
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    //tiene que haber un direccion URL
    @GetMapping("/start")
    public String start(Model model) {

        List<FilmDto> films = maintenanceService.getAllFilms();
        model.addAttribute("films", films);

        return "maintenance";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {

        FilmDetailDto filmDetailDto = maintenanceService.getFilmById(id);
        model.addAttribute("filmDetailDto", filmDetailDto);
        return "maintenance-detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        FilmEditDto filmEditDto = maintenanceService.getFilmEditById(id);
        model.addAttribute("filmEditDto", filmEditDto);
        return "maintenance-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateFilm(@PathVariable Integer id, @ModelAttribute FilmEditDto filmEditDto) {
        maintenanceService.updateFilmById(id, filmEditDto); // Llama al método de servicio que actualiza la entidad
        return "redirect:/maintenance/start"; // Redirige a la página de detalles después de guardar
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(
//                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"), true));
//    }

}
