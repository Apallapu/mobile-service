package com.hackthon.controller;

import com.hackthon.model.CustomerDTO;
import com.hackthon.model.CustomerEnquiryResponse;
import com.hackthon.model.CustomerResponse;
import com.hackthon.model.CustomerStatus;
import com.hackthon.service.IMobileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Tag(name = "Mobile Application", description = "The Mobile Application API")
@Slf4j
public class MobileApplicationController {

    @Autowired
    private IMobileService mobileService;


    @Operation(summary = "Add a new customer service ", description = "Mobile Application", tags = {"Mobile Application"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200, description = Customer created",
                    content = @Content(schema = @Schema(implementation = CustomerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Customer already exists")})
    @PostMapping(value = "/customer", consumes = {"application/json"})
    public CustomerResponse createCustomer(
            @Parameter(description = "Customer to add. Cannot null or empty.",
                    required = true, schema = @Schema(implementation = CustomerDTO.class))
            @Valid @RequestBody CustomerDTO customerDTO) {
        return this.mobileService.createCustomer(customerDTO);
    }


    /**
     * Find customer details by customerReferenceId.
     *
     * @param customerReferenceId the customerReferenceId
     * @return the list
     */
    @Operation(summary = "Find the Customer status by customer referenceId", description = "Returns Customer status by customer referenceId ", tags = { "Mobile Application" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = CustomerEnquiryResponse.class))),
            @ApiResponse(responseCode = "404", description = "customerReferenceId not found") })
    @GetMapping(value = "/customer/{customerReferenceId}", produces = { "application/json" })
    public CustomerEnquiryResponse findCustomerByCustomerReferenceId(
            @Parameter(description="customerReferenceId of the customer  to be obtained. Cannot be empty.", required=true)
            @PathVariable String customerReferenceId) {
        return mobileService.findCustomerByCustomerReferenceId(customerReferenceId);
    }


    /**
     * Find all customer details for admin users.
     * @return the list
     */
    @Operation(summary = "Find the Customer status by customer referenceId", description = "Returns Customer status by customer referenceId ", tags = { "Mobile Application" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = CustomerDTO.class))),
            @ApiResponse(responseCode = "404", description = "customerReferenceId not found") })
    @GetMapping(value = "/customers", produces = { "application/json" })
    public List<CustomerDTO> findAllCustomers() {
        return mobileService.findAllCustomers();
    }

    /**
     * Find all customer details for admin users.
     * @return the list
     */
    @Operation(summary = "Find the Customer status by customer referenceId", description = "Returns Customer status by customer referenceId ", tags = { "Mobile Application" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation",
                    content = @Content(schema = @Schema(implementation = CustomerStatus.class))),
            @ApiResponse(responseCode = "404", description = "customerReferenceId not found") })
    @PutMapping(value = "/customer/{customerReferenceId}", consumes = {"application/json"})
    public void approveCustomer(@Parameter(description="customerReferenceId of the customer  to be obtained. Cannot be empty.", required=true)
                                                 @PathVariable String customerReferenceId, @Valid @RequestBody CustomerStatus customerStatus ) {
         mobileService.approveCustomer(customerReferenceId,customerStatus);
    }
}
