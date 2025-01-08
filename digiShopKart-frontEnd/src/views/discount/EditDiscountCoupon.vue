<template>
    <div class="card">
        <div class="card-header">
            <h4> Update Discount Coupon
            <RouterLink to="/discount/create" class="btn btn-primary float-end">
                Add New Discount Coupon
            </RouterLink>
            </h4>
        </div>
    
        <div class="card-body">
    <table class="table">
        <thead>
            <tr>
                <th>Field</th>
                <th>Input</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>
                    <label for="id">Id:</label>
                </td>
                <td>
                    <input type="number" id="id" name="id" v-model="discountCoupon.id" disabled aria-label="Id" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="couponType">Coupon Type:</label>
                </td>
                <td>
                    <select id="couponType" name="couponType" v-model="discountCoupon.couponType" required aria-label="Coupon Type">
                        <option value="" disabled selected>Select a coupon type</option>
                        <option value="FLAT">Flat</option>
                        <option value="PERCENTAGE">Percentage</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="value">Discount Value:</label>
                </td>
                <td>
                    <input type="number" id="value" name="value" v-model="discountCoupon.value" placeholder="Enter discount value" required aria-label="Discount Value" />
                </td>
            </tr>
            <tr>
              <td colspan="2" class="text-center">
                            <button type="button" class="btn btn-success" @click="editDiscountCoupon()"> Update Coupon </button>
              </td>
            </tr>
        </tbody>
    </table>
</div>

    </div>
  
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
  
    name: 'editDiscountCoupon',
    data() {
      return {
        discountCoupon: {
            id:null,
            couponType:"",
            value: null,
        },
        editCoupon:{
          couponType:"",
            value: null,
        }
      };
    },

    mounted() {
      console.log("Iddd : "+this.$route.params.id);
      this.getDiscountCoupon(this.$route.params.id);
    },
  
    methods: {
  
    // Function to Fetch All discount coupons  
      getDiscountCoupon(couponId) {
        axios.get(`http://localhost:8083/digi/discount/fetchCoupon?id=${couponId}`).then(res => {
         this.discountCoupon = res.data;
          console.log("this.discountCoupon :: ", this.discountCoupon);
        })
      },

    // Function to edit Discount coupon by Id
      editDiscountCoupon(){
        console.log("inside edit");
        
        this.editCoupon.couponType = this.discountCoupon.couponType
        this.editCoupon.value = this.discountCoupon.value

        console.log("editCoupon::",this.editCoupon);
        
        axios.put(`http://localhost:8083/digi/discount/updateCoupon?id=${this.discountCoupon.id}`,this.editCoupon)
              .then(res=>{ 
                console.log("Putt: ",res.data);
                       
            })
        }
    },
  }
  
  
  
  
  
  </script>