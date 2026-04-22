package com.orderservice.controller.docs;

import com.orderservice.model.Order;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Orders", description = "API for managing customer orders")
public interface OrderControllerDocs {

    @Operation(
            summary = "Get all orders",
            description = "Returns a list of all orders in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of orders retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Order.class))
                    )
            )
    })
    List<Order> getAll();

    @Operation(
            summary = "Get order by ID",
            description = "Returns the order identified by the given ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order with the given ID was not found",
                    content = @Content
            )
    })
    ResponseEntity<Order> getById(
            @Parameter(description = "Order identifier", required = true, example = "1001")
            Long id
    );

    @Operation(
            summary = "Create a new order",
            description = "Creates a new order from the provided payload and returns the created entity."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request payload",
                    content = @Content
            )
    })
    Order create(
            @RequestBody(
                    description = "Order data to create",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)
                    )
            )
            Order order
    );

    @Operation(
            summary = "Update an existing order",
            description = "Updates the order identified by the given ID with the provided payload."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order with the given ID was not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid request payload",
                    content = @Content
            )
    })
    ResponseEntity<Order> update(
            @Parameter(description = "Identifier of the order to update", required = true, example = "1001")
            Long id,
            @RequestBody(
                    description = "New order data",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)
                    )
            )
            Order order
    );

    @Operation(
            summary = "Delete an order",
            description = "Deletes the order identified by the given ID."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Order deleted successfully",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order with the given ID was not found",
                    content = @Content
            )
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "Identifier of the order to delete", required = true, example = "1001")
            Long id
    );
}
