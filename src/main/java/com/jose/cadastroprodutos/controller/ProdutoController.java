package com.jose.cadastroprodutos.controller;

import com.jose.cadastroprodutos.model.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ProdutoController {
    @GetMapping("/teste")
    String teste(){
        return "Testando API";
    }
    private List<Produto> produtos = new ArrayList<Produto>();
    public  ProdutoController(){
        produtos.addAll(
                List.of(
                        new Produto(1,"PS5", 3500, "Playstation 5 - Cosole video game"),
                        new Produto(2,"Memoria ram 8 hyperx", 130, "3500 GHZ")
                )
        );
    }
    @GetMapping("/produtos")
    Iterable<Produto> getProdutos() {
        return produtos;
    }
    @GetMapping("/produtos/{id}")
    Optional<Produto> getProdutosPorId(@PathVariable int id) {
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return Optional.of(produto);
            }
        }
        return Optional.empty();
    }
    @PostMapping("/produtos")
    Produto postProduto(@RequestBody Produto produto) {
        produtos.add(produto);
        return produto;
    }
    @PutMapping("/produtos/{id}")
    Produto putProduto(@PathVariable int id, @RequestBody Produto produto) {
        int produtoIndex = -1;
        for (Produto a : produtos) {
            if (a.getId() == id){
                produtoIndex = produtos.indexOf(a);
                produtos.set(produtoIndex, produto);
            }
        }
        return (produtoIndex == -1) ? postProduto(produto) : produto;
    }
    @DeleteMapping("/produtos/{id}")
    void deleteProduto(@PathVariable int id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }
}
