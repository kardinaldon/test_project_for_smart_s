new Vue({
    el: '#login_form_content',
    data: {
        url_auth: '/auth/authenticator',
        url_index: '/index.html',
        url_main: '/main_page.html',
        login: '',
        password: ''
    },
    methods: {
        onSubmit: function() {
            axios.get(this.url_auth, {
                auth: {
                    username: this.login,
                    password: this.password
                }})
                .then(response => {
                    if(response.status == '200'){
                        window.location.href = this.url_main;
                    } else {
                        window.location.href = this.url_index;
                    }
            })
                .catch(error => {
                    console.log(error);
                });
        }
    }
});