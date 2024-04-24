package med.voll.api.domain.consulta.validacois;


import med.voll.api.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.paciente.PacienteRepository;


public class ValidadorPacienteAtivo {

    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados){

       var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
       if(!pacienteEstaAtivo){
           throw new ValidacaoException("Consulta não pode ser agendada com paciente exluido");
       }

    }
}
