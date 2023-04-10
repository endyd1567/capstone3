package seventeen.capstone3.model;

import lombok.Data;

import java.util.List;

@Data
public class ResponseData {
    private String id;
    private String object;
    private String model;
    private List<Choice> choices;
}
