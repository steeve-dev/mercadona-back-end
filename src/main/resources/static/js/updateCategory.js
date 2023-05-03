// Update category form

async function getCategoryUpdateForm(url, divId) {
    await fetch(url).then(res => res.text()).then(data => {
        document.getElementById(divId).innerHTML = data;
    }).then(() => {
    })
}

const categoryForm = document.getElementsByClassName("edit-button");

for (let i = 0; i < categoryForm.length; i++) {
    categoryForm[i].addEventListener('click', function(event) {
        event.preventDefault(); // Empêcher le comportement par défaut du lien

        let categoryId = event.target.getAttribute('update-cetegory-id');
        let url = "category/update/" + categoryId; // l'URL du contrôleur correspondant à la catégorie
        let divId = "category-update-form" + categoryId;

        getCategoryUpdateForm(url, divId);
    });
}
