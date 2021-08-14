package com.hackthon.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CustomerEnquiryResponse {

    @Schema(description = "customerReferenceId",
            example = "Cust123444", required = false)
    private String customerReferenceId;

    @Schema(description = "status",
            example = "status", required = false)
    private String status;

    @Schema(description = "connetion",
            example = "true", required = false)
    private Boolean isConnection;

}
