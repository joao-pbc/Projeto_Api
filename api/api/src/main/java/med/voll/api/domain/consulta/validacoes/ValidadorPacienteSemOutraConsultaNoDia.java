package med.voll.api.domain.consulta.validacois;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

public class ValidacaoPacienteSemOutraConsultaNoDia {

    private ConsultaRepository repository;
    public void validar(DadosAgendamentoConsulta dados){
        var primeiroHorario = dados.data().withHour(7);
        var ultimoHorario = dados.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
    }
}
