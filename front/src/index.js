const modal = document.querySelector(".modal");
const btnOpenPopup = document.querySelector(".loginBtn");
const cancleBtn = document.querySelector(".cancleBtn");

btnOpenPopup.addEventListener("click", () => {
  modal.style.display = "block";
  modal.classList.add("show");
});

cancleBtn.addEventListener("click", () => {
  modal.style.display = "none";
  modal.classList.remove("show");
});
