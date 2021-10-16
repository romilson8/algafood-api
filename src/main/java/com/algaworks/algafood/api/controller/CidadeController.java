package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.model.app.Cidade;
import com.algaworks.algafood.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> listar(){
        List<Cidade> cidades = cidadeService.listar();
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable("id") Long id){
        Cidade cidade = cidadeService.buscar(id);
        if(cidade != null){
            return ResponseEntity.ok(cidade);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cidade> salvar(@RequestBody Cidade cidade) {
        Cidade cidadeSalva = cidadeService.salvar(cidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Cidade> atualizar(@PathVariable("id") Long id, @RequestBody Cidade cidade){
//        Cidade cidadeEncontrada = cidadeService.buscar(id);
//        if(cidadeEncontrada != null){
//            BeanUtils.copyProperties(cidade, cidadeEncontrada, "id");
//            cidadeService.salvar(cidadeEncontrada);
//        }
//    }




}
