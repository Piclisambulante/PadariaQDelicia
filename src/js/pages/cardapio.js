/*
document.addEventListener("DOMContentLoaded", () => {
  const container = document.getElementById("produtos");

  function criarCard(produto) {
    return `
      <div class="card" data-categoria="${}">
        <img src="${}" alt="${}">
        <h3>${}</h3>
        <p>R$ ${}</p>
      </div>
    `;
  }

  fetch("")
    .then(res => res.json())
    .then(produtos => {
      container.innerHTML = produtos.map(criarCard).join("");
    })
    .catch(err => console.error("Erro ao carregar produtos:", err));

  const botoes = document.querySelectorAll(".btnsCardapios button");
  botoes.forEach(botao => {
    botao.addEventListener("click", () => {
      const filtro = botao.dataset.filtro;
      const cards = container.querySelectorAll(".card");

      cards.forEach(card => {
        if (filtro === "todos" || card.dataset.categoria === filtro) {
          card.style.display = "block";
        } else {
          card.style.display = "none";
        }
      });
    });
  });
});*/


const botoes = document.querySelectorAll('.botao');

botoes.forEach(botao => {
    botao.addEventListener('click', () => {
        botoes.forEach(b => b.classList.remove('selecionado'));

        botao.classList.add('selecionado');

        const filtro = botao.dataset.filtro;
        console.log(filtro);
    });
});



const btn = document.getElementById("btnEncomenda");
const contato = document.getElementById("contato");
const headerContato = document.getElementById(" ")

btn.addEventListener("click", (e) => {
  e.preventDefault();

  const start = window.pageYOffset;
  const end = contato.getBoundingClientRect().top + start;
  const distance = end - start;
  const duration = 900; 
  let startTime = null;

  function easeInOut(t) {
    return t < 0.5
      ? 2 * t * t
      : 1 - Math.pow(-2 * t + 2, 2) / 2;
  }

  function animation(currentTime) {
    if (!startTime) startTime = currentTime;
    const timeElapsed = currentTime - startTime;
    const progress = Math.min(timeElapsed / duration, 1);

    window.scrollTo(0, start + distance * easeInOut(progress));

    if (timeElapsed < duration) {
      requestAnimationFrame(animation);
    }
  }

  requestAnimationFrame(animation);
});

document.addEventListener("DOMContentLoaded", () => {
  const btnNumber = document.getElementById("btnNumber");
  const textoOriginal = btnNumber.innerText;

  btnNumber.addEventListener("click", () => {
    navigator.clipboard.writeText(textoOriginal).then(() => {
      btnNumber.innerText = "Número copiado!";

      setTimeout(() => {
        btnNumber.innerText = textoOriginal;
      }, 1500);
    });
  });
});

