package es.safareyes.schoolevents.controladores;

import es.safareyes.schoolevents.dto.request.EventoBorradorRequest;
import es.safareyes.schoolevents.dto.response.EventoCalendarioResponse;
import es.safareyes.schoolevents.dto.response.EventoDetalleResponse;
import es.safareyes.schoolevents.dto.response.EventoResponse;
import es.safareyes.schoolevents.dto.response.EventoResumenResponse;
import es.safareyes.schoolevents.servicios.EventoServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/eventos")
@AllArgsConstructor
public class EventoController {
    private EventoServiceImpl eventoServiceImpl;

    @GetMapping("/filtrar")
    public List<EventoResumenResponse> consultarConFiltros(@RequestParam(required = false) String titulo,
                                               @RequestParam(required = false) Integer categoria,
                                               @RequestParam(required = false) List<Integer> cursos,
                                               @RequestParam(required = false) LocalDateTime fechaHoraIni,
                                               @RequestParam(required = false) LocalDateTime fechaHoraFin) {
        return eventoServiceImpl.consultarEventosConFiltros(titulo, categoria, cursos, fechaHoraIni, fechaHoraFin);
    }

    @GetMapping("/{id}")
    public EventoDetalleResponse consultarDetalles(@PathVariable Integer id) {
        return eventoServiceImpl.consultarDetallesEvento(id);
    }

    @GetMapping("/calendario")
    public List<EventoCalendarioResponse> consultarCalendario(@RequestParam Integer mes,
                                                              @RequestParam Integer ano) {
        return eventoServiceImpl.consultarEventosDelMesPorDia(mes, ano);
    }

    @GetMapping("/categoria/{id}")
    public List<EventoResumenResponse> consultarPorCategoria(@PathVariable Integer id) {
        return eventoServiceImpl.consultarListaEventosporCategoria(id);
    }

    @PostMapping
    public ResponseEntity<EventoResponse> crearEvento(@Valid @RequestBody EventoBorradorRequest json) {
        EventoResponse creado = eventoServiceImpl.crearEvento(json);

        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.id())
                .toUri();

        return ResponseEntity.created(ubicacion).body(creado);
    }

    @PatchMapping("/{id}")
    public void cancelarEvento(@PathVariable Integer id) {
        eventoServiceImpl.cancelarEvento(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarEvento(@PathVariable Integer id) {
        eventoServiceImpl.eliminarEvento(id);
    }
//    public void editarEvento(@PathVariable Integer id, @Valid @RequestBody ) {

//    }
}