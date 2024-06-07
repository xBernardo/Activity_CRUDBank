package br.com.facol.bank.bank_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.facol.bank.bank_api.helper.Constants;
import br.com.facol.bank.bank_api.model.ContaEntity;
import br.com.facol.bank.bank_api.service.ContaService;

@RestController
@RequestMapping(path = Constants.API_VERSION + "/contas")
public class ContaController {

    @Autowired
    private ContaService service;

    @PostMapping
    public ResponseEntity<ContaEntity> criarConta(@RequestBody ContaEntity entity) {
        service.criarConta(entity);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<java.util.List<ContaEntity>> contas(){
        java.util.List<ContaEntity> contas = service.listarContas();
        return ResponseEntity.ok(contas);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Void> atualizarConta(@PathVariable("id") String id, @RequestBody ContaEntity entity) throws Exception {
        service.atualizarConta(Long.parseLong(id), entity);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<ContaEntity> buscarContaPorId(@PathVariable("id") String id) throws NumberFormatException, Exception{
        ContaEntity entity = service.buscarConta(Long.parseLong(id));
        return ResponseEntity.ok(entity);
    }

        @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable("id") String id) throws Exception {
        service.deletarConta(Long.parseLong(id));
        return ResponseEntity.noContent().build();
    }

}