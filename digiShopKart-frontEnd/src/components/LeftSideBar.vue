<template>
<div class="left-bar">
    <table class="table">
        <thead>

        </thead>
        <tbody>
            <tr>
                <td>
                    <label for="">Search Bar</label>
                    <input type="text" v-model="state.searchQuery" class="search-bar" placeholder="Search products..."/>
                </td>
            </tr>
            <tr>
                <td>price Range :
                    <label for="price Filter"> 
                        {{state.minPrice}} - {{state.maxPrice}}
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="minPrice">Min Price: 
                        {{ state.minPrice }}
                    </label>
                    <input type="range" id="minPrice" v-model=state.minPrice :min="0" :max="100000" step="10"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="maxPrice">Max Price: 
                        {{ state.maxPrice}}
                    </label>
                    <input type="range" id="maxPrice" v-model=state.maxPrice name="maxPrice" :min="0" :max="100000" step="10"/>    
                </td>
            </tr>
            <tr>
                <td>
                    <!-- <RouterLink to="/mainConainer"> -->
                        <button @click="applyFilter()" class="filter-btn"> Apply Filter</button>
                    <!-- </RouterLink> -->
                </td>
            </tr>
            <tr>
                <td>
                    <ul>
                        <li v-for="product in filteredProducts" :key="product.id">
                            <!-- {{ product.name }} - ${{ product.price }} -->
                        </li>
                    </ul>
                </td>
            </tr>
        </tbody>
    </table>
</div>
</template>

<script>
import axios from 'axios';
import { computed, onMounted, reactive } from 'vue';

export default{
    setup(){
        const state = reactive({
            min:0,
            max:200,
            minPrice:0,
            maxPrice:200000,
            // priceRange:[0,50000],
            filteredProducts:[],
            products:[],
            searchQuery:''
        })

        const applyFilter=()=>{
            state.products.forEach(product => console.log("### : ",product.productName, product.productPrice));

            console.log("applyFilter called...", state.products);
            
                // Filtering products based on price rang   e and search query
                    const filteredProducts = state.products.filter((product) => {
                    console.log("Range: ", state.minPrice, "-", state.maxPrice);

                    // Filter by price range
                    const matchesPrice = 
                        product.productPrice >= state.minPrice && 
                        product.productPrice <= state.maxPrice;
                    console.log("matchesPrice: ", matchesPrice);

                    // Filter by search query
                    const matchesSearch = 
                        product.brand.toLowerCase().includes(state.searchQuery.toLowerCase());
                    console.log("matchesSearch: ", matchesSearch);

                    // Return products that match both conditions
                    return matchesPrice && matchesSearch;
                    });

                    console.log("Filtered Products: ", filteredProducts);
            
        }

        const getProducts=()=> {

            axios.get(`http://localhost:8082/digi/product/fetchAll?pageNo=0&pageSize=20`).then(res => {
                console.log("Response ::", res.data.content);
                state.products = res.data.content

            })
        }

        onMounted : { 
        getProducts()

    }
                
    return{ state, applyFilter ,getProducts}

    }    
    
}
</script>

<style scoped>
.left-sidebar {
  padding: 20px;
  background-color: #f7f7f7;
  border-right: 1px solid #ccc;
  height: 100%;
}

.search-bar {
  width: 100%;
  margin-bottom: 2px;
  margin-left: 0px;
  margin-right: auto;
  border: 1px solid #ccc;
  border-radius: 8px;
}

/* .price-filter {
  margin-bottom: 20px;
} */

.price-filter label {
  display: block;
  margin-bottom: 8px;
}

.filter-btn {
  padding: 10px 15px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.filter-btn:hover {
  background-color: #0056b3;
}

.product-list ul {
  list-style-type: none;
  padding: 0;
}

.product-list li {
  margin-bottom: 5px;
}

input[type="range"] {
  width: 100%;
  margin: 10px 0;
}

</style>