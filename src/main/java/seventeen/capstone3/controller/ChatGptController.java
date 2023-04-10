package seventeen.capstone3.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import seventeen.capstone3.model.ResponseData;
import seventeen.capstone3.service.ChatGptService;

@Controller
@Slf4j
public class ChatGptController {

    @Autowired
    ChatGptService chatGptService;

    @RequestMapping("/chatGpt")
    @ResponseBody
    public String chatGpt() throws JsonProcessingException {
        log.info("ok");
        ResponseData responseData = chatGptService.generateText("ChatGPT는 무엇인가요?", 1.0f, 1000);
        return responseData.toString();
    }

}
