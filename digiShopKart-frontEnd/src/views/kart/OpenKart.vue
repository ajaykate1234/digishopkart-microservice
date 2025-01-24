<template>
<h1>You added below Items in the Kart</h1>

<div class="card">
    <div class="card-header">

    </div>
    <div class="card-body">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Item Id</th>
                    <th>Item Name</th>
                    <th>Item Image</th>
                    <th>Item Price</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="item in items" :key="item.id">
                    <td>{{ item.id }}</td> 
                    <td>{{ item.productName }}</td>
                    <td>{{ abc }}</td>
                    <td>{{ item.productPrice }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
</template>

<script>
import axios from 'axios';
import { onMounted, reactive, toRefs} from 'vue';

    export default{
        setup(){
            const state = reactive({
                    count: 10,
                    items:[{
                        id:null
                    }],
                    abc:'AAA',
                    JwtRequest:{
                        email:'Bhalu',
                        password: 'Kaliya'
                    }, 
                })
        const fetchBearerToken=()=>{
                axios.post(`http://localhost:8082/auth/login`,state.JwtRequest).then(res=>{
                    console.log("fetchBearerToken : res : ",res);
                    localStorage.setItem('jwtToken',res.data.jwtToken)
                }).catch(err=>{
                    console.log("fetchBearerToken : Error : ",err);
                })
            }

     // for now I have permited this url for backend , but facing some issue with this Jwt Authorization
        const fetchAllkartItemsForCustomer=()=>{
                axios.get(
                    `http://localhost:8082/digi/kart/fetchItems?customerId=${111}`,
                    {
                        headers:{
                            'Authorization': `Bearer ${localStorage.getItem("jwtToken")}`
                        },
                    }
                ).then(res=>{
                    console.log("fetchAllkartItemsForCustomer : Response : ",res);
                    state.items = res.data.productList
                    
                }).catch((err)=>{
                    console.log("fetchAllkartItemsForCustomer : Error : ",err);
                    
                })
            }

            onMounted(()=>{
                console.log("mounted");
                fetchBearerToken()
                fetchAllkartItemsForCustomer()
                
            })

        return{
            ...toRefs(state),
            fetchAllkartItemsForCustomer,
            fetchBearerToken


        }

        }
    }
    
</script>