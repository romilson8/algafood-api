package com.algaworks.algafood.models;

import com.algaworks.algafood.domain.model.app.Estado;

public class EstadoData {

    public static Estado estadoMock() {
        return Estado
                .builder()
                .nome("Pernambuco")
                .build();
    }
}
