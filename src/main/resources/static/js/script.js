// Get product with promotion

async function getProductSection() {
    await fetch("promotion").then(res => res.text()).then(data => {
        document.getElementById("home-product").innerHTML = data;
    }).then(() => {
    })
}

const catalogueButton = document.getElementById("promotion-productCard");

catalogueButton.addEventListener("click", getProductSection)


// Get product by category

async function getCategorySection(url) {
    await fetch(url).then(res => res.text()).then(data => {
        document.getElementById("home-product").innerHTML = data;
    }).then(() => {
    })
}

const categoryLinks = document.getElementsByClassName("home-main-categoryList__a");

for (let i = 0; i < categoryLinks.length; i++) {
    categoryLinks[i].addEventListener('click', function(event) {
        event.preventDefault();

        let categoryId = event.target.getAttribute('data-category-id');
        let url = '/category/' + categoryId; // l'URL du contrôleur correspondant à la catégorie

        getCategorySection(url);
    });
}

