document.querySelectorAll(".add-icon").forEach(button => {
  button.addEventListener("click", () => {
    const span = button.querySelector("span");

    if (button.classList.contains("active")) return;

    button.classList.add("active");
    span.textContent = "✔";

    setTimeout(() => {
      span.textContent = "+";
      button.classList.remove("active");
    }, 1000);
  });
});

const btn = document.getElementById("btnEncomenda");
const btnHeader = document.getElementById("btnContato");
const contato = document.getElementById("contato");

function scrollToContato(e) {
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
}
btn.addEventListener("click", scrollToContato);
btnHeader.addEventListener("click", scrollToContato);

const observer = new IntersectionObserver((entries) => {
  entries.forEach(entry => {
    if (entry.isIntersecting) {
      entry.target.classList.add("show");
      observer.unobserve(entry.target); 
    }
  });
}, {
  threshold: 0.15
});

document.querySelectorAll(".reveal").forEach(el => {
  observer.observe(el);
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
