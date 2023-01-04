package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car {

    private String make;
    private int numberOfSeats;
    private String type;
  //  private LocalDate dateField;

    //constructor, getters, setters etc.
}
