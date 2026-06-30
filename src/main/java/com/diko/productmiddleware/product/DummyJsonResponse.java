package com.diko.productmiddleware.product;

import lombok.Data;

import java.util.List;

@Data
public class DummyJsonResponse {

    private List<DummyJsonProductDto> products;
}
