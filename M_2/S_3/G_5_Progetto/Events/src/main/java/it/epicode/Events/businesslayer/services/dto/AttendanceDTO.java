package it.epicode.Events.businesslayer.services.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class AttendanceDTO extends BaseDTO{

    private Long user_id;
    private Long event_id;
}
