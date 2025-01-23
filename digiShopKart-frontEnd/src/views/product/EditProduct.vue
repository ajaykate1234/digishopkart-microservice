<template>
    <h4>Update product</h4>
    <div class="form">
        <form  @submit.prevent="editProduct">
            <table class="table table-bordered">
                <thead>
                    <th>Fields</th>
                    <th>Items</th>
                </thead>

                <tbody>
                    <tr>
                        <td>
                            <label for="productName">ProductName</label>
                        </td>
                        <td>
                            <input type="text" id="productName" v-model="this.product.productName" placeholder="Enter Product name" required>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label for="brand">Brand</label>
                        </td>
                        <td>
                            <input type="text" id="brand" v-model="this.product.brand" placeholder="Enter Brand name" required>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label for="productPrice">Price</label>
                        </td>
                        <td>
                            <input type="number" id="productPrice" v-model="this.product.productPrice" placeholder="Enter Product Price" required>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            <label>Availability:</label>
                        </td>
                        <td>
                            <select v-model="product.availability" required>
                                <option :value="true" selected>Available</option>
                                <option :value="false">Unavailable</option>
                            </select>
                        </td>     
                    </tr>
                    <tr >
                        Enter Number of Varient to Add : <input type="number" v-model="varientCount" min="0" required>
                           
                        <div v-for="(variant, index) in this.product.varientList" :key="index">
                            <h6>Varients {{ index + 1 }}</h6>
                        <td>
                            <label for="varientName">varientName</label>
                        </td>
                        <td>
                            <input type="text"  :id="'varientName' + index" v-model="this.product.varientList[index].name" placeholder="Enter Product varientName" required>
                        </td>

                        <td>
                            <label for="varientValue">varientValue</label>
                        </td>
                        <td>
                            <input type="text" :id="'varientValue' + index" v-model="this.product.varientList[index].value" placeholder="Enter Product varientValue" required>
                        </td>
                        </div>
                    </tr>

                    <tr>
                        
                    </tr>
                    
                    <tr>
                        
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button type="submit" class="btn btn-success align-center">Submit Coupon</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        <!-- Success Message -->
        <p v-if="successMessage" style="color: green;">{{ successMessage }}</p>

        <!-- Error Message -->
        <p v-if="errorMessage" style="color: red;">{{ errorMessage }}</p>
    </div>
</template>

<script>
import axios from 'axios';


export default{

    data(){
        return{
            product : {
                        id:null,
                        productName: "",
                        brand: "",
                        productPrice: null,
                        availability: null,
                        varientList:[
                            {
                                id: null,
                                name: "",
                                value: "",
                                availability: "",
                            }]   
                },
                successMessage:"",
                errorMessage:"",
                variantCount:1,
        }
    },

    mounted(){
        console.log("Mounted");
        this.getProduct(this.$route.params.id);
    },

    methods:{
        getProduct(productId){

            axios.get(`http://localhost:8082/digi/product/fetch?id=${productId}`).then(response=>{
                console.log("Response ::",response);
                this.product = response.data
            }).catch((err)=>{
                console.log("Error: ",err);
                
            })

        },

        editProduct(){
            console.log("inside editProduct : ",this.product);
            // this.product.isExpired=false
            axios.put(`http://localhost:8082/digi/product/update?id=${this.product.id}`,this.product).then(res=>{
                console.log("Product Added: ",res);
                this.successMessage = "Product Updated successfully!";
                this.errorMessage = "";
                
                
            })
            .catch((error)=>{
                console.log("Error : ",error);
                 this.errorMessage = "Failed to Update Product. Please try again.";
                this.successMessage = "";
            })
            
        },

        updateVarients(){
     
            
            const count = parseInt(this.varientCount);
            console.log("inside updateVarients: count = ",count);
            if(count>=0){
                if(this.product.varientList.length < 1){
                    this.product.varientList.push({
                        id: null,
                        name: '',
                        value: '',
                        availability: true,
                    });
                }
                else{
                    this.product.varientList.splice(count);
                }
            }
        },

        handleGlobalEvents() {
            this.updateVarients();
        }
    }



}
</script>