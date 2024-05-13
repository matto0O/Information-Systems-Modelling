package com.example.ism.controller;

import com.example.api.SkillApi;
import com.example.ism.model.Skill;
import com.example.ism.service.SkillService;
import com.example.model.SkillDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SkillController implements SkillApi {
    @Autowired
    SkillService skillService;

    @Override
    public ResponseEntity<SkillDTO> createSkill(SkillDTO skillDTO) {
        Skill skill = new Skill();
        skill.setName(skillDTO.getName());
        skill.setId(skillDTO.getId());
        skillService.addSkill(skill);

        return new ResponseEntity<>(skillDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteSkill(Integer skillId) {

        if (skillService.deleteSkillById(skillId) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        List<Skill> skills = skillService.findAllSkills();
        List<SkillDTO> skillDTOs = new ArrayList<>();
        skills.forEach(
                skill -> {
                    SkillDTO skillDTO = new SkillDTO();
                    skillDTO.setId(skill.getId());
                    skillDTO.setName(skill.getName());
                    skillDTOs.add(skillDTO);
                }
        );


        return new ResponseEntity<>(skillDTOs, HttpStatus.OK);
    }
}
