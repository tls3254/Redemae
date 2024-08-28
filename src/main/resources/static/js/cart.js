function deleteCart(){
    const cart = document.getElementById("cart");
    const cartId = cart.getAttribute("data-cart-Id");


    fetch("/api/carts/"+cartId, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            if (response.ok) {
                window.location.href = '/stores';
            } else {
                alert('카트 삭제에 실패했습니다.');
                return response.text();
            }
        })
        .catch(error => {
            console.error('에러:', error);
        });
}