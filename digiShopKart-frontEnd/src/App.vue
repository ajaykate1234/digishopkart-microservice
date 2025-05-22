<template>
  <div class="page-container">
    <header class="header">
      <nav class="navbar navbar-expand-lg "> <!--bg-body-tertiary-->
        <div class="container-fluid"> <img class="circular-image" src="./assets/DigiShopKart_Logo.png" height="70px" width="70px" alt="file not found">
          <marquee direction="left" style="font-size: large; color: brown; font-family: Arial, Helvetica, sans-serif; Unicode', Geneva, Verdana, sans-serif;"><-- 🙏😊 Welcome to DigiShopKart Digital Marketing ...</marquee>
          <RouterLink class="navbar-brand" to="/">
          </RouterLink>
          <!-- <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
           aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button> -->
          <!-- <div class="collapse navbar-collapse" id="navbarSupportedContent"> -->
          <div>  
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <RouterLink class="nav-link" to="/">Home</RouterLink>
              </li>
              <li class="nav-item">
                <RouterLink class="nav-link" to="/digi/shopping">Shopping</RouterLink>
              </li>
              <li class="nav-item">
                <RouterLink class="nav-link" to="/customers">Customers</RouterLink>
              </li>
              <li class="nav-item">
                <RouterLink class="nav-link" to="/products">Products</RouterLink>
              </li>
              <li class="nav-item">
                <RouterLink class="nav-link" to="/discount">Discounts</RouterLink>
              </li>
              <li class="nav-item">
                <RouterLink class="nav-link" to="/about">About</RouterLink>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
    <div class="content-wrapper">
      <aside class="left-sidebar">
        <LeftSideBar/>
        <!-- <table class="table">
          <thead>
            <tr><th>1</th><th>2</th></tr>
          </thead>
          <tbody>
            <tr>
              <td>4</td><td>5</td>
            </tr>
          </tbody>
        </table> -->

      </aside>
      <section class="body-content">
        <RouterView />
        <!-- <MainContainer/> -->
      </section>
      <aside class="right-sidebar">
        <!-- <RouterLink class="nav-to" to="/rightSideBar">  Right Sidebar</RouterLink> -->
        <RouterLink class="nav-to" to="/openKart">  Open Kart</RouterLink><br>
        <RouterLink class="nav-to" to="/digi/userLogin">  User Login</RouterLink><br>
        <RouterLink class="nav-to" to="/digi/registerUser">  Register User</RouterLink><br>
        <RouterLink class="nav-to" to="/digi/razorpay">Make Payment</RouterLink>
      </aside>
    </div>
    <footer class="footer">Footer</footer>
  </div>
</template>

<script>
import LeftSideBar from '@/components/LeftSideBar.vue'
export default {
  components : {LeftSideBar},
  name: "PageLayout",
};
</script>

<style scoped>
/* General Reset */
body,
html {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
  box-sizing: border-box;
}

/* Main Page Layout */
.page-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

/* Header */
.header {
  height: 100px;
  background-color: #f7feff;
  color: rgb(118, 255, 14);
  text-align: center;
  line-height: 26px;
  font-size: 20px;
  border-color: black;
  border: solid;
}

/* Content Wrapper */
.content-wrapper {
  display: flex;
  flex: 1; /* Fills remaining space between header and footer */
}

/* Left Sidebar */
.left-sidebar {
  width: 200px;
  background-color: rgb(187, 255, 232);
  color: gold;
  padding: 10px;
  text-align: center;
  position: fixed; /* Fixes it to the left edge */
  left: 0;
  top: 100px; /* Aligns with the header */
  bottom: 40px; /* Aligns with the footer */
  border: solid;
}

/* Right Sidebar */
.right-sidebar {
  width: 200px;
  background-color: rgb(187, 255, 232);
  color: gold;
  padding: 10px;
  text-align: center;
  position: fixed; /* Fixes it to the right edge */
  right: 0;
  top: 100px; /* Aligns with the header */
  bottom: 40px; /* Aligns with the footer */
  border: solid;
}

/* Body Content */
.body-content {
  flex: 1;
  margin: 0px 200px 0px; /* Leaves space for header, left, and right */
  background-color: #f8f9fa;
  color: black;
  /* padding: 20px; */
  overflow-y: auto;
  text-align: center;
  border: solid;
}

/* Footer */
.footer {
  height: 40px;
  background-color: #888888;
  color: tomato;
  text-align: center;
  line-height: 40px;
  border: solid;
}

.circular-image {
  width: 70px; /* Image width */
  height: 70px; /* Image height */
  border-radius: 50%; /* Makes the image circular */
  border: 1px solid #007bff; /* Adds a border with desired color */
  object-fit: cover; /* Ensures the image fits well inside the circle */
}

.navbar-nav{
  background-color:turquoise;
  border-radius: 8px;
}
</style>
