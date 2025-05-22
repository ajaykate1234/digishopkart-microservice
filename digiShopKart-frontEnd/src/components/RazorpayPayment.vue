<template>
    <table class="table table-bordered">
      <tbody>
        <tr>
          <td><label for="amount">Enter Amount</label></td>
          <td>
            <input v-model="amount" id="amount" type="number" class="form-control" />
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <button @click="startPayment" class="btn btn-primary">Make Pay</button>
          </td>
        </tr>
      </tbody>
    </table>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import axios from 'axios';
  
  // Reactive amount state
  const amount = ref(50); // default ₹50
  
  const startPayment = async () => {
    try {
      // Step 1: Call backend to create Razorpay order
      const response = await axios.post(
        `http://localhost:8088/digi/create-order?amount=${amount.value}`
      );
  
      const order = response.data;
  
      // Step 2: Razorpay options
      const options = {
        key: 'rzp_test_GLk3nC2quI9HHP', // Replace with your test key
        amount: order.amount,
        currency: order.currency,
        name: 'DigiShopKart',
        description: 'Test Payment',
        order_id: order.id,
        handler: async (response) => {
          // Step 3: Verify payment on backend
          await axios.post('http://localhost:8088/digi/verify-payment', {
            razorpay_order_id: response.razorpay_order_id,
            razorpay_payment_id: response.razorpay_payment_id,
            razorpay_signature: response.razorpay_signature
          });
          alert('Payment Verified!');
        },
        prefill: {
          name: 'Ajay',
          email: 'ajay@example.com',
          contact: '9999999999'
        },
        theme: {
          color: '#3399cc'
        }
      };
  
      const razorpay = new window.Razorpay(options);
      razorpay.open();
    } catch (err) {
      console.error('Payment Error:', err);
      alert('Something went wrong. Try again.');
    }
  };
  </script>
  
  <style scoped>
  .btn {
    background-color: #3399cc;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
  }
  
  .form-control {
    padding: 6px;
    width: 100%;
  }
  </style>
  