package com.hackthon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CustomerResponse {

    @Schema(description = "customerReferenceId",
            example = "Cust123444", required = false)
    private String customerReferenceId;

}
