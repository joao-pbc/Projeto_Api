package med.voll.api.domain.consulta.validacois;

import med.voll.api.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {

    ConsultaRepository repository;

    public void validar(DadosAgendamentoConsulta dados){

        var medicoPossuiOutraConsultaNoMesmoHorario = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());
        if(medicoPossuiOutraConsultaNoMesmoHorario)
            throw new ValidacaoException("Medico ja possui outra consulta agendada nesse mesmo horario");

    }

}
