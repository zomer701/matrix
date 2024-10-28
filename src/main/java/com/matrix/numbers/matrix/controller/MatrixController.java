package com.matrix.numbers.matrix.controller;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_3_5_TURBO;
import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

@RestController
@RequestMapping("/api/matrix")
public class MatrixController {

    @Value("${number.openapi.key}")
    private String openApiKey;

    @PostMapping
    public Mono<String> sayHello() {

//        OpenAiChatModel model = OpenAiChatModel.builder()
//                .apiKey(openApiKey)
//                .modelName(GPT_4_O_MINI)
//                .build();
//
//        String answer = model.generate("Say 'Hello World'");
//        System.out.println(answer);

        return Mono.just("Hello, Reactive World!");
    }

}
