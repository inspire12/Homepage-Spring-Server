package com.inspire12.homepage.model.message;


import lombok.Data;
import lombok.Setter;

@Data(staticConstructor = "of")
@Setter
public class EmailMsg {
    String valid;
}
