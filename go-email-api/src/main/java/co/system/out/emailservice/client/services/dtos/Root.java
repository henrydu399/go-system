package co.system.out.emailservice.client.services.dtos;


import java.util.ArrayList;


import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Root{
    public Sender sender;
    @JsonProperty("to") 
    public ArrayList<To> myto;
    public String subject;
    public String htmlContent;
    
    
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
public static class Sender{
    public String name;
    public String email;
}

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
public static  class To{
    public String email;
    public String name;
}

}
