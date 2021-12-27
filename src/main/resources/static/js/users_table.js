const checkbox = document.querySelector('#select-all');
const checkboxes = document.querySelectorAll('[type="checkbox"]')
let changeStatusCheckboxes = (status) => checkboxes.forEach(value => value.checked = status);

checkbox.onclick = () => checkbox.checked === true ? changeStatusCheckboxes(true) : changeStatusCheckboxes(false);

let elements = document.getElementById('users_form').getElementsByTagName('input');
const key = 'id';

function formatUsersId(key, checkboxes = []) {
    let iDs = new FormData();
    checkboxes.forEach(element => iDs.append(key, element.value));
    alert(iDs.getAll(key));
    alert(iDs.getAll(key).length);
}

let blockUsersByActiveCheckbox = (elements) => {
    document.getElementById('block-option-user').onclick = () => {
        const result =  elements.filter(checkbox => checkbox.checked === true && checkbox.id !== 'select-all');
        formatUsersId(key, result);
    }
}

blockUsersByActiveCheckbox(Array.from(elements));
