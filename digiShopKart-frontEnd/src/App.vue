<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    
    <div class="wrapper">
      <nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <RouterLink class="navbar-brand" to="/">Navbar</RouterLink>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <RouterLink class="nav-link" to="/">Home</RouterLink>
        </li>
        <li class="nav-item">
          <RouterLink class="nav-link" to="/about">About</RouterLink>
        </li>
        <li class="nav-item">
          <RouterLink class="nav-link" to="/products">Products</RouterLink>
        </li>
        <li class="nav-item">
          <RouterLink class="nav-link" to="/discount">Discounts</RouterLink>
        </li>
      </ul>
    </div>
  </div>
</nav>

    </div>
  </header>

  <RouterView />
</template>

<style scoped>

</style>
