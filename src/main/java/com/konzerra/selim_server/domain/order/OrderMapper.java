package com.konzerra.selim_server.domain.order;

import com.konzerra.selim_server.domain.order.dto.*;
import com.konzerra.selim_server.domain.order.model.Order;
import com.konzerra.selim_server.domain.order.model.OrderHistory;
import com.konzerra.selim_server.domain.security.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring", imports = {LocalDateTime.class})
public abstract class OrderMapper {
    @Autowired
    protected TokenService tokenService;
    public abstract Order dtoToEntity(OrderRequest orderRequest);
    public abstract OrderResponse entityToDto(Order order);
    public abstract OrderDetailsResponse entityToDetailsDto(Order order);
    @Mapping(source = "orderHistory.user.username", target = "admin")
    public abstract OrderHistoryResponse historyEntityToDto(OrderHistory orderHistory);
    public abstract OrderHistory historyDtoToEntity(OrderHistoryRequest orderHistoryRequest);

    @AfterMapping
    protected void setUserAndDate(@MappingTarget OrderHistory orderHistory) {
        orderHistory.setUser(tokenService.getUserFromToken());
        orderHistory.setRecordingDate(LocalDateTime.now());
    }
}
