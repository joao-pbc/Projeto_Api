package med.voll.api.domain.consulta.validacois;

import med.voll.api.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;

public class ValidadorMedicoAtivo {

    MedicoRepository repository;

    public void validar (DadosAgendamentoConsulta dados){

        if( dados.idMedico() == null){
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());

        if (!medicoEstaAtivo){
            throw new ValidacaoException("Consulta não pode ser agendada com medico excluido");
        }


    }

}
