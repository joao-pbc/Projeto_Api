package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


//Mapeador de requests para o diretório /hello
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    //Ao receber uma solicitação do tipo POST, com dados da requisição
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder){
        var medico = new Medico(dados);

        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAtualizacaoMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size=10, sort={"nome"}) Pageable paginacao){
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoAtualizacaoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.desativarConta();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity detalharMedico(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoAtualizacaoMedico(medico));
    }


}

// a anotação @RequestBody faz com que a requisição venha e seja jogada dentro dos parâmetros
// Para trabalhar com os campos recebidos pelo individualmente, será necessário criar uma classe com todos os itens dentro do post
// foi criado um record com todos os dados da requisição sendo eles:
        /*
 {
    "nome": "Fulano de Tal",
    "email": "fulanotal@email.com.br",
    "telefone": "47990904545",
    "crm": "123456",
    "especialidade": "ORTOPEDIA",
    "endereco": {
        "logradouro": "rua 1",
        "bairro": "bairro",
        "cep": "12345678",
        "cidade": "Brasilia",
        "uf": "DF",
        "numero": "1",
        "complemento": "complemento"
    }
}
 */


