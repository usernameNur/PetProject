package antities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomResponse {


    private String category_title;
    private String category_id;
    private String product_id;
    private String created;
    private String category_description;
    private String product_title;
    List<CustomResponse> responses;
    private int seller_id;
    private String seller_name;
    private String company_name;
    private String email;

}
