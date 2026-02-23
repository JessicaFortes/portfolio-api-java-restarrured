package br.com.portfolio.support;

import br.com.portfolio.clients.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.nio.file.Path;
import java.util.Map;

public class TestDataSeeder {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String FIXTURE = "users.json";

    public static void seed(ApiClient apiClient, String token) {
        try {
            Path filePath = Path.of(System.getProperty("user.dir"), "data", FIXTURE);
            Map<String, Object> root = MAPPER.readValue(filePath.toFile(), new TypeReference<>() {});

            seedUser(root, "consulta", apiClient, token);
            seedUser(root, "alteracao", apiClient, token);
            seedUser(root, "remover", apiClient, token);

            MAPPER.writerWithDefaultPrettyPrinter().writeValue(filePath.toFile(), root);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao preparar massa de usuários", e);
        }
    }

    @SuppressWarnings("unchecked")
    private static void seedUser(Map<String, Object> root, String key, ApiClient apiClient, String token) throws Exception {
        Object node = root.get(key);
        if (!(node instanceof Map)) {
            throw new RuntimeException("Massa '" + key + "' nao encontrada em " + FIXTURE);
        }

        Map<String, Object> user = (Map<String, Object>) node;
        // Do not send _id on POST
        Map<String, Object> payload = new java.util.HashMap<>(user);
        payload.remove("_id");
        String body = MAPPER.writeValueAsString(payload);
        Response response = apiClient.postUser(token, body);

        String userId = null;
        if (response.statusCode() == 201) {
            userId = response.jsonPath().getString("_id");
        } else {
            String email = (String) user.get("email");
            Response search = apiClient.getUsers(token, Map.of("email", email));
            if (search.statusCode() == 200 && search.jsonPath().getInt("quantidade") > 0) {
                userId = search.jsonPath().getString("usuarios[0]._id");
            } else {
                String status = String.valueOf(response.statusCode());
                String bodyResp = response.asString();
                throw new RuntimeException("Falha ao criar/buscar usuário '" + key + "' (status " + status + "): " + bodyResp);
            }
        }

        if (userId == null || userId.isBlank()) {
            throw new RuntimeException("Nao foi possivel obter _id para massa '" + key + "'");
        }

        user.put("_id", userId);
    }
}

