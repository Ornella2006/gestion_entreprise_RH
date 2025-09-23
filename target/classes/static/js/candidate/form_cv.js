            // Fonction pour prévisualiser l'image
            function previewImage(input) {
                const preview = document.getElementById('photoPreview');
                const img = document.getElementById('previewImg');
                
                if (input.files && input.files[0]) {
                    const reader = new FileReader();
                    reader.onload = function(e) {
                        img.src = e.target.result;
                        preview.style.display = 'block';
                    }
                    reader.readAsDataURL(input.files[0]);
                } else {
                    preview.style.display = 'none';
                }
            }

            // Fonctions pour ajouter des éléments dynamiques
            function addExperience() {
                const template = document.getElementById('experienceTemplate');
                const clone = template.content.cloneNode(true);
                const inputs = clone.querySelectorAll('[name]');
                inputs.forEach(input => {
                    const name = input.getAttribute('name');
                    input.setAttribute('name', name.replace('[]', ''));
                });
                document.getElementById('experiencesContainer').appendChild(clone);
            }

            function addSkill() {
                const template = document.getElementById('skillTemplate');
                const clone = template.content.cloneNode(true);

                const inputs = clone.querySelectorAll('[name]');
                inputs.forEach(input => {
                    const name = input.getAttribute('name');
                    input.setAttribute('name', name.replace('[]', ''));
                });
                document.getElementById('skillsContainer').appendChild(clone);
            }

            function addLanguage() {
                const template = document.getElementById('languageTemplate');
                const clone = template.content.cloneNode(true);


                const inputs = clone.querySelectorAll('[name]');
                inputs.forEach(input => {
                    const name = input.getAttribute('name');
                    input.setAttribute('name', name.replace('[]', ''));
                });
                document.getElementById('languagesContainer').appendChild(clone);
            }

            function removeItem(button) {
                button.closest('.experience-item, .skill-item, .language-item').remove();
            }

            function toggleCurrentJob(checkbox) {
                const endDateInput = checkbox.closest('.mb-3').querySelector('input[type="date"]');
                endDateInput.disabled = checkbox.checked;
                if (checkbox.checked) {
                    endDateInput.value = '';
                }
            }

            // Ajouter une expérience par défaut au chargement
            document.addEventListener('DOMContentLoaded', function() {
                addExperience();
                addSkill();
                addLanguage();
            });
