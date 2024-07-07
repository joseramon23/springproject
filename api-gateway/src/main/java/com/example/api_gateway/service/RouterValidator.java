package com.example.api_gateway.service;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class RouterValidator {
    public static final List<String> endPoints = List.of(
      "/v1/auth",
      "/v1/api/users"
    );

    public Predicate<ServerHttpRequest> isSecured = serverHttpRequest ->
            endPoints.stream()
                    .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
