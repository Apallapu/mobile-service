package com.hackthon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ContactDTO {

    @Schema(description = "mobileNumber",
            example = "mobileNumber", required = false)
    private String mobileNumber;

    @Schema(description = "type",
            example = "type", required = false)
    private String type;

}
