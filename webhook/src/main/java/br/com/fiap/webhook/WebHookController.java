package br.com.fiap.webhook;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class WebHookController{

    private final List<String> urlsRegistradas = new ArrayList<>();

    //Endpoint para registrar a URL da aplicação que enviará as requisições
    @PostMapping("/registra_webhook")
    public ResponseEntity<String> registrarWebHook(@RequestBody Map<String, String> requisicao){
        String url = requisicao.get("url");
        if (url == null || url.isEmpty()){
            return new ResponseEntity<>("URL é necessária.", HttpStatus.BAD_REQUEST);
        }
        urlsRegistradas.add(url);
        System.out.println("WEBHOOK CADASTRADO COM SUCESSO");
        return new ResponseEntity<>("WebHook registrado com sucesso", HttpStatus.OK);
    }

    //Endpoint para simular o recebimento de uma requisição de validação do pagamento
    @PostMapping("/validacao_pagamento_webhook")
    public ResponseEntity<Map<String, String>> validaPagamentoWebHook(@RequestBody Map<String, Object> payload){
        String status = new Random().nextBoolean() ? "aprovado" : "recusado";

        Map<String, String> response = Map.of("status", status);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
