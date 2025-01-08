<template>
  <div class="card">
    <div class="card-header">
      <h4> Discounts
        <RouterLink to="/discount/create" class="btn btn-primary float-end">
          Add Discount Coupon
        </RouterLink>
      </h4>
    </div>
    <div class="card-body">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>Id</th>
            <th>Coupon type</th>
            <th>Coupon Value</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody v-if="discountCoupons.length > 0">
          <tr v-for="(coupon, index) in this.discountCoupons" :key="index">
            <td>{{ coupon.id }}</td>
            <td>{{ coupon.couponType }}</td>
            <td>{{ coupon.value }}</td>
            <td>
              <button class="btn btn-success"><RouterLink :to =" { path:'/discount/'+ coupon.id +'/edit' } ">Edit</RouterLink></button>&nbsp;&nbsp;
              <button class="btn btn-danger" @click="deleteDiscountCoupon(coupon.id)">Delete</button>
            </td>
          </tr>
        </tbody>
        <tbody v-else>
          <tr aria-colspan="3">Loading..</tr>
        </tbody>
      </table>
    </div>

  </div>
</template>

<script>
import axios from 'axios';

export default {

  name: 'discountCoupons',
  data() {
    return {
      discountCoupons: []
    }
  },
  mounted() {
    console.log("I am in mounted");
    this.getDiscountCoupons();
  },

  methods: {

  // Function to Fetch All discount coupons  
    getDiscountCoupons() {
      axios.get("http://localhost:8083/digi/discount/fetchAllCoupons?pageSize=10&pageNo=0").then(res => {
        this.discountCoupons = res.data.content;
        console.log("discountCoupons :: ", this.discountCoupons);
      })
    },

  // Function to delete a discount coupon by ID
    deleteDiscountCoupon(id) {
      const userConfirmed = confirm("Do you want to delete this Coupon for Id : " + id)
      if (userConfirmed) {
        axios
          .delete(`http://localhost:8083/digi/discount/deleteCoupon?id=${id}`)
          .then((res) => {
            console.log("Response:", res);
            alert("Coupon deleted successfully!");
            window.location.reload();
          })
          .catch((err) => {
            console.error("Error while deleting the coupon:", err);
            alert("Failed to delete the coupon. Please try again.");
          });
      } else {
        console.log("User canceled the delete operation for ID: " + id);
      }
    },

  // Function to edit Discount coupon by Id
    editDiscountCoupon(){
      axios.put('http://localhost:8083/digi/discount/updateCoupon?id=64')
            .then(res=>{
           // discountCoupon = res.data
      
          })
      }



  },
}





</script>