package com.example.gestionEntreprise.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.gestionEntreprise.dto.CandidatureForm;
import com.example.gestionEntreprise.model.Candidate;
import com.example.gestionEntreprise.model.CandidateLanguage;
import com.example.gestionEntreprise.model.CandidateSkill;
import com.example.gestionEntreprise.model.CandidateStatusType;
import com.example.gestionEntreprise.model.Degree;
import com.example.gestionEntreprise.model.EducationLevel;
import com.example.gestionEntreprise.model.Experience;
import com.example.gestionEntreprise.model.Language;
import com.example.gestionEntreprise.model.Level;
import com.example.gestionEntreprise.model.MaritalStatusType;
import com.example.gestionEntreprise.model.Person;
import com.example.gestionEntreprise.model.Skill;
import com.example.gestionEntreprise.repository.CandidateLanguageRepository;
import com.example.gestionEntreprise.repository.CandidateRepository;
import com.example.gestionEntreprise.repository.CandidateSkillRepository;
import com.example.gestionEntreprise.repository.DegreeRepository;
import com.example.gestionEntreprise.repository.EducationLevelRepository;
import com.example.gestionEntreprise.repository.ExperienceRepository;
import com.example.gestionEntreprise.repository.LanguageRepository;
import com.example.gestionEntreprise.repository.LevelRepository;
import com.example.gestionEntreprise.repository.PersonRepository;
import com.example.gestionEntreprise.repository.SkillRepository;
import com.example.gestionEntreprise.repository.MatchingCandidateProjection;

import jakarta.servlet.http.HttpSession;

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
        @ModelAttribute CandidatureForm form,
        HttpSession session,
        RedirectAttributes redirectAttributes) {

    try {
         System.out.println("=== DÉBUT TRAITEMENT CANDIDATURE ===");
        System.out.println("Prénom: " + form.getFirstName());
        System.out.println("Nombre d'expériences: " + (form.getCompanyName() != null ? form.getCompanyName().size() : 0));
        System.out.println("Nombre de compétences: " + (form.getSkillId() != null ? form.getSkillId().size() : 0));
        // Vérifier si l'utilisateur est connecté
        Integer candidateId = (Integer) session.getAttribute("candidateId");
        System.out.println("Candidate ID from session: " + candidateId);

        if (candidateId == null) {
             System.out.println("Aucun candidat connecté - redirection");
            redirectAttributes.addFlashAttribute("errorMessage", "Veuillez vous connecter d'abord");
            return "redirect:/auth_candidate";
        }

        // Récupérer le candidat existant
        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        Person person = candidate.getPerson();

        // Mettre à jour les informations personnelles
        person.setFirstName(form.getFirstName());
        person.setLastName(form.getLastName());
        
        if (form.getBirthDate() != null && !form.getBirthDate().isEmpty()) {
            person.setBirthDate(LocalDate.parse(form.getBirthDate()));
        }
        
        person.setGender(form.getGender());
        person.setPhone(form.getPhone());
        person.setCity(form.getCity());
        person.setLinkedin(form.getLinkedin());
        
        if (form.getMaritalStatus() != null && !form.getMaritalStatus().isEmpty()) {
            person.setMaritalStatus(MaritalStatusType.valueOf(form.getMaritalStatus().toLowerCase()));
        }
        
        person.setDriverLicense(form.getDriverLicense() != null && form.getDriverLicense());

        // Gérer l'upload de la photo
        if (form.getPhoto() != null && !form.getPhoto().isEmpty()) {
            String photoPath = saveFile(form.getPhoto(), "photos");
            person.setPhotoPath(photoPath);
        }

        person = personRepository.save(person);

        // Mettre à jour les informations du candidat
        candidate.setCurrentStatus(CandidateStatusType.valueOf(form.getCurrentStatus().toUpperCase()));
        
        if (form.getEducationLevel() != null) {
            EducationLevel educationLevel = educationLevelRepository.findById(form.getEducationLevel())
                    .orElseThrow(() -> new RuntimeException("Niveau d'éducation non trouvé"));
            candidate.setEducationLevel(educationLevel);
        }
        
        if (form.getLastDegree() != null) {
            Degree lastDegree = degreeRepository.findById(form.getLastDegree())
                    .orElseThrow(() -> new RuntimeException("Diplôme non trouvé"));
            candidate.setLastDegree(lastDegree);
        }
        
        if (form.getSalaryMin() != null) {
            candidate.setExpectedSalaryMin(java.math.BigDecimal.valueOf(form.getSalaryMin()));
        }
        
        if (form.getSalaryMax() != null) {
            candidate.setExpectedSalaryMax(java.math.BigDecimal.valueOf(form.getSalaryMax()));
        }
        
        candidate.setAdditionalInfo(form.getAdditionalInfo());
        candidate = candidateRepository.save(candidate);

        // Gérer les expériences professionnelles
        saveExperiences(candidate, form);

        // Gérer les compétences
        saveSkills(candidate, form);

        // Gérer les langues
        saveLanguages(candidate, form);

        // Mettre à jour la session avec les nouvelles informations
        session.setAttribute("candidateName", person.getFirstName() + " " + person.getLastName());
        session.setAttribute("candidateEmail", form.getEmail());
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
        e.printStackTrace(); // Pour logger l'erreur complète dans le terminal
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

    private void saveExperiences(Candidate candidate, CandidatureForm form) {
    // Supprimer les expériences existantes
    experienceRepository.deleteByCandidateIdCandidate(candidate.getIdCandidate());

    // Vérifier si des expériences ont été fournies
    if (form.getCompanyName() == null || form.getCompanyName().isEmpty()) {
        return;
    }

    // Ajouter les nouvelles expériences
    for (int i = 0; i < form.getCompanyName().size(); i++) {
        // Vérifier que tous les champs requis sont présents
        if (form.getCompanyName().get(i) == null || form.getPosition().get(i) == null || form.getStartDate().get(i) == null) {
            continue; // Ignorer les expériences incomplètes
        }
        
        Experience experience = new Experience();
        experience.setCandidate(candidate);
        experience.setCompanyName(form.getCompanyName().get(i));
        experience.setPosition(form.getPosition().get(i));
        experience.setStartDate(LocalDate.parse(form.getStartDate().get(i)));
        
        if (form.getCurrentJob() != null && i < form.getCurrentJob().size() && form.getCurrentJob().get(i) != null && 
            Boolean.parseBoolean(form.getCurrentJob().get(i))) {
            experience.setEndDate(null); // Poste actuel
        } else if (form.getEndDate() != null && i < form.getEndDate().size() && form.getEndDate().get(i) != null && 
                   !form.getEndDate().get(i).isEmpty()) {
            experience.setEndDate(LocalDate.parse(form.getEndDate().get(i)));
        }
        
        if (form.getJobDescription() != null && i < form.getJobDescription().size()) {
            experience.setDescription(form.getJobDescription().get(i));
        }
        
        experienceRepository.save(experience);
    }
}


    private void saveSkills(Candidate candidate, CandidatureForm form) {
    // Supprimer les compétences existantes
    candidateSkillRepository.deleteByCandidateIdCandidate(candidate.getIdCandidate());

    // Vérifier si des compétences ont été fournies
    if (form.getSkillId() == null || form.getSkillId().isEmpty()) {
        return;
    }

    // Ajouter les nouvelles compétences
    for (int i = 0; i < form.getSkillId().size(); i++) {
        if (form.getSkillId().get(i) == null || form.getSkillLevel().get(i) == null) {
            continue; // Ignorer les compétences incomplètes
        }
        
        CandidateSkill candidateSkill = new CandidateSkill();
        candidateSkill.setCandidate(candidate);
        
        Skill skill = skillRepository.findById(form.getSkillId().get(i))
                .orElseThrow(() -> new RuntimeException("Compétence non trouvée"));
        candidateSkill.setSkill(skill);
        
        Level level = levelRepository.findById(form.getSkillLevel().get(i))
                .orElseThrow(() -> new RuntimeException("Niveau non trouvé"));
        candidateSkill.setLevel(level);
        
        candidateSkillRepository.save(candidateSkill);
    }
}


    private void saveLanguages(Candidate candidate, CandidatureForm form) {
    // Supprimer les langues existantes
    candidateLanguageRepository.deleteByCandidateIdCandidate(candidate.getIdCandidate());

    // Vérifier si des langues ont été fournies
    if (form.getLanguageId() == null || form.getLanguageId().isEmpty()) {
        return;
    }

    // Ajouter les nouvelles langues
    for (int i = 0; i < form.getLanguageId().size(); i++) {
        if (form.getLanguageId().get(i) == null || form.getLanguageLevel().get(i) == null) {
            continue; // Ignorer les langues incomplètes
        }
        
        CandidateLanguage candidateLanguage = new CandidateLanguage();
        candidateLanguage.setCandidate(candidate);
        
        Language language = languageRepository.findById(form.getLanguageId().get(i))
                .orElseThrow(() -> new RuntimeException("Langue non trouvée"));
        candidateLanguage.setLanguage(language);
        
        Level level = levelRepository.findById(form.getLanguageLevel().get(i))
                .orElseThrow(() -> new RuntimeException("Niveau non trouvé"));
        candidateLanguage.setLevel(level);
        
        candidateLanguageRepository.save(candidateLanguage);
    }
}

   private boolean checkAutomationRules(Candidate candidate) {
    try {
        // Assumez un request actif (id=1 pour test). Plus tard, query le dernier published
        Integer activeRequestId = 1;  // Ou autowire RecruitmentRequestRepository et findByStatus("published")
        
        List<MatchingCandidateProjection> matching = candidateRepository.findMatchingCandidates(activeRequestId);
        
        // Check si ce candidate est dans les matching (triage auto basé sur critères admin)
        return matching.stream()
            .anyMatch(m -> m.getIdCandidate().equals(candidate.getIdCandidate()));
        
    } catch (Exception e) {
        e.printStackTrace(); // Pour logger l'erreur complète dans le terminal
        return false;  // Si erreur (ex: pas de request), ne passe pas
    }
}


private boolean checkTechnicalSkills(Integer candidateId) {
    List<CandidateSkill> skills = candidateSkillRepository.findByCandidateIdCandidate(candidateId);
    if (skills == null || skills.isEmpty()) {
        return false;
    }
    
    return skills.stream()
            .filter(cs -> cs.getSkill() != null && cs.getSkill().getSkillName() != null)
            .anyMatch(cs -> {
                String skillName = cs.getSkill().getSkillName().toLowerCase();
                return skillName.contains("java") || skillName.contains("python") || 
                       skillName.contains("javascript") || skillName.contains("spring");
            });
}

private boolean checkEducationLevel(Candidate candidate) {
    return candidate.getEducationLevel() != null && 
           candidate.getEducationLevel().getIdEducationLevel() != null &&
           candidate.getEducationLevel().getIdEducationLevel() >= 3;
}

private boolean checkLanguageSkills(Integer candidateId) {
    List<CandidateLanguage> languages = candidateLanguageRepository.findByCandidateIdCandidate(candidateId);
    if (languages == null || languages.isEmpty()) {
        return false;
    }
    
    return languages.stream()
            .filter(cl -> cl.getLanguage() != null && cl.getLevel() != null)
            .anyMatch(cl -> 
                "Anglais".equalsIgnoreCase(cl.getLanguage().getLanguageName()) && 
                cl.getLevel().getIdLevel() != null &&
                cl.getLevel().getIdLevel() >= 2
            );
}

@PostMapping("/testForm")
@ResponseBody
public String testForm(@ModelAttribute CandidatureForm form) {
    StringBuilder result = new StringBuilder();
    result.append("FirstName: ").append(form.getFirstName()).append("<br>");
    result.append("Nombre d'expériences: ").append(form.getCompanyName() != null ? form.getCompanyName().size() : 0).append("<br>");
    result.append("Nombre de compétences: ").append(form.getSkillId() != null ? form.getSkillId().size() : 0).append("<br>");
    result.append("Nombre de langues: ").append(form.getLanguageId() != null ? form.getLanguageId().size() : 0).append("<br>");
    
    if (form.getCompanyName() != null) {
        result.append("Entreprises: ").append(String.join(", ", form.getCompanyName())).append("<br>");
    }
    
    return result.toString();
}

@GetMapping("/testFormPage")
public String testFormPage() {
    return "test-form"; // Cela affichera test-form.html
}



}
