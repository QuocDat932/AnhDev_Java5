class Login {
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
    btnLogin_click = async () => {
        let dataLogin = {
            tk : $('#username').val(),
            mk : $('#password').val()
        }
        let {data : response} = await axios.get('java05/account-api/login',
            { params : dataLogin }
        )
        if (!response.status) {
            Swal.fire({
                title : response.message,
                text : 'Please check your username or password',
                icon : 'error',
                showConfirmButton: false,
                timer: 1500
            })
            $('body').removeClass('swal2-height-auto')
            return;
        }
        localStorage.setItem('sys_id', response.sysId)
        window.location.href = '/index'
    }
    checkKeyPress = (event) => {
        if (event.key === 'Enter') {
            this.btnLogin_click()
        }
    }
}