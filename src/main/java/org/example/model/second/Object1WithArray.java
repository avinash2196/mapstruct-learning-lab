package org.example.model.second;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.second.Car;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Object1WithArray {
    List<Car> cars;
    String key1;

}
