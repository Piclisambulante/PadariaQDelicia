import { fazerPedido } from "../api/api.js";

let carrinho = [];

export function setCarrinho(produtos) {
    carrinho = produtos;
    renderCarrinho();
}

function renderCarrinho() {
    const container = document.querySelector(".cart-items-list");
    const empty = document.querySelector(".cart-empty");
    const resumo = document.querySelector(".cart-summary");

    container.innerHTML = "";

    if (carrinho.length === 0) {
        empty.style.display = "flex";
        resumo.classList.add("cart-summary-disabled");
        return;
    }

    empty.style.display = "none";
    resumo.classList.remove("cart-summary-disabled");

    carrinho.forEach(item => {
        const div = document.createElement("div");
        div.classList.add("cart-item");

        div.innerHTML = `
            <span>${item.nome}</span>
            <span>x${item.quantidade}</span>
        `;

        container.appendChild(div);
    });
}

async function enviarPedido() {
    const nome = document.querySelector("input[type='text']").value;
    const telefone = document.querySelector("input[type='tel']").value;

    if (!nome) {
        alert("Informe seu nome");
        return;
    }

    // 🔥 DTO EXATAMENTE COMO O BACK ESPERA
    const dto = {
        clienteNome: nome,
        clienteEmail: null,
        clienteNumero: telefone,
        itens: carrinho.map(item => ({
            produtoId: item.id,
            quantidade: item.quantidade
        }))
    };

    try {
        const pedido = await fazerPedido(dto);
        alert("Pedido criado com sucesso!");
        console.log(pedido);
    } catch (err) {
        alert("Erro ao enviar pedido");
        console.error(err);
    }
}
document.addEventListener("DOMContentLoaded", () => {
    const btn = document.querySelector(".whatsapp");
    if (btn) {
        btn.addEventListener("click", enviarPedido);
    }
});

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
