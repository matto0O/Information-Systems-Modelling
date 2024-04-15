package com.example.ism;

import com.example.api.SkillApi;
import com.example.model.Skill;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SkillController implements SkillApi {

    @Override
    public ResponseEntity<Skill> createSkill(Skill skill) {
        return SkillApi.super.createSkill(skill);
    }

    @Override
    public ResponseEntity<Void> deleteSkill(Integer skillId) {
        return SkillApi.super.deleteSkill(skillId);
    }

    @Override
    public ResponseEntity<List<Skill>> getAllSkills() {
        return SkillApi.super.getAllSkills();
    }
}
