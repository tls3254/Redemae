function addCart(){
    const smObject = document.getElementById("storeAndMenu");
    const mpAndMq = document.getElementById("priceAndQ");
    const cartObject = {
        orderItemPrice : mpAndMq.getAttribute("data-price"),
        orderItemQuantity : mpAndMq.getAttribute("data-quantity"),
        storeId : smObject.getAttribute("data-store-id"),
        menuId : smObject.getAttribute("data-menu-id")
    }

    fetch('/api/carts', {
        method:'POST',
        headers:{
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(cartObject),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('response 오류');
        }
        alert("장바구니 준비 완료")
        window.location.href = '/stores';
    })
    .catch((error) => {
        console.error('가져오기 작업에 문제 발생:', error);
        alert('공백 또는 이미 가입된 계정인지 확인하세요');
    });
}

function createMenu() {
    const storeObject = document.getElementById("store");
    const storeId = storeObject.getAttribute("data-store-id");
    const menuData = {
        menuName : document.getElementById("name").value,
        menuPrice : document.getElementById("price").value
    }

    fetch('/api/stores/' + storeId + '/menus',{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(menuData),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('response 오류');
        }
        alert("메뉴 생성 완료")
        window.location.href = '/stores';
    })
    .catch((error) => {
        console.error('가져오기 작업에 문제 발생:', error);
        alert('공백 또는 이미 가입된 계정인지 확인하세요');
    });
}

function updateMenu() {
    const smObject = document.getElementById("storeAndMenu");
    const storeId = smObject.getAttribute("data-store-id");
    const menuId = smObject.getAttribute("data-menu-id");
    const menuData = {
        menuName : document.getElementById("name").value,
        menuPrice : document.getElementById("price").value
    };

    fetch('/api/stores/' + storeId + '/menus/' + menuId,{
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(menuData),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('response 오류');
        }
        alert("메뉴 변경 완료")
        window.location.href = '/stores';
    })
    .catch((error) => {
        console.error('가져오기 작업에 문제 발생:', error);
        alert('공백 또는 이미 가입된 계정인지 확인하세요');
    });
}

function deleteMenu() {
    const smObject = document.getElementById("storeAndMenu");
    const storeId = smObject.getAttribute("data-store-id");
    const menuId = smObject.getAttribute("data-menu-id");
    const menuData = {
        menuName : document.getElementById("name").value,
        menuPrice : document.getElementById("price").value
    };

    fetch('/api/stores/' + storeId + '/menus/' + menuId,{
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(menuData),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('response 오류');
        }
        alert("메뉴 삭제 완료")
        window.location.href = '/stores';
    })
    .catch((error) => {
        console.error('가져오기 작업에 문제 발생:', error);
        alert('공백 또는 이미 가입된 계정인지 확인하세요');
    });
}