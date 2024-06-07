package br.com.facol.bank.bank_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.facol.bank.bank_api.model.ContaEntity;
import br.com.facol.bank.bank_api.repository.ContaRepository;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public void criarConta(ContaEntity contaEntity) {
        ContaEntity contaSalva = contaRepository.save(contaEntity);
    }

    public List<ContaEntity> listarContas() {
        return contaRepository.findAll();
    }

    public void atualizarConta(Long id, ContaEntity entity) throws Exception {
        ContaEntity conta = buscarConta(id);
        conta.setNumeroConta(entity.getNumeroConta());
        contaRepository.save(conta);
    }

    public ContaEntity buscarConta(Long id) throws Exception {
        return contaRepository.findById(id)
                .orElseThrow(() -> new Exception("Conta nao encontrada"));
    }

    public void deletarConta(Long id) throws Exception {
        ContaEntity conta = buscarConta(id);
        contaRepository.delete(conta);
    }

}
