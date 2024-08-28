function setRating(event) {
    const stars = document.querySelectorAll('#rating span');
    const ratingValue = document.getElementById('ratingValue');
    let rating = stars.length - Array.from(stars).indexOf(event.target);
    ratingValue.value = rating;

    stars.forEach((star, index) => {
        if (index >= stars.length - rating) {
            star.classList.add('active');
        } else {
            star.classList.remove('active');
        }
    });
}

function createReview(){
    const cartOb = document.getElementById("cartId");
    const cartId = cartOb.getAttribute('data-cart-id');
    const rObj = {
        point : document.getElementById("ratingValue").value,
        content : document.getElementById("message").value
    }

    fetch('/api/carts/' + cartId + '/reviews' ,{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(rObj),
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/carts/6/reviews';
        } else {
            alert('리뷰 생성에 실패했습니다.');
            return response.text();
        }
    })
    .catch(error => {
        console.error('에러:', error);
    });
}