package com.matrix.numbers.matrix.controller;

import com.matrix.numbers.matrix.dto.MatrixDto;
import com.matrix.numbers.matrix.service.MatrixProcessingService;
import com.matrix.numbers.matrix.utils.PythagoreanSquareUtils;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/matrix")
@Slf4j
public class MatrixController {

    @Resource
    private MatrixProcessingService matrixProcessingService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> sayHello(@RequestBody @Valid MatrixDto matrix) {
        return  matrixProcessingService.getChatResponse(matrix)
                .onErrorResume(error -> {
                    log.error("ChatResponse problem", error);
//            if (error instanceof IllegalArgumentException) {
//                return Mono.error(new CustomException("Custom error message for bad argument"));
//            }
            return Mono.error(error); // Pass through other errors
        });
    }

}
