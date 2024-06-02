package it.epicode.Events.businesslayer.services.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class EventDTO extends BaseDTO {
    private String title;
    private String description;
    private String place;
    private int maxPrticipants;
}
