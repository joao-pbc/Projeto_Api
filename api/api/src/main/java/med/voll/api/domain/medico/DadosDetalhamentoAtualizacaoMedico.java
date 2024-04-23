package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoAtualizacaoMedico(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        Endereco endereco) {

    public DadosDetalhamentoAtualizacaoMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}
