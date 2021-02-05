package com.espe.salud.service.paciente;

import com.espe.salud.common.exception.ConflictException;
import com.espe.salud.domain.paciente.entities.Paciente;
import com.espe.salud.dto.paciente.PacienteDTO;
import com.espe.salud.mapper.paciente.PacienteMapper;
import com.espe.salud.persistence.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper mapper;

    @Autowired
    public PacienteServiceImpl(PacienteRepository pacienteRepository, PacienteMapper mapper) {
        this.pacienteRepository = pacienteRepository;
        this.mapper = mapper;
    }


    @Override
    public PacienteDTO save(PacienteDTO paciente) {
        Optional< Paciente > optional = findExisting(paciente);
        if (!optional.isPresent()) {
            Paciente domainObject = toEntity(paciente);
            return toDTO(pacienteRepository.save(domainObject));
        } else {
            throw new ConflictException(String.format("Ya existe un paciente registrada para ese código[%s]", paciente.getId()));
        }
    }

    @Override
    public PacienteDTO update(PacienteDTO paciente) {
        Paciente domainObject = toEntity(paciente);
        return toDTO(pacienteRepository.save(domainObject));
    }

    @Override
    public Optional<Paciente> findExisting(PacienteDTO pacienteDTO) {
        return pacienteRepository.findByCodigo(pacienteDTO.getId());
    }

    @Override
    public Boolean delete(Long id) {
        return pacienteRepository.findById(id).map(object -> {
            pacienteRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Override
    public Optional<PacienteDTO> findByCodigo(Long codigo) {
        return pacienteRepository.findByCodigo(codigo).map(paciente -> mapper.toPacienteDTO(paciente));
    }

    @Override
    public List<PacienteDTO> findAll() {
        List<Paciente> pacientes = new ArrayList<>();
        pacienteRepository.findAll().forEach(pacientes::add);
        return mapper.toPacientesDTO(pacientes);
    }


    @Override
    public PacienteDTO toDTO(Paciente paciente) {
        return mapper.toPacienteDTO(paciente);
    }

    @Override
    public Paciente toEntity(PacienteDTO dto) {
        return mapper.toPaciente(dto);
    }
}

