package com.monitorexample.hashedwheeltimerdemo.controller;

import com.monitorexample.hashedwheeltimerdemo.entity.dto.WorkDto;
import com.monitorexample.hashedwheeltimerdemo.requst.WorkRequest;
import com.monitorexample.hashedwheeltimerdemo.service.WorkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/work-monitor-service/works")
public class WorkController {

    private final WorkService workService;

    @Operation(summary = "Start a Work", operationId = "startWork", description = "This service is to start a Work", tags = "WorkController",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = WorkRequest.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = WorkDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request (Invalid syntax)"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    @PostMapping(path = "/start", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WorkDto> startWork(@Valid @RequestBody WorkRequest workRequest) {
        return this.workService.startTheWork(workRequest);
    }

    @Operation(summary = "End a Work", operationId = "endWork", description = "This service is to end a Work by its' id", tags = "WorkController",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = WorkDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request (Invalid syntax)"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    @PatchMapping(path = "/end/{workId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WorkDto> endWork(@PathVariable long workId) {
        return this.workService.endTheWorkById(workId);
    }

    @Operation(summary = "Get a Work", operationId = "getWorkById", description = "This service is to retrieve details of a Work by its' id", tags = "WorkController",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = WorkDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request (Invalid syntax)"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    @GetMapping(path = "/{workId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<WorkDto> getWorkById(@PathVariable long workId) {
        return this.workService.getWorkById(workId);
    }

    @Operation(summary = "List all Works", operationId = "listAllWorks", description = "This service is to retrieve details of a Work by its' id", tags = "WorkController",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = WorkDto.class)))),
                    @ApiResponse(responseCode = "400", description = "Bad Request (Invalid syntax)"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<WorkDto> listAllWorks() {
        return this.workService.findAllWork();
    }

}
