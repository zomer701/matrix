package com.matrix.numbers.matrix.service;

import com.matrix.numbers.matrix.dto.MatrixDto;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiChatModelName;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MatrixProcessingService {

    @Resource
    private final OpenAiChatModel openAiChatModel;

    public MatrixProcessingService(@Value("${number.openapi.key}") String openApiKey) {
        openAiChatModel = OpenAiChatModel.builder()
                .apiKey(openApiKey)
                .modelName(OpenAiChatModelName.GPT_4_O_MINI)
                .build();
    }

    public Mono<String> getChatResponse(MatrixDto matrixDto) {
        return Mono.fromCallable(() -> openAiChatModel.generate("Say 'Hello World'"));
    }
}
