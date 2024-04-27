package com.example.ism.service;

import com.example.ism.model.Skill;

import java.util.List;

public interface SkillService {
    public Skill addSkill(Skill skill);
    public Skill deleteSkillById(long id);
    public List<Skill> findAllSkills();
}
