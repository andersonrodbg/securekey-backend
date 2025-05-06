package com.example.faceid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ReconhecimentoController {

    @Value("${face.api.key}")
    private String apiKey;

    @Value("${face.api.secret}")
    private String apiSecret;

    @PostMapping("/autenticar-facial")
    public ResponseEntity<Map<String, Object>> autenticar(@RequestBody Map<String, String> payload) {
        String imagemBase64 = payload.get("imagem");
        boolean sucesso = verificarRosto(imagemBase64);

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("sucesso", sucesso);
        return ResponseEntity.ok(resposta);
    }

    private boolean verificarRosto(String imagemBase64) {
        try {
            String url = "https://api-us.faceplusplus.com/facepp/v3/detect";
            String base64Limpo = imagemBase64.replaceFirst("^data:image/[^;]+;base64,", "");

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("api_key", apiKey);
            params.add("api_secret", apiSecret);
            params.add("image_base64", base64Limpo);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            return response.getBody() != null && response.getBody().contains("face_token");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
