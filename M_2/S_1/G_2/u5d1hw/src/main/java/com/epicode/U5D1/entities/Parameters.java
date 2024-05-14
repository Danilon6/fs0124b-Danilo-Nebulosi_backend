package com.epicode.U5D1.entities;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Parameters {

    @Value("${costPerSeat.value}")
    private int costPerSeat;
}
