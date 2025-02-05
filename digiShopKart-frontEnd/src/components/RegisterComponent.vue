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
            <h4>Register User </h4>
        </div>

        <div class="card-body">
            <table class="table table-bordered">
                <thead >
                 
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <label>Enter username : </label>
                        </td>
                        <td>
                            <input name="username" type="text">
                        </td>                    
                    </tr> 
                    <tr>
                        <td>
                            <label>Enter Email : </label>
                        </td>
                        <td>
                            <input name="email" type="email">
                        </td>
                    </tr>   
                    <tr>
                        <td>
                            <label>Enter Password :</label>
                        </td>
                        <td>
                            <input name="password" type="text">
                        </td>                    
                    </tr>
                    <tr>
                        <td>Select Roles</td>
                        <td>
                           <label for="user">User  :</label>&nbsp;
                           <input type="checkbox" name="user" value="user">&nbsp;&nbsp;&nbsp;
                    
                           <label for="admin">Admin  :</label> &nbsp;
                           <input type="checkbox" name="admin" value="admin">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button class="btn btn-success"> Submit</button>
                        </td>
                    </tr> 
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios';


//-------------------------------------------------------------------

export default {

    data() {
        return {
            user:{
                email:'avtar@gmail.com',
                username:'avtar',
                password:'Avtar@123',
                roles:['USER','ADMIN']
            }
                
        };
    },

    mounted() {
        // console.log("### : ",filteredProducts());
            
        console.log("register...!");
        this.registerUser();

    },

    methods: {

        registerUser() {
            axios.post(`http://localhost:8086/digi/user/register`,this.user).then(res => {
                console.log("Response :", res);
                this.products = res.data.content
            })
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
    font-weight:bold;
}
</style>