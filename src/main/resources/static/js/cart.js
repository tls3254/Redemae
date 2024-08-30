function CartDetails() {
    const cart = document.getElementById('cart');
    const cartId = cart.getAttribute("data-cart-id");
    window.location.href = '/carts/' + cartId;
}

function confirmOrderWithSSE(cartState) {
    const cart = document.getElementById('cart');
    const cartId = cart.getAttribute("data-cart-id");
    
    fetch("/api/carts/value/"+cartId, {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
        },
        body:JSON.stringify({"cartState":cartState})
    })
    .then(response => {
        if (response.ok) {
            setSse(cartId)
        } else {
            console.error('서버 응답이 실패했습니다. 응답 상태 코드:', response.status);
        }
    })
    .catch(error => {
        console.error('에러:', error);
    });
    
    function setSse(cartId) {
        fetch('/api/sse/' + cartId, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                alert('주문이 실패했습니다.');
                return response.text();
            }
        })
        .catch(error => {
            console.error('에러:', error);
        });
    }
}

function setupSse() {
    const cart = document.getElementById("cart");
    const cartId = cart.getAttribute("data-cart-id");
    fetch('/api/sse/user/' + cartId, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
    .then(response => {
        if (response.ok) {
            alert('주문을 완료했습니다.');
        } else {
            alert('주문이 실패했습니다.');
            return response.text();
        }
    })
    .catch(error => {
        console.error('에러:', error);
    });
}

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

//api gateWay 로 보낼 때
// async function confirmOrderWithSSE(orderId) {
//     const url = 'https://b26yyhck34.execute-api.ap-northeast-2.amazonaws.com/connect/';
//     const jwtToken = document.cookie;
//
//     try {
//         const response = await fetch(url, {
//             method: "POST",
//             mode: "cors",
//             cache: "no-cache",
//             credentials: "same-origin",
//             headers: {
//                 "Content-Type": "application/json",
//                 "Authorization" : jwtToken
//             },
//             body:JSON.stringify({
//                 "orderId":orderId
//             })
//         });
//
//         if (response.ok) {
//             console.log('서버 응답이 성공했습니다.');
//             alert("서버 응답 성공")
//             setupSSE(orderId)
//             // 실제 내용은 response.body 등을 통해 접근할 수 없음
//         } else {
//             console.error('서버 응답이 실패했습니다. 응답 상태 코드:', response.status);
//         }
//
//         // 추가 작업 수행
//     } catch (error) {
//         console.error('Fetch 요청 중 에러가 발생했습니다.', error);
//     }
//
//     // SSE 연결을 설정하는 함수
//     function setupSSE(orderId) {
//         const eventSource = new EventSource('/api/sse/'+ orderId);
//         eventSource.onmessage = function (event) {
//             alert("주문이 완료되었습니다")
//             location.reload();
//         };
//         eventSource.onerror = function (error) {
//             console.error('EventSource failed:', error);
//         };
//     }
// }