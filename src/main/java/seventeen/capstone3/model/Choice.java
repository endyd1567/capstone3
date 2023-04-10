package seventeen.capstone3.model;

import lombok.Data;

@Data
public class Choice {
    private String text;
    private int index;
    private String finish_reason;

}
