package org.example.model.second;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {

    private String make;
    private int seatCount;
    private String type;
   // private String dateField;

    //constructor, getters, setters etc.
}
