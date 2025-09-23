package com.example.gestionEntreprise.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.gestionEntreprise.model.Candidate;
import com.example.gestionEntreprise.repository.CandidateRepository;
import com.example.gestionEntreprise.repository.CandidateSkillRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateSkillRepository skillRepo;

    @GetMapping("/cv/list")
    public String listCv(Model model, @RequestParam(required = false) String filterSkill, @RequestParam(required = false) Integer minEducation) {
        List<Candidate> candidates = candidateRepository.findAll();
        if (filterSkill != null) {
            candidates = skillRepo.findCandidatesBySkillNameContaining(filterSkill);
        }
        if (minEducation != null) {
            candidates = candidateRepository.findByEducationLevelGreaterThan(minEducation);
        }
        model.addAttribute("candidates", candidates);
        return "admin/cv_list";
    }
}