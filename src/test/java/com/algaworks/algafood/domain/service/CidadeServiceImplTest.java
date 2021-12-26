package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.app.Cidade;
import com.algaworks.algafood.domain.model.app.repository.CidadeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.algaworks.algafood.models.CidadeData.cidadeMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

// duas formas de fazer o mockito funcionar (com a anotation @ExtendWith(MockitoExtension.class) e com MockitoAnnotations.initMocks(this) dentro @BeforeEach)
//@ExtendWith(MockitoExtension.class)
class CidadeServiceImplTest {

    @InjectMocks
    CidadeServiceImpl cidadeService;

    @Mock
    CidadeRepository cidadeRepository;

    final Long ID_UM = 1L;
    final Long ID_DOIS = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deveListarTodasCidades() {
        List<Cidade> listaCidades = new ArrayList<>();
        listaCidades.add(cidadeMock());
        when(cidadeRepository.findAll()).thenReturn(listaCidades);
        List<Cidade> cidadeList = cidadeService.listar();
        assertEquals(cidadeMock().getNome(), cidadeList.get(0).getNome());
    }

    @Test
    void deveBuscarCidadePorIdComSucesso() {

        when(cidadeRepository.findById(anyLong())).thenReturn(Optional.of(cidadeMock()));
        var cidade = cidadeService.buscar(ID_UM);
        assertEquals(cidadeMock().getNome(), cidade.getNome());
    }

    @Test
    void deveBuscarCidadePorIdException() {
        var exception = assertThrows(IllegalArgumentException.class, () ->
                cidadeService.buscar(ID_DOIS));
        assertEquals(" Objeto não encontrado ", exception.getMessage());
    }

    @Test
    void devSalvarCidade() {
        when(cidadeRepository.save(any())).thenReturn(cidadeMock());
        Cidade cidade = cidadeService.salvar(cidadeMock());
        assertEquals(cidadeMock(), cidade);
    }

    @Test
    void deveRemoverCidadeSucesso() {
        when(cidadeRepository.findById(anyLong())).thenReturn(Optional.of(cidadeMock()));
        doNothing().when(cidadeRepository).delete(cidadeMock());
        cidadeService.remover(ID_UM);
        verify(cidadeRepository, times(1)).delete(any());
    }

    @Test
    void atualizar() {
        when(cidadeRepository.findById(anyLong())).thenReturn(Optional.of(cidadeMock()));
        when(cidadeRepository.save(any())).thenReturn(cidadeMock());
        Cidade cidade = cidadeService.atualizar(ID_UM, cidadeMock());
        assertEquals(cidadeMock(), cidade);
    }

    @Test
    void atualizarException() {
        when(cidadeRepository.findById(anyLong())).thenReturn(null);
        var exception = assertThrows(EntidadeNaoEncontradaException.class, () ->
                cidadeService.atualizar(ID_DOIS, cidadeMock()));
        assertEquals("Cidade com codigo 1 não encontrado na base", exception.getMessage());
    }

}