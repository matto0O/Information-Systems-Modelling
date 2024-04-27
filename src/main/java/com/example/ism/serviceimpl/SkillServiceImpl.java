package com.example.ism.serviceimpl;

import com.example.ism.model.Skill;
import com.example.ism.repository.SkillRepository;
import com.example.ism.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill deleteSkillById(long id) {
        Optional<Skill> c = skillRepository.findById(id);
        if(c.isPresent()){
            skillRepository.deleteById(id);
            return c.get();
        }
        return null;
    }

    @Override
    public List<Skill> findAllSkills() {
        return skillRepository.findAll();
    }
}
