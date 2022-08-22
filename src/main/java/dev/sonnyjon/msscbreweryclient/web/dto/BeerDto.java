package dev.sonnyjon.msscbreweryclient.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * Created by jt on 2019-04-20.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto
{
    private UUID id;
    private String name;
    private String style;
    private Long upc;
}
