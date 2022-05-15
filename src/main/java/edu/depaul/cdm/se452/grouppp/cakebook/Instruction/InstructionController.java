package edu.depaul.cdm.se452.grouppp.cakebook.Instruction;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/instructions")
public class InstructionController {

    @Autowired
    private final InstructionService instructionService;

    public InstructionController(InstructionService instructionService) {
        this.instructionService = instructionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Instruction>> getAllInstructions() {
        return instructionService.getAllInstructions();
    }

    @GetMapping(path="/id/{id}")
    public ResponseEntity<Instruction> getInstructionById(@PathVariable("id") long id) {
        return instructionService.getInstructionById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addInstruction(@RequestBody Instruction instruction) {
        return instructionService.addInstruction(instruction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteInstructionById(@PathVariable("id") long id){
        return instructionService.deleteInstructionById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instruction> updateInstruction(@PathVariable("id") long id, @RequestBody Instruction newInstruction) {
        return instructionService.updateInstruction(id, newInstruction);
    }
}