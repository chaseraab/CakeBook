package edu.depaul.cdm.se452.grouppp.cakebook.Instruction;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class InstructionService {
    @Autowired
    private final InstructionRepository instructionRepository;

    public InstructionService(InstructionRepository instructionRepository) {
        this.instructionRepository = instructionRepository;
    }

    public ResponseEntity<List<Instruction>> getAllInstructions() {
        return new ResponseEntity<>(instructionRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Instruction> getInstructionById(long id) {
        try {
            Optional<Instruction> instructionData = instructionRepository.findById(id);
            if (instructionData.isPresent()) {
                return new ResponseEntity<>(instructionData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addInstruction(Instruction instruction) {
        try {
            Optional<Instruction> searchInstruction = instructionRepository.findById(instruction.getId());
            if (searchInstruction.isPresent()) {
                return new ResponseEntity<>("An instruction with this name already exists.", HttpStatus.CONFLICT);
            }
            instructionRepository.save(instruction);
            return new ResponseEntity<>("Instruction created.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

   
    public ResponseEntity<HttpStatus> deleteInstructionById(long id) {
        try {
            instructionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Instruction> updateInstruction(long id, Instruction newInstruction) {
        Optional<Instruction> searchInstruction = instructionRepository.findById(id);
        if (searchInstruction.isPresent()) {
            Instruction _instruction = searchInstruction.get();
            _instruction.setInstruction(newInstruction.getInstruction());
            _instruction.setRecipe(newInstruction.getRecipe());
            return new ResponseEntity<>(instructionRepository.save(_instruction), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
