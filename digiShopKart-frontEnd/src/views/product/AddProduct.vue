<template>
    <h4>Add product</h4>
    <div class="form">
        <form  @submit.prevent="addProduct">
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
                    <tr>
                        <td>Product Image</td>
                        <td>
                            <input type="file" id="file"/>
                        </td>
                    </tr>
                    <tr >
                       <td>Enter Number of Varient to Add </td> <td><input type="number" v-model="this.varientCount" @change="updateVarients()"  min="0" required> </td> 
                    </tr>
                    <tr>       
                        <div v-for="(variant, index) in this.product.varientList" :key="index">
                            <h6>Varients {{ index + 1 }}</h6>
                        <td>
                            <label :for="varientName">varientName</label>
                        </td>
                        <td>
                            <!-- <input type="text"  :id="'varientName' + index" v-model="this.product.varientList[index].name" placeholder="Enter Product varientName" required> -->
                             <input type="text"  :id="'varientName' + index" v-model="this.product.varientList[index].name" placeholder="Enter Product varientName" required>
                        </td>
                            
                        <td>
                            <label :for="varientValue">varientValue</label>
                        </td>
                        <td>
                            <input type="text" :id="'varientValue' + index" v-model="this.product.varientList[index].value" placeholder="Enter Product varientValue" required>
                        </td>
                        </div>
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
                varientCount:null,
        }
    },

    mounted(){
        console.log("Mounted");
        // // Add global event listeners for mouse and key events
        // window.addEventListener("mousemove", this.handleGlobalEvents);
        // window.addEventListener("keydown", this.handleGlobalEvents);
        // window.addEventListener("keyup", this.handleGlobalEvents);
    },

    methods:{

        addProduct(){
            console.log("inside addProduct : ",this.product);
            
            axios.post(`http://localhost:8082/digi/product/add`,this.product).then(res=>{
                console.log("Product Added: ",res);
                this.successMessage = "Coupon submitted successfully!";
                this.errorMessage = "";  
                
                
            })
            .catch((error)=>{
                console.log("Error : ",error);
                 this.errorMessage = "Failed to submit the coupon. Please try again.";
                this.successMessage = "";
            })
            
        },

        updateVarients(){
            console.log("$$$$$$$$$$$");
            
            
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