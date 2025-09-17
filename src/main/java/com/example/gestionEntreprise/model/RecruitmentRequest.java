package com.example.gestionEntreprise.model;

import jakarta.persistence.*;

@Entity
public class RecruitmentRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecruitmentRequest;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idProfile")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "idDepartment")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "idNeed_Type")
    private NeedType needType;

    @Column(columnDefinition = "TEXT")
    private String justification;

    @Column(columnDefinition = "TEXT")
    private String mainMission;

    @ManyToOne
    @JoinColumn(name = "idFormation_Experience")
    private FormationExperience formationExperience;

    @ManyToOne
    @JoinColumn(name = "idSkill_Level")
    private SkillLevel skillLevel;

    @ManyToOne
    @JoinColumn(name = "idLanguage_Level")
    private LanguageLevel languageLevel;

    @ManyToOne
    @JoinColumn(name = "idContract_Type")
    private ContractType contractType;

    @Column(length = 50)
    private String workingTime;

    @ManyToOne
    @JoinColumn(name = "idCity")
    private City city;

    @Column(length = 50)
    private String salaryRange;

    @ManyToOne
    @JoinColumn(name = "idStatus")
    private Status status;

    // Getters and setters
    // ...
}
