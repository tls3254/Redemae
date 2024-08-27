function addCart(){

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
        alert("상점 생성 완료")
        window.location.href = '/stores';
    })
    .catch((error) => {
        console.error('가져오기 작업에 문제 발생:', error);
        alert('공백 또는 이미 가입된 계정인지 확인하세요');
    });
}