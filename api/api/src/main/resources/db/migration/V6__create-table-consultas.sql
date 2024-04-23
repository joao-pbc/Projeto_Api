create table consultas(

    id BIGSERIAL PRIMARY KEY,
    medico_id bigint not null,
    paciente_id bigint not null,
    data TIMESTAMP not null,

    foreign key(medico_id) references medicos(id),
    foreign key(paciente_id) references pacientes(id)
);