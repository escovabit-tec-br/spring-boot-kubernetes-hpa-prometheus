package br.tec.escovabit.apphpa.controller;

import java.time.OffsetDateTime;

import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.tec.escovabit.apphpa.controller.model.response.ThreadWaitModel;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ping")
@Slf4j
public class PingController {

    @SneakyThrows
    @GetMapping("/thread-wait/{time}")
    public ResponseEntity<ThreadWaitModel> threadWait(@PathVariable("time") Long time) {
        ThreadWaitModel model = new ThreadWaitModel();
        model.setStartDate(OffsetDateTime.now());
        log.info("Thread wait start: {}", model.getStartDate());
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.error("Thread InterruptedException", e);
            throw e;
        }
        model.setEndDate(OffsetDateTime.now());
        log.info("Thread wait end: {}", model.getEndDate());
        return ResponseEntity.ok().body(model);
    }

}
