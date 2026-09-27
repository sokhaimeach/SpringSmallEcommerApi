package org.backend.smallecommerceapi.mapper;

import org.backend.smallecommerceapi.dto.order.OrderDetailResponse;
import org.backend.smallecommerceapi.dto.order.OrderResponse;
import org.backend.smallecommerceapi.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toResponse(Order order);

    @Mapping(target = "items", source = "orderItems")
    @Mapping(target = "payments", source = "payments")
    OrderDetailResponse toDetailResponse(Order order);
}
