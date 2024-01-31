package pl.programujodpodstaw.javafxpierwszyprojekt;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class OpenAiHelper {
    private final OpenAiService service;

    public OpenAiHelper() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            System.out.println("Nie mogę otworzyć pliku");
        }

        service = new OpenAiService(properties.getProperty("token"), Duration.ofSeconds(60));
    }

    public String recommend(List<String> products) {
        String question = "Mam w lodówce następujące produkty: " + String.join(", ", products) + ". " +
                "Daj mi przepis na danie, które mogę z nich przygotować. Nie musisz wykorzystywać wszystkich produktów." +
                "Nie wykorzystuj żadnych produktów spoza mojej listy.";

//        String question = "Mam w lodówce następujące produkty: " + String.join(", ", products) + ". " +
//                "Daj mi przepis na danie, które mogę z nich przygotować. Nie musisz wykorzystywać wszystkich produktów." +
//                "Nie wykorzystuj żadnych produktów spoza mojej listy. Zwróć przepis w formacie JSON, czyli na przykład " +
//                """
//                        {
//                            "ingredients": ["papryka", "czosnek", "masło"],
//                            "steps": ["Pokrój paprykę", "Dodaj czosnek"]
//                       }
//                       """;

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .messages(List.of(new ChatMessage("user", question)))
                .model("gpt-3.5-turbo")
                .build();

        List<ChatCompletionChoice> choices = service.createChatCompletion(request).getChoices();

        return choices.stream()
                .map(ChatCompletionChoice::getMessage)
                .map(ChatMessage::getContent)
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
