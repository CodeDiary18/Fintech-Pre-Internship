package com.example.finedu.dto;

import com.example.finedu.entity.FredData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Observation {
    String date;
    Double value;

    // Entity Converter
    public static Observation fromFredData(FredData fredData) {
        return new Observation(fredData.getObservationDate(), fredData.getValue());
    }
}