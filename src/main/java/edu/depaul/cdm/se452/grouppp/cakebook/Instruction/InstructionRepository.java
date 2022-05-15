package edu.depaul.cdm.se452.grouppp.cakebook.Instruction;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructionRepository extends JpaRepository<Instruction, Long>{

    Optional<Instruction> findById(long id);

}