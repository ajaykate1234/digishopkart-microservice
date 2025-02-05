<template>
    <div class="card">
        <div>
            <!-- <RouterLink to="/product/add" class="btn btn-success float-end add-btn">Add Product</RouterLink> -->

            <!-- <input type="text" v-model="this.searchQuery" placeholder="search product by Name or Brand" class="search-bar"> -->
            <!-- <ul>
            <li v-for="product in filteredProducts" :key="product.id">
                {{ product.productName }}
            </li>
        </ul> -->

        </div>
        <div class="card-header">
            <h4>Login User </h4>
        </div>

        <div class="card-body">
            <table class="table table-bordered">
                <thead>

                </thead>
                <tbody>
                    <tr>
                        <td>
                            <label>Username</label>
                        </td>
                        <td>
                            <input name="username" type="text">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Password </label>
                        </td>
                        <td>
                            <input name="password" type="text">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button class="btn btn-success"> Login</button>
                        </td>
                        
                    </tr>
                    <tr>
                        <td>
                            <button class="btn btn-success" @click="loginWithGoogleAccount()"> GetAccessToken</button>
                        </td>
                        <td>
                            {{ this.user }}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <button class="btn btn-success" @click="loginWithGoogle()">SignIn with Google</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
// axios.defaults.withCredentials = true; // Enables cookies in all requests


//-------------------------------------------------------------------

export default {

    data() {
        return {
            user: {
                userId:'',
                email: 'avtar1234@gmail.com',
                username: 'avtarpower',
                password: 'Avtar@123',
                roles: ['USER', 'ADMIN']
            },
            authorizationCode:'',
            accessToken:''

        };
    },

    mounted() {
        // console.log("### : ",filteredProducts());

        console.log("Login...!");
        // this.loginWithGoogleAccount();
        // this.extractAuthCode();
        // this.loginUser();

        // this.getUserInfo();
        this.loginWithGoogle();

    },

    methods: {

        loginUser() {
            console.log("start of loginUser");

            // axios.post(`http://localhost:8086/digi/user/login`,this.user).then(res => {
            axios.get(`http://localhost:8086/login?username=avtar&email=avtar@gmail.com&password=Avtar@123`,{withCredentials: true }).then(res => {
                console.log("loginUser : Response :", res);
                this.user = res.data.content

                console.log("loginUser : USER : ", this.user);


            }).catch(error => {
                console.log("loginUser : Error :", error);

            })
        },

        getUserInfo() {
            console.log("start of getUserInfo");
            // axios.post(`http://localhost:8086/digi/user/login`,this.user).then(res => {
            axios.get(`http://localhost:8086/user/info`,
                {
                    // Cookie: "JSESSIONID=691DFF6DE48C75A99047F326CAEC05D5",
                    withCredentials: true }).then(res => {

                    console.log("getUserInfo : Response :", res);
                    this.user = res.data.content

                    console.log("getUserInfo : USER : ", this.user);


                }).catch(error => {
                    console.log("getUserInfo : Error :", error);

                })
        },

        async loginWithGoogleAccount (){
            console.log("start of loginWithGoogleAccount");
            // axios.get('http://localhost:8086/oauth/callback').then(res=>{
            axios.get('http://localhost:8086/oauth2/login?username=avtar&email=avtar@gmail.com&password=Avtar@123')
                .then(res=>{
                    console.log("loginWithGoogleAccount : Response : ",res);
                    this.authorizationCode = res.data;
                    console.log("this.accessToken :",this.authorizationCode);
                })
                .catch(err=>{
                    console.log("loginWithGoogleAccount : Error : ",err);
                })

            //--
            // axios.get(`http://localhost:8086/oauth2/exchange?code=${this.authorizationCode}`)
            //     .then(res=>{
            //     console.log("exchange res: ",res);
            //     this.accessToken = res.data;
            //     }).catch(err=>{
            //         console.log("exchange err : ",err);
            //     })

            //---
            // axios.get(`http://localhost:8086/oauth2/getUserDataFromGoogle?accessToken=${this.accessToken}`)
            //     .then(res=>{
            //         console.log("getUserDataFromGoogle res : ",res);
            //         this.user= res.data;
            //     }).catch(err=>{
            //         console.log("getUserDataFromGoogle err :",err);
            //     })    
        },

        loginWithGoogle() {
            console.log("start of  loginWithGoogle");
            
            const clientId = "156204871014-085snmkiot5ajp5vfh5bjbkvhvsg2523.apps.googleusercontent.com";
            const redirectUri = "http://localhost:8086/oauth2/callback"; // Your backend callback
            const scope = "profile email";
            const responseType = "code"; // OAuth requires a code response
            const state = "mystate"; // Optional, used for CSRF protection

            // Construct Google OAuth URL
            const googleAuthUrl = `https://accounts.google.com/o/oauth2/v2/auth?response_type=${responseType}&client_id=${clientId}&scope=${encodeURIComponent(scope)}&state=${state}&redirect_uri=${encodeURIComponent(redirectUri)}`;

            console.log("googleAuthUrl : ",googleAuthUrl);
            
            // Redirect the user to Google Login Page
           window.location.href = googleAuthUrl;
        },


        extractAuthCode() {
            const urlParams = new URLSearchParams(window.location.search);
            const authCode = urlParams.get("code");

            console.log("authCodeauthCode : ",authCode);
            

            if (authCode) {
                console.log("Authorization Code:", authCode);
                // this.exchangeToken(authCode);
            }
        },

    }
}
</script>

<style>
.table-responsive {
    margin-bottom: 15px;
}

.search-bar {
    padding: 8px;
    margin-bottom: 10px;
    margin-top: 10px;
    margin-right: 40px;
    width: 100%;
    max-width: 300px;
    font-size: 16px;
    border-radius: 8px;
    float: right;
}

.add-btn {
    margin-bottom: 10px;
    margin-top: 10px;
    margin-right: 40px;
}

th {
    font-weight: bold;
}
</style>