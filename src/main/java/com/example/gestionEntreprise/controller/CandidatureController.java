package com.example.gestionEntreprise.controller;

import com.example.gestionEntreprise.model.*;
import com.example.gestionEntreprise.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Controller
public class CandidatureController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private CandidateSkillRepository candidateSkillRepository;

    @Autowired
    private CandidateLanguageRepository candidateLanguageRepository;

    @Autowired
    private EducationLevelRepository educationLevelRepository;

    @Autowired
    private DegreeRepository degreeRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private LevelRepository levelRepository;

    // Dossier pour stocker les fichiers uploadés
    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/traitement_candidature")
    public String traitementCandidature(
            @RequestParam("photo") MultipartFile photoFile,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("birthDate") String birthDateStr,
            @RequestParam("gender") String gender,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("city") String city,
            @RequestParam("linkedin") String linkedin,
            @RequestParam("maritalStatus") String maritalStatus,
            @RequestParam(value = "driverLicense", required = false) Boolean driverLicense,
            @RequestParam("currentStatus") String currentStatus,
            @RequestParam("educationLevel") Integer educationLevelId,
            @RequestParam("lastDegree") Integer lastDegreeId,
            @RequestParam(value = "salaryMin", required = false) Double salaryMin,
            @RequestParam(value = "salaryMax", required = false) Double salaryMax,
            @RequestParam("companyName") String[] companyNames,
            @RequestParam("position") String[] positions,
            @RequestParam("startDate") String[] startDates,
            @RequestParam("endDate") String[] endDates,
            @RequestParam("jobDescription") String[] jobDescriptions,
            @RequestParam("currentJob") String[] currentJobs,
            @RequestParam("skillId") Integer[] skillIds,
            @RequestParam("skillLevel") Integer[] skillLevels,
            @RequestParam("languageId") Integer[] languageIds,
            @RequestParam("languageLevel") Integer[] languageLevels,
            @RequestParam("additionalInfo") String additionalInfo,
            @RequestParam(value = "cvFile", required = false) MultipartFile cvFile,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        try {
            // Vérifier si l'utilisateur est connecté
            Integer candidateId = (Integer) session.getAttribute("candidateId");
            if (candidateId == null) {
                redirectAttributes.addFlashAttribute("errorMessage", "Veuillez vous connecter d'abord");
                return "redirect:/auth_candidate";
 
            }

            
            // Récupérer le candidat existant
            Candidate candidate = candidateRepository.findById(candidateId)
                    .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

            Person person = candidate.getPerson();

            // Mettre à jour les informations personnelles
            person.setFirstName(firstName);
            person.setLastName(lastName);
            
            if (birthDateStr != null && !birthDateStr.isEmpty()) {
                person.setBirthDate(LocalDate.parse(birthDateStr));
            }
            
            person.setGender(gender);
            person.setPhone(phone);
            person.setCity(city);
            person.setLinkedin(linkedin);
            
            if (maritalStatus != null && !maritalStatus.isEmpty()) {
                person.setMaritalStatus(MaritalStatusType.valueOf(maritalStatus.toUpperCase()));
            }
            
            person.setDriverLicense(driverLicense != null && driverLicense);

            // Gérer l'upload de la photo
            if (photoFile != null && !photoFile.isEmpty()) {
                String photoPath = saveFile(photoFile, "photos");
                person.setPhotoPath(photoPath);
            }

            person = personRepository.save(person);

            // Mettre à jour les informations du candidat
            candidate.setCurrentStatus(CandidateStatusType.valueOf(currentStatus.toUpperCase()));
            
            if (educationLevelId != null) {
                EducationLevel educationLevel = educationLevelRepository.findById(educationLevelId)
                        .orElseThrow(() -> new RuntimeException("Niveau d'éducation non trouvé"));
                candidate.setEducationLevel(educationLevel);
            }
            
            if (lastDegreeId != null) {
                Degree lastDegree = degreeRepository.findById(lastDegreeId)
                        .orElseThrow(() -> new RuntimeException("Diplôme non trouvé"));
                candidate.setLastDegree(lastDegree);
            }
            
            if (salaryMin != null) {
                candidate.setExpectedSalaryMin(java.math.BigDecimal.valueOf(salaryMin));
            }
            
            if (salaryMax != null) {
                candidate.setExpectedSalaryMax(java.math.BigDecimal.valueOf(salaryMax));
            }
            
            candidate.setAdditionalInfo(additionalInfo);
            candidate = candidateRepository.save(candidate);

            // Gérer les expériences professionnelles
            saveExperiences(candidate, companyNames, positions, startDates, endDates, jobDescriptions, currentJobs);

            // Gérer les compétences
            saveSkills(candidate, skillIds, skillLevels);

            // Gérer les langues
            saveLanguages(candidate, languageIds, languageLevels);

            // Gérer l'upload du CV
            if (cvFile != null && !cvFile.isEmpty()) {
                String cvPath = saveFile(cvFile, "cvs");
                // Vous pouvez stocker le chemin du CV dans la table Candidate ou une table dédiée
            }

            // Mettre à jour la session avec les nouvelles informations
            session.setAttribute("candidateName", person.getFirstName() + " " + person.getLastName());
            session.setAttribute("candidateEmail", email);
            if (person.getPhotoPath() != null) {
                session.setAttribute("candidatePhoto", person.getPhotoPath());
            }

            // Vérifier l'automatisation selon le profil recherché
            boolean shouldRedirectToTest = checkAutomationRules(candidate);
            
            if (shouldRedirectToTest) {
                redirectAttributes.addFlashAttribute("successMessage", "Votre candidature a été enregistrée avec succès! Vous allez être redirigé vers le test.");
                return "redirect:/test/qcm";
            } else {
                redirectAttributes.addFlashAttribute("successMessage", "Votre candidature a été enregistrée avec succès! Nous vous contacterons prochainement.");
                return "redirect:/candidate";
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erreur lors de l'enregistrement: " + e.getMessage());
            return "redirect:/demandeCandidature";
        }
    }

    private String saveFile(MultipartFile file, String subfolder) throws IOException {
        // Créer le dossier s'il n'existe pas
        Path uploadPath = Paths.get(UPLOAD_DIR + subfolder);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Générer un nom de fichier unique
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        return "/" + UPLOAD_DIR + subfolder + "/" + fileName;
    }

    private void saveExperiences(Candidate candidate, String[] companyNames, String[] positions, 
                               String[] startDates, String[] endDates, String[] jobDescriptions, 
                               String[] currentJobs) {
        // Supprimer les expériences existantes
        experienceRepository.deleteByCandidateIdCandidate(candidate.getIdCandidate());

        // Ajouter les nouvelles expériences
        for (int i = 0; i < companyNames.length; i++) {
            Experience experience = new Experience();
            experience.setCandidate(candidate);
            experience.setCompanyName(companyNames[i]);
            experience.setPosition(positions[i]);
            experience.setStartDate(LocalDate.parse(startDates[i]));
            
            if (currentJobs[i] != null && Boolean.parseBoolean(currentJobs[i])) {
                experience.setEndDate(null); // Poste actuel
            } else if (endDates[i] != null && !endDates[i].isEmpty()) {
                experience.setEndDate(LocalDate.parse(endDates[i]));
            }
            
            experience.setDescription(jobDescriptions[i]);
            experienceRepository.save(experience);
        }
    }

    private void saveSkills(Candidate candidate, Integer[] skillIds, Integer[] skillLevels) {
        // Supprimer les compétences existantes
        candidateSkillRepository.deleteByCandidateIdCandidate(candidate.getIdCandidate());

        // Ajouter les nouvelles compétences
        for (int i = 0; i < skillIds.length; i++) {
            CandidateSkill candidateSkill = new CandidateSkill();
            candidateSkill.setCandidate(candidate);
            
            Skill skill = skillRepository.findById(skillIds[i])
                    .orElseThrow(() -> new RuntimeException("Compétence non trouvée"));
            candidateSkill.setSkill(skill);
            
            Level level = levelRepository.findById(skillLevels[i])
                    .orElseThrow(() -> new RuntimeException("Niveau non trouvé"));
            candidateSkill.setLevel(level);
            
            candidateSkillRepository.save(candidateSkill);
        }
    }

    private void saveLanguages(Candidate candidate, Integer[] languageIds, Integer[] languageLevels) {
        // Supprimer les langues existantes
        candidateLanguageRepository.deleteByCandidateIdCandidate(candidate.getIdCandidate());

        // Ajouter les nouvelles langues
        for (int i = 0; i < languageIds.length; i++) {
            CandidateLanguage candidateLanguage = new CandidateLanguage();
            candidateLanguage.setCandidate(candidate);
            
            Language language = languageRepository.findById(languageIds[i])
                    .orElseThrow(() -> new RuntimeException("Langue non trouvée"));
            candidateLanguage.setLanguage(language);
            
            Level level = levelRepository.findById(languageLevels[i])
                    .orElseThrow(() -> new RuntimeException("Niveau non trouvé"));
            candidateLanguage.setLevel(level);
            
            candidateLanguageRepository.save(candidateLanguage);
        }
    }

    private boolean checkAutomationRules(Candidate candidate) {
        // Convertir l'ID du candidat en Long
        Integer candidateId = candidate.getIdCandidate();
        
        // Implémenter la logique d'automatisation selon les besoins en personnel
        // Cette méthode devrait vérifier si le candidat correspond aux profils recherchés
        
        // Exemple de règles simples :
        // 1. Vérifier les compétences techniques
        boolean hasTechnicalSkills = candidateSkillRepository.findByCandidateIdCandidate(candidateId)
                .stream()
                .anyMatch(cs -> {
                    String skillName = cs.getSkill().getSkillName().toLowerCase();
                    return skillName.contains("java") || skillName.contains("python") || 
                           skillName.contains("javascript") || skillName.contains("spring");
                });
        
        // 2. Vérifier le niveau d'éducation
        boolean hasHigherEducation = candidate.getEducationLevel() != null && 
                                   candidate.getEducationLevel().getIdEducationLevel() >= 3; // Bac+3 minimum
        
        // 3. Vérifier les langues
        boolean hasEnglish = candidateLanguageRepository.findByCandidateIdCandidate(candidateId)
                .stream()
                .anyMatch(cl -> cl.getLanguage().getLanguageName().equalsIgnoreCase("Anglais") && 
                              cl.getLevel().getIdLevel() >= 2); // Niveau intermédiaire minimum
        
        return hasTechnicalSkills && hasHigherEducation && hasEnglish;
    }
}