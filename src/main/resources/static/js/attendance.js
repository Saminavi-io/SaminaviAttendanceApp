document.addEventListener('DOMContentLoaded', function() {
  document.querySelectorAll('.attendance-form').forEach(function(form) {
    form.addEventListener('submit', function(event) {
      event.preventDefault(); // prevent page redirect
      const url = form.getAttribute('action');
      const formData = new FormData(form);

      fetch(url, {
        method: 'POST',
        body: formData,
        headers: {
          'X-Requested-With': 'XMLHttpRequest' // Optional: indicates AJAX to the backend
        }
      })
      .then(response => {
        if (response.ok) return response.text();
        throw new Error('Network response was not ok');
      })
      .then(result => {
        let actionText = url.includes('signin') ? 'signed in' : 'signed off';
        showPopup('You\'re successfully ' + actionText + '.');
      })
      .catch(error => {
        showPopup('An error occurred: ' + error.message);
      });
    });
  });

  function showPopup(message) {
    const popupMsg = document.getElementById('popup-message');
    const popup = document.getElementById('confirmation-popup');
    if (popupMsg && popup) {
      popupMsg.textContent = message;
      popup.style.display = 'block';
    } else {
      alert(message); // Fallback if no modal is defined
    }
  }
});