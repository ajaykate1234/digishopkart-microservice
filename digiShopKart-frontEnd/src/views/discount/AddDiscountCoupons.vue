<template>
    <h4>Add discount Coupon</h4>
    <div class="form">
            <form @submit.prevent="addDiscountCoupon">
                <label for="couponType">Coupon Type:</label>
                    <select id="couponType" v-model="discountCoupon.couponType" required>
                        <option value="" disabled>Select a coupon type</option>
                        <option value="FLAT">Flat</option>
                        <option value="PERCENTAGE">Percentage</option>
                    </select>
                    
                <label for="value">Discount Value:</label>
                    <input type="number" id="value" v-model="discountCoupon.value" placeholder="Enter discount value" required/>

                <div>
                    <button type="submit" class="btn btn-success">Submit Coupon</button>
                </div>    
            </form>

             <!-- Success Message -->
            <p v-if="successMessage" style="color: green;">{{ successMessage }}</p>

            <!-- Error Message -->
            <p v-if="errorMessage" style="color: red;">{{ errorMessage }}</p>

    </div>
</template>

<script>
import axios from 'axios';
import { onMounted } from 'vue';


export default{
    name:'discountCoupons',
    data(){
        return{
            discountCoupon:{
                couponType:"",
                value:null,
            },
            successMessage:"",
            errorMessage:"",
        };
    },

    mounted(){
    console.log("Calling from mounted");
    },

    methods:{
        addDiscountCoupon(){
            axios.post("http://localhost:8083/digi/discount/addCoupon",this.discountCoupon).then(res=>{
                console.log("Coupon Submitted successfully : ",res);
                this.successMessage = "Coupon submitted successfully!";
                this.errorMessage = "";  
                
            // Reset the form after submission
            this.discountCoupon = {
            couponType: "",
            value: null,
          };
            }).catch((error)=>{
                console.error("Error submitting coupon:", error);
                this.errorMessage = "Failed to submit the coupon. Please try again.";
                this.successMessage = "";
            });
        },
    },
};



</script>