class Login {
    loadInit = () => {

    }
    btnShowPassword_click = () => {
        let element = $(`#password`)
        let toggle = $(`#toggleShowPassword`)
        if (element.prop('type') === 'password') {
            element.prop('type', 'text')
            toggle.removeClass('bi-eye-fill').addClass('bi-eye-slash-fill')
        } else {
            element.prop('type', 'password')
            toggle.removeClass('bi-eye-slash-fill').addClass('bi-eye-fill')
        }
    }
}