<template>
    <div class="card">
        <div class="card-header">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>CustomerName</th>
                        <th>Email</th>
                        <th>Mobile</th>
                        <th>Gender</th>
                        <th>Addresses</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="customer in customers " :key="customer.id">
                        <td>{{  customer.id}}</td>
                        <td>{{  customer.name}}</td>
                        <td>{{  customer.email}}</td>
                        <td>{{  customer.mobile}}</td>
                        <td>{{  customer.gender}}</td>
                        <td>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>fullname</th>
                                        <th>houseOrBuilding</th>
                                        <th>area</th>
                                        <th>landmark</th>
                                        <th>town</th>
                                        <th>state</th>
                                        <th>country</th>
                                        <th>pinCode</th>
                                        <th>mobile</th>
                                        <th>addressType</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="address in customer.customerAddressList" :key="address.id">
                                        <td>{{ address.id }}</td>
                                        <td>{{ address.fullname }}</td>
                                        <td>{{ address.houseOrBuilding }}</td>
                                        <td>{{ address.area }}</td>
                                        <td>{{ address.landmark }}</td>
                                        <td>{{ address.town }}</td>
                                        <td>{{ address.state }}</td>
                                        <td>{{ address.country }}</td>
                                        <td>{{ address.pinCode }}</td>
                                        <td>{{ address.mobile }}</td>
                                        <td>{{ address.addressType }}</td>

                                    </tr>
                                </tbody>
                            </table>
                        </td>
                        <td>
                            <button class="btn btn-success"> Edit</button> &nbsp;&nbsp;
                            <button class="btn btn-danger"> Delete</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

</template>

<script>
import axios from 'axios';


export default{
    data(){
        return{
            customers:[
                {
                    id:null,
                    name:'Ajay Kate',
                    gender:'Male',
                    email:'ajaykate@gmail.com',
                    mobile:'9000000001',
                    status:'',
                    customerAddressList:[
                        {
                            id:null,
                            fullname:'Ajay Kate',
                            country:'India',
                            state:'Maharashtra',
                            town:'Pune',
                            area:'Kharadi',
                            houseOrBuilding:'Sai Prasad Building',
                            landmark:'near to Vandana Residency',
                            pinCode:'4101014',
                            mobile:'9000000001',
                            addressType:'BillingAddress'
                        }
                    ]
                },
            ],
            
        }
    },

    methods:{
        getCustomers(){
            axios.get(`http://localhost:8086/digi/customer/fetchAll?pageNo=0&pageSize=5`).then(res=>{
                console.log("Response : ",res);
                this.customers = res.data.content
                
            }).catch((err)=>{
                console.log("Error : ",err);
            })
        }
    },

    mounted(){
        this.getCustomers();
    },
}
</script>