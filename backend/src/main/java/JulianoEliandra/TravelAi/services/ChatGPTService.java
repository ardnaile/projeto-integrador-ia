package JulianoEliandra.TravelAi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bson.internal.BsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Map;

@Service
public class ChatGPTService {

    @Value("${openai.api.key}")
    private String apiKey;


    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";


    public String getChatResponse(String message) throws Exception {
        System.out.println(apiKey);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(OPENAI_URL);
            request.setHeader("Authorization", "Bearer " + apiKey);
            request.setHeader("Content-Type", "application/json");

            // Criação do payload
            String payload = new ObjectMapper().writeValueAsString(
                    Map.of(
                            "model", "gpt-3.5-turbo",
                            "messages", List.of(Map.of("role", "user", "content", message))
                    )
            );
            System.out.println("payload criado");
            request.setEntity(new StringEntity(payload));
            var response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == 200) {
                String jsonResponse = EntityUtils.toString(response.getEntity());

                // Extrair a resposta da API
                return new ObjectMapper().readTree(jsonResponse).path("choices").get(0).path("message").path("content").asText();
            } else {
                throw new RuntimeException("Erro na API: " + response.getStatusLine().getStatusCode());
            }
        }
    }
}
