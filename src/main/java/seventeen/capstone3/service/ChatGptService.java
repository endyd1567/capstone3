package seventeen.capstone3.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import seventeen.capstone3.model.KakaoProfile;
import seventeen.capstone3.model.ResponseData;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatGptService {



    private String API_KEY = "";
    private static final String ENDPOINT = "https://api.openai.com/v1/completions";


    public ResponseData generateText(String prompt, float temperature, int maxTokens) {

        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-type", "application/json;charset=utf-8");
        headers.set("Authorization", "Bearer " + API_KEY);

        //HttpBody 오브젝트 생성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "text-davinci-003");
        requestBody.put("prompt", prompt);
        requestBody.put("temperature", temperature);
        requestBody.put("max_tokens", maxTokens);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        RestTemplate rt = new RestTemplate();

        //Http POST 방식 요청 - response 변수 응답
        ResponseEntity<String> response = rt.exchange(
                ENDPOINT,   // 사용자 정보 요청 주소
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        ObjectMapper objectMapper = new ObjectMapper();
        ResponseData responseData = null;
        try {
            responseData = objectMapper.readValue(response.getBody(), ResponseData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return responseData;
    }
}
