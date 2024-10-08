function store() {
    const storeData = {
        storeName: document.getElementById('name').value,
        storeAddress: document.getElementById('address').value,
        storeCategory: document.getElementById('category').value
    };

    fetch('/api/stores', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(storeData),
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


function updateData() {
    const storeData = {
        storeName : document.getElementById('name').value,
        storeAddress : document.getElementById('address').value,
        storeCategory : document.getElementById('category').value
    }
    const updateButton = document.querySelector('.btn-primary');
    const storeId = updateButton.getAttribute('data-store-id');

    fetch(`/api/stores/${storeId}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(storeData),
    })
    .then(response => {
        if (response.ok) {
            window.location.reload();
        } else {
            alert('상점 수정에 실패했습니다.');
            return response.text();
        }
    })
    .catch(error => {
        console.error('에러:', error);
    });
    $('#textModal').modal('hide');
}


function deleteStore() {
    const updateButton = document.querySelector('.btn-pri');
    const storeId = updateButton.getAttribute('data-store-id');

    fetch(`/api/stores/${storeId}`, {
        method: 'DELETE',
        headers: {
        'Content-Type': 'application/json',
        },
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/api/stores';
        } else {
            alert('상점 삭제에 실패했습니다.');
        }
    }).catch(error => {
        console.error('에러:', error);
        alert('상점 삭제 중에 오류가 발생했습니다.');
    })
}