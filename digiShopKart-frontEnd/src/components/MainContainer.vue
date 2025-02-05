<template>
    <div class="card">
        <div>
        <RouterLink to="/product/add" class="btn btn-success float-end add-btn">Add Product</RouterLink>

        <input type="text" v-model="this.searchQuery" placeholder="search product by Name or Brand" class="search-bar">
        <ul>
            <li v-for="product in filteredProducts" :key="product.id">
                {{ product.productName }}
            </li>
        </ul>
        
    </div>
        <div class="card-header">
            <h4>Products
                
            </h4>
        </div>

        <div class="card-body">
            <table class="table table-bordered">
                <thead >
                    <tr>
                        <th>Product Id</th>
                        <th>Product Name</th>
                        <th>Image</th>
                        <th>Brand</th>
                        <th>Price</th>
                        <th>Availability</th>
                        <th>Variants</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="product in result" :key="product.id">
                        <td>{{ product.id }}</td>
                        <td>{{ product.productName || 'N/A' }}</td>
                        <td>
                            <!-- Displaying the image from the byte[] -->
                            <div v-if="product.productImage">
                              <img :src="imageSrc(product.productImage)" height="auto" weight="auto" alt="Product Image" />
                            </div>
                        </td>
                        <td>{{ product.brand }}</td>
                        <td>{{ product.productPrice }}</td>
                        <td>{{ product.availability ? 'In Stock' : 'Out Of Stock' }}</td>
                        <td>
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th>Variant Id</th>
                                        <th>Name</th>
                                        <th>Value</th>
                                        <!-- <th>Availability</th> -->
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr v-for="variant in product.varientList" :key="variant.id">
                                        <td>{{ variant.id }}</td>
                                        <td>{{ variant.name }}</td>
                                        <td>{{ variant.value }}</td>
                                        <!-- <td>{{ variant.availability !== null ? (variant.availability ? 'In Stock' : 'Out Of Stock') : 'N/A' }}</td> -->
                                    </tr>
                                </tbody>
                            </table>
                        </td>

                        <td>
                            <button class="btn btn-success"><RouterLink :to="{path:'/product/'+product.id+'/edit'}">Edit</RouterLink></button> &nbsp;&nbsp;
                            <button class="btn btn-danger" @click="deleteProduct(product.id)">Delete</button>
                        </td>
                       
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script>
import axios from 'axios';


//-------------------------------------------------------------------

export default {

    data() {
        return {
            products: [
                {
                    id: 1,
                    productName: "Laptop",
                    brand: "Dell",
                    productPrice: 60000.0,
                    availability: true,
                    varientList: [
                         { id: 1, name: "RAM", value: "8GB", availability: true },
                         { id: 2, name: "Storage", value: "1TB", availability: null },
                         { id: 3, name: "Processor", value: "intel-i5", availability: null },
                         { id: 4, name: "Colour", value: "Silver", availability: null },
                    ],
                    productImage:'',
                },
                {
                    id: 2,
                    productName: "T-Shirt",
                    brand: "Adidas",
                    productPrice: 1699.0,
                    availability: false,
                    varientList: [
                        { id: 1, name: "Size", value: "XL", availability: null },
                        { id: 2, name: "Colour", value: "Red", availability: null },
                        { id: 3, name: "Material", value: "Cotton", availability: null },
                    ],
                    productImage: '',
                }],
                searchQuery:''
                
        };
    },

    computed:{
        filteredProducts(){
            // return this.products.filter(product=>
            //     product.productName.toLowerCase().includes(this.searchQuery.toLowerCase())
           this.result= this.products.filter(product=>
           product.productName.toLowerCase().includes(this.searchQuery.toLowerCase()) ||
           product.brand.toLowerCase().includes(this.searchQuery.toLowerCase())
        )
        //  );
        }
    },

    mounted() {
        // console.log("### : ",filteredProducts());
            
        console.log("fdfd");
        this.getProducts();

    },

    methods: {

        getProducts() {
            axios.get(`http://localhost:8082/digi/product/fetchAll?pageNo=0&pageSize=20`).then(res => {
                console.log("Response :", res);
                this.products = res.data.content

            })
        }, 

        deleteProduct(id){
            axios.delete(`http://localhost:8082/digi/product/delete?id=${id}`).then(res=>{
                console.log("Response :", res);
                alert(res.data);
                window.location.reload();
            }).catch((error)=>{
                console.log("Errorr : ",error.response.data);
                alert("Error : "+error.response.data)
                
            })
        },

        imageSrc(productImage) {
            if (productImage) {
                return `data:image/jpeg;base64,${productImage}`;
            }
            return '';
        }
    }
}
</script>

<style>
.table-responsive {
    margin-bottom: 15px;
}

.search-bar {
  padding: 8px;
  margin-bottom: 10px;
  margin-top: 10px;
  margin-right: 40px;
  width: 100%;
  max-width: 300px;
  font-size: 16px;
  border-radius: 8px;
  float: right; 
}

.add-btn {
  margin-bottom: 10px;
  margin-top: 10px;
  margin-right: 40px;
}
th {
    font-weight:bold;
}
</style>